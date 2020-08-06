# Ip address unique counter

Console utility for counting the number of unique IP addresses (ver. 4) in a given large file.

It supports ONLY IP addresses of ver. 4.

IP Addresses in the file must be written strictly line by line:
```
...
227.215.10.99
161.71.174.30
215.10.61.63
...
```

To count the number of unique IP addresses was used a bit mask approach. To work with all IP addresses it requires `4294967296 bits` or `512Mb`. `LongBitSet` is used to work with bitmasks that based on `BitSet's` that are distributed in the `HashMap`. The maximum number of `BitSet's` in the map is `64` (is achieved by the value of the constant - `VALUE_BITS = 26`) and the maximum number of bits in each `BitSet` is `8Mb`. To reduce memory consumption, the size of the newly created `BitSet` is `128 bytes`.

If you want to rebalance all structures then you have to adjust the constant values - `VALUE_BITS` and `INITIAL_BITS_COUNT`.

### Installation

Console utility requires [Apache Maven](https://maven.apache.org/) (in my case I was using v3.6.0) and Java v11 to run.

```sh
$ mvn clean install
```

Once done, run the utility by passing the path to the file through the key `-file-path`:

```sh
$ java -cp target/IpAddressUniqueCounter-1.0.0.jar com.counter.Estimator -file-path <Your_file_with_ip_addresses>
```