package com.counter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IpCounterTest {
  private final String filePath = "src/test/resources/ip-addresses";
  private final IpCounter ipCounter = new IpCounter(filePath);

  @DisplayName("It converts IP addresses to decimal number")
  @Test
  void testOfconvertIpAddressToNumber() {
    assertEquals(0L, ipCounter.convertIpAddressToNumber("0.0.0.0"));
    assertEquals(169090600L, ipCounter.convertIpAddressToNumber("10.20.30.40"));
    assertEquals(4294967295L, ipCounter.convertIpAddressToNumber("255.255.255.255"));
  }

  @DisplayName("It gets cardinality before count")
  @Test
  void testOfCardinalityBeforeCount() {
    assertEquals(ipCounter.cardinality(), 0L);
  }

  @DisplayName("It gets cardinality after count")
  @Test
  void testOfCardinalityAfterCount() {
    ipCounter.count();
    assertEquals(1520L, ipCounter.cardinality());
  }
}