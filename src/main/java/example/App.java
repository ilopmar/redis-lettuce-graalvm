package example;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class App {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connect = client.connect();

        RedisExamples redisExamples = new RedisExamples(connect);

        redisExamples.runBasicExample();
        redisExamples.runCompletableFutureExample();
        redisExamples.runCommandExample();

        connect.close();
        client.shutdown();
    }
}
