"""Run the exact owning CI source command with preserved flags and real failures."""
from datetime import datetime,timezone
import hashlib
import json
import os
from pathlib import Path
import subprocess
import sys
import time

sys.dont_write_bytecode=True
import verify_owner

ROOT=Path(sys.argv[1])
OUT=Path(sys.argv[2])
MAVEN=Path(sys.argv[3])
CACHE=Path(sys.argv[4])
JDK=Path(sys.argv[5])
NETWORK=sys.argv[6] if len(sys.argv)>6 else 'offline'
if NETWORK not in ('offline','online'):
    raise SystemExit('BUILD_NETWORK_MODE_REFUSAL')
OUT.mkdir(parents=True,exist_ok=False)
sdk_path=Path('S:/codex-sdk/temurin25-20261006/SDK_CUSTODY.json')
sdk_bytes=sdk_path.read_bytes()
if hashlib.sha256(sdk_bytes).hexdigest()!='313d87611ed46c348f07d5d29a6ecc5d66355a32fa07cd68c19c612f66413f0b':
    raise SystemExit('OWNED_JDK25_RECEIPT_DRIFT_REFUSAL')
sdk=json.loads(sdk_bytes)
if JDK.resolve()!=Path(sdk['java_home']).resolve():
    raise SystemExit('OWNED_JDK25_ROOT_REFUSAL')
for entry in sdk['files']:
    path=JDK/entry['path']
    if path.stat().st_size!=entry['bytes'] or hashlib.sha256(path.read_bytes()).hexdigest()!=entry['sha256']:
        raise SystemExit('OWNED_JDK25_BYTES_REFUSAL: '+entry['path'])
version=subprocess.run([str(JDK/'bin/java.exe'),'-version'],capture_output=True,timeout=30)
if version.returncode or b'25.0.4.1' not in version.stderr:
    raise SystemExit('OWNED_JDK25_VERSION_REFUSAL')
verify_owner.ROOT=ROOT
verify_owner.verify()
seal=json.loads((verify_owner.CRATE/'SOURCE_SEAL.json').read_text(encoding='utf-8'))
before={x['path']:(ROOT/x['path']).stat().st_mtime_ns for x in seal['source_files']}
argv=[str(MAVEN),*(['-o'] if NETWORK=='offline' else []),'-V','-B','-fae','-ntp','-Pmaster','-Dmaven.repo.local='+str(CACHE),'clean','verify']
env=dict(os.environ,JAVA_HOME=str(JDK),TEMP=str(OUT),TMP=str(OUT),MAVEN_OPTS='-Djava.io.tmpdir='+str(OUT)+' -Dfile.encoding=UTF-8 -Xmx2g',GIT_CONFIG_COUNT='2',GIT_CONFIG_KEY_0='core.autocrlf',GIT_CONFIG_VALUE_0='false',GIT_CONFIG_KEY_1='core.longpaths',GIT_CONFIG_VALUE_1='true')
started=datetime.now(timezone.utc).isoformat()
t=time.monotonic()
# Wait for the actual native Maven command; its original Tycho test timeouts remain authoritative.
with (OUT/'original-owning-build.log').open('wb') as log:
    result=subprocess.run(argv,cwd=ROOT,env=env,stdout=log,stderr=subprocess.STDOUT)
code=result.returncode
data=(OUT/'original-owning-build.log').read_bytes()
unchanged=[]
changed=[]
mtime_changed=[]
for row in seal['source_files']:
    path=ROOT/row['path']
    if path.is_file() and path.stat().st_mtime_ns!=before[row['path']]:
        mtime_changed.append(row['path'])
    if not path.is_file() or hashlib.sha256(path.read_bytes()).hexdigest()!=row['sha256']:
        changed.append(row['path'])
    else:
        unchanged.append(row['path'])
receipt={'status':'OWNED_BUILD_EXECUTED_PASS' if code==0 and not changed else 'REFUSED_OWNED_BUILD_FAILED_OR_SOURCE_DRIFT','argv':argv,'cwd':str(ROOT),'started_utc':started,'wall_seconds':time.monotonic()-t,'returncode':code,'timeout':False,'network_mode':NETWORK,'jdk':str(JDK),'declared_execution_environment':'JavaSE-25','executed_JDK_major':25,'JDK_custody_sha256':hashlib.sha256(sdk_bytes).hexdigest(),'JDK_files_verified':len(sdk['files']),'java_version_stderr_sha256':hashlib.sha256(version.stderr).hexdigest(),'original_CI_JDK_major':25,'original_CI_Maven_version':'3.9.12','executed_Maven_version':'3.9.14','additional_flags':(['-o'] if NETWORK=='offline' else [])+['-Dmaven.repo.local=<existing-cache>'],'original_CI_flags_preserved':['-V','-B','-fae','-ntp','-Pmaster','clean','verify'],'module_test_target_or_skip_override':False,'log_sha256':hashlib.sha256(data).hexdigest(),'source_bytes_unchanged':len(unchanged),'source_mtime_changed_paths':mtime_changed,'changed_source_paths':changed,'full_build_admission':code==0 and not changed,'cross_platform_or_JNI_admission':False}
(OUT/'OWNED_BUILD_RECEIPT.json').write_text(json.dumps(receipt,indent=2,sort_keys=True)+'\n',encoding='utf-8')
print(json.dumps(receipt,sort_keys=True),flush=True)
if changed:
    raise SystemExit('SOURCE_CUSTODY_AFTER_BUILD_REFUSAL')
raise SystemExit(code)
