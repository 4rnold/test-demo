package com.example.demo.t220314_redis_sentinel;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedisSentinelFailover {
    public static void main(String[] args) {
        String masterName = "mymaster";
        HashSet<String> sentinels = new HashSet<>();
        sentinels.add("192.168.137.111:26379");
        sentinels.add("192.168.137.112:26379");
        sentinels.add("192.168.137.113:26379");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName,sentinels);

        while (true) {
            Jedis jedis = null;
            try {
                jedis = jedisSentinelPool.getResource();
                int i = new Random().nextInt(100000);
                String key = "k-" + i;
                String value = "v-" + i;
                jedis.set(key, value);
                log.info("{} value is {}",key,jedis.get(key));
                TimeUnit.MICROSECONDS.sleep(100000);
            } catch (Exception exception) {
                log.error(exception.getMessage(),exception);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }
}
