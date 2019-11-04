package net.leloubil.mcpwn.async;

import android.content.Context;
import lombok.Getter;

import java.util.function.Consumer;

public class McPwnCallback<T> implements Consumer<T> {

    @Getter
    private Context context;

    private Consumer<T> consumer;

    private McPwnCallback(Context c) {
        this.context = c;
    }

    static <R> McPwnCallback<R> then(Context c, Consumer<R> consumer) {
        return new McPwnCallback<R>(c).then(consumer);
    }

    private McPwnCallback<T> then(Consumer<T> consumer) {
        this.consumer = consumer;
        return this;
    }

    @Override
    public void accept(T t) {
        consumer.accept(t);
    }
}
