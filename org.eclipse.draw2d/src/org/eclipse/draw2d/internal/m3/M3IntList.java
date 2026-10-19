/*******************************************************************************
 * Copyright (c) 2026 Synexia, Hitesh Soliwal (hsoliwal@gmail.com) and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0, or (at your option) the Eclipse
 * Public License 2.0, which are available at
 * https://www.apache.org/licenses/LICENSE-2.0 and
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: Apache-2.0 OR EPL-2.0
 *
 * Contributors:
 *     Hitesh Soliwal (Synexia) - initial API and implementation
 *******************************************************************************/
package org.eclipse.draw2d.internal.m3;

import java.util.Arrays;

/**
 * Growable int array, reused across queries: the lane behind every slot list
 * of the child index. No boxing, no per-element objects.
 */
public final class M3IntList {

	private int[] values = new int[8];
	private int size;
	private long[] scratch = new long[8];

	/** Number of values. */
	public int size() {
		return size;
	}

	/** Value at {@code index} (unchecked beyond the backing array). */
	public int get(int index) {
		return values[index];
	}

	/** Replaces the value at {@code index}. */
	public void set(int index, int value) {
		values[index] = value;
	}

	/** Appends a value. */
	public void add(int value) {
		if (size == values.length) {
			values = Arrays.copyOf(values, size * 2);
		}
		values[size++] = value;
	}

	/** Removes and returns the last value. */
	public int removeLast() {
		return values[--size];
	}

	/** Removes every value; keeps the capacity. */
	public void clear() {
		size = 0;
	}

	/**
	 * Sorts the values ascending by {@code keys[value]}; ties keep no particular
	 * order (keys are unique in use).
	 */
	public void sortBy(long[] keys) {
		if (size < 2) {
			return;
		}
		if (scratch.length < size) {
			scratch = new long[Math.max(size, scratch.length * 2)];
		}
		long[] packed = scratch;
		boolean small = true;
		for (int i = 0; i < size; i++) {
			long k = keys[values[i]];
			small &= k >= 0 && k < (1L << 39) && values[i] < (1 << 24);
			packed[i] = (k << 24) | values[i];
		}
		if (small) {
			Arrays.sort(packed, 0, size);
			for (int i = 0; i < size; i++) {
				values[i] = (int) (packed[i] & 0xffffff);
			}
			return;
		}
		Integer[] boxed = new Integer[size];
		for (int i = 0; i < size; i++) {
			boxed[i] = Integer.valueOf(values[i]);
		}
		Arrays.sort(boxed, (a, b) -> Long.compare(keys[a.intValue()], keys[b.intValue()]));
		for (int i = 0; i < size; i++) {
			values[i] = boxed[i].intValue();
		}
	}
}
