package com.counter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongBitSetTest {
  private final LongBitSet longBitSet = new LongBitSet();

  @DisplayName("It sets bit by given index")
  @Test
  void testOfSet() {
    assertEquals(0L, longBitSet.cardinality());
    longBitSet.set(255L);
    longBitSet.set(65280L);
    longBitSet.set(16711680L);
    longBitSet.set(4278190080L);
    assertEquals(4L, longBitSet.cardinality());
  }

  @DisplayName("It clears bits by given range of indexes")
  @Test
  void testOfClearBits() {
    longBitSet.set(255L);
    longBitSet.set(65280L);
    longBitSet.set(16711680L);
    longBitSet.set(4278190080L);
    assertEquals(4L, longBitSet.cardinality());
    longBitSet.clear(0L, 16711680L);
    assertEquals(2L, longBitSet.cardinality());
  }

  @DisplayName("It clears bit by given index")
  @Test
  void testOfClearBit() {
    longBitSet.set(4278190080L);
    assertEquals(1L, longBitSet.cardinality());
    longBitSet.clear(4278190080L);
    assertEquals(0L, longBitSet.cardinality());
  }

  @DisplayName("It gets bit by given index")
  @Test
  void testOfGet() {
    assertEquals(false, longBitSet.get(4278190080L));
    longBitSet.set(4278190080L);
    assertEquals(true, longBitSet.get(4278190080L));
  }
}