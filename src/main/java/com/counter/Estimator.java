package com.counter;

import com.options.Options;

class Estimator {
  public static void main(String args[]) {
    Options options = new Options().parse(args);
    String filePath = options.get("file-path");

    System.out.println("File: " + filePath);

    IpCounter ipCounter = new IpCounter(filePath);
    long startTime = System.currentTimeMillis();
    ipCounter.count();
    long cardinality = ipCounter.cardinality();

    System.out.println(
      "Elapsed time: " + (System.currentTimeMillis() - startTime) +
      " ms, count of unique ip-addresses: " + cardinality
    );
  }
}