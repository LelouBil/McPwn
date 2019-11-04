package net.leloubil.mcpwn.async;

@FunctionalInterface
public interface TriConsumer<T, K, V> {

    void consume(T t, K k, V v);
}
