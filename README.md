# Java Redis Lettuce GraalVM

Sample application to try [Lettuce Redis library](https://github.com/lettuce-io/lettuce-core) with [GraalVM](https://www.graalvm.org/).

## Running the application

To run Redis you can use Docker:

```bash
$ docker run -it --rm -p 6379:6379 redislabs/redisearch:1.6.13
```

To run the application:

```bash
$ ./gradlew run
```

## GraalVM

If you don't have GraalVM installed, run:

```bash
$ sdk install java 20.1.0.r8-grl
$ gu install native-image
```

Then, to convert the application to a native-image:

```bash
$ sdk use java 20.1.0.r8-grl
$ ./build-native-image.sh
```

And start the application:

```bash
$ ./redis-lettuce-graal
```
