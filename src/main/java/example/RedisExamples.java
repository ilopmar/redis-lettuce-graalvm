package example;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.dynamic.RedisCommandFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RedisExamples {

    private final StatefulRedisConnection<String, String> connect;

    public RedisExamples(StatefulRedisConnection<String, String> connect) {
        this.connect = connect;
    }

    void runBasicExample() {
        RedisCommands<String, String> syncCommands = connect.sync();

        syncCommands.set("key", "Hello, Redis!");
        System.out.println(syncCommands.get("key"));
    }

    void runCompletableFutureExample() {
        RedisAsyncCommands<String, String> async = connect.async();
        RedisFuture<String> set = async.set("key", "value");
        RedisFuture<String> get = async.get("key");

        try {
            set.await(1, TimeUnit.SECONDS);
            System.out.println(set.get());
            System.out.println(get.get(1, TimeUnit.MINUTES));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    void runCommandExample() {
        RedisCommandFactory factory = new RedisCommandFactory(connect);
        KeyCommands commands = factory.getCommands(KeyCommands.class);
        commands.set("name", "Redis");
        System.out.println(commands.get("name"));
    }
}
