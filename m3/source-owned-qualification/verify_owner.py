"""Verify the existing full owning source; no source transformation authority."""
from pathlib import Path
import hashlib
import json
import os
import subprocess
import sys

CRATE=Path(__file__).resolve().parent
ROOT=Path(sys.argv[1]) if len(sys.argv)>1 else CRATE.parents[1]
BASE='ef39f9d7f82c0d05c5c32b081d9a48b07579bccc'
TREE='d5a55a690f73ee8267cb82b4bc1f74235ca2a213'
env=dict(os.environ,GIT_CONFIG_COUNT='2',GIT_CONFIG_KEY_0='core.autocrlf',GIT_CONFIG_VALUE_0='false',GIT_CONFIG_KEY_1='core.longpaths',GIT_CONFIG_VALUE_1='true')

def git(args):
    result=subprocess.run(['git','-C',str(ROOT),*args],capture_output=True,env=env,timeout=60)
    if result.returncode:
        raise RuntimeError('SOURCE_GIT_IDENTITY_REFUSAL: '+result.stderr.decode('utf-8',errors='replace'))
    return result.stdout

def verify():
    # Native guard reads only these trusted metadata/guard files before source bodies.
    native=['powershell','-NoProfile','-NonInteractive','-ExecutionPolicy','Bypass','-File',str(CRATE/'windows-reparse-preflight.ps1'),'-Root',str(ROOT),'-Source',str(ROOT),'-Manifest',str(CRATE/'SOURCE_PATHS.tsv'),'-Context',str(CRATE/'RECIPE_PATHS.tsv')]
    result=subprocess.run(native,capture_output=True,timeout=180)
    if result.returncode:
        raise RuntimeError('NATIVE_SOURCE_CUSTODY_REFUSAL: '+result.stderr.decode('utf-8',errors='replace'))
    seal=json.loads((CRATE/'SOURCE_SEAL.json').read_text(encoding='utf-8'))
    if seal['owner_commit']!=BASE or seal['owner_tree']!=TREE:
        raise RuntimeError('SOURCE_SEAL_IDENTITY_REFUSAL')
    if git(['rev-parse',BASE+'^{tree}']).decode().strip()!=TREE:
        raise RuntimeError('SOURCE_BASE_TREE_REFUSAL')
    git(['merge-base','--is-ancestor',BASE,'HEAD'])
    mismatches=[]
    for row in seal['source_files']:
        path=ROOT/row['path']
        if not path.is_file():
            mismatches.append(row['path']+':missing')
            continue
        before=path.stat()
        data=path.read_bytes()
        after=path.stat()
        if (before.st_size,before.st_mtime_ns)!=(after.st_size,after.st_mtime_ns):
            mismatches.append(row['path']+':mutated-during-read')
        oid=hashlib.sha1(('blob '+str(len(data))+'\0').encode('ascii')+data).hexdigest()
        if oid!=row['git_oid'] or hashlib.sha256(data).hexdigest()!=row['sha256']:
            mismatches.append(row['path']+':bytes')
    if mismatches:
        raise RuntimeError('SOURCE_BYTES_REFUSAL: '+','.join(mismatches))
    print(json.dumps({'status':'PASS_EXACT_EXPECTED_OWNER_SOURCE_BYTES','base_commit':BASE,'base_tree':TREE,'source_files':len(seal['source_files']),'admitted_source_mutations':len(seal.get('recipe_authorized_changes',[])),'build_admission':False}),flush=True)

if __name__=='__main__':
    verify()
