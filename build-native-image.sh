./gradlew assemble
native-image --no-server --no-fallback --class-path build/libs/redis-lettuce-all.jar
