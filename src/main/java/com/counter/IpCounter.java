package com.counter;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class IpCounter {
  private LongBitSet longBitSet;
  private String filePath;

  public IpCounter(String filePath) {
    longBitSet = new LongBitSet();
    this.filePath = filePath;
  }

  public void count() {
    BufferedReader reader;

    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();

      while (line != null) {
        longBitSet.set(convertIpAddressToNumber(line));
        line = reader.readLine();
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public long convertIpAddressToNumber(String ipAddress) {
    String[] ipAddressParts = ipAddress.trim().split("\\.");
    long result = 0;

    for (int i = 3; i >= 0; i--) {
      long ip = Long.parseLong(ipAddressParts[3 - i]);
      result |= ip << (i * 8);
    }

    return result;
  }

  public long cardinality() {
    return longBitSet.cardinality();
  }
}