package com.counter;

import java.util.HashMap;
import java.util.Map;
import java.util.BitSet;

public class LongBitSet {
  private static final int VALUE_BITS = 26;
  private static final long VALUE_MASK = (1 << VALUE_BITS) - 1;
  private static final int INITIAL_BITS_COUNT = 1024; // 128 Bytes

  private Map<Long, BitSet> bitSetMap = new HashMap<Long, BitSet>(16);

  private long getBitSetIndex(final long index) {
    return index >> VALUE_BITS;
  }

  private int getBitPosition(final long index) {
    return (int) (index & VALUE_MASK);
  }

  private BitSet bitSet(final long index) {
    final Long bitSetIndex = getBitSetIndex(index);
    BitSet bitSet = bitSetMap.get(bitSetIndex);

    if (bitSet == null) {
      bitSet = new BitSet(INITIAL_BITS_COUNT);
      bitSetMap.put(bitSetIndex, bitSet);
    }

    return bitSet;
  }

  public void set(final long index, final boolean value) {
    final int bitPosition = getBitPosition(index);

    if (value) {
      bitSet(index).set(bitPosition, value);
      return;
    }

    final BitSet bitSet = bitSetMap.get(getBitSetIndex(index));
    if (bitSet != null) {
      bitSet.clear(bitPosition);
    }
  }

  public void set(final long index) {
    set(index, true);
  }

  public boolean get(final long index) {
    final BitSet bitSet = bitSetMap.get(getBitSetIndex(index));
    return bitSet != null && bitSet.get(getBitPosition(index));
  }

  public Long cardinality() {
    Long cardinality = 0L;
    for (final Map.Entry<Long, BitSet> entry : bitSetMap.entrySet()) {
      cardinality += entry.getValue().cardinality();
    }
    return cardinality;
  }

  public void clear(final long fromIndex, final long toIndex) {
    if (fromIndex >= toIndex) {
      return;
    }

    final long fromBitSetIndex = getBitSetIndex(fromIndex);
    final long toBitSetIndex = getBitSetIndex(toIndex);

    // Remove all maps in the middle
    for (long i = fromBitSetIndex + 1; i < toBitSetIndex; ++i) {
      bitSetMap.remove(i);
    }

    // Clean two corner bit sets manually
    final BitSet fromBitSet = bitSetMap.get(fromBitSetIndex);
    final BitSet toBitSet = bitSetMap.get(toBitSetIndex);

    // If both bit sets are the same
    if (fromBitSet != null && fromBitSet == toBitSet) {
      fromBitSet.clear(getBitPosition(fromIndex), getBitPosition(toIndex));
      return;
    }

    // Clean left bit subset from left index to the end
    if (fromBitSet != null) {
      fromBitSet.clear(getBitPosition(fromIndex), fromBitSet.length());
    }

    // Clean right bit subset from 0 to given index
    if (toBitSet != null) {
      toBitSet.clear(0, getBitPosition(toIndex));
    }
  }

  public void clear(final long bitIndex) {
    final long bitSetIndex = getBitSetIndex(bitIndex);
    final BitSet bitSet = bitSetMap.get(bitSetIndex);

    if (bitSet != null) {
      bitSet.clear(getBitPosition(bitIndex));
    }
  }
}