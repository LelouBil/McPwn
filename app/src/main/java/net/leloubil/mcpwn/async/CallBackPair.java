package net.leloubil.mcpwn.async;

import android.content.Context;
import android.util.Pair;
import lombok.Getter;

import java.util.function.Consumer;

public class CallBackPair<T, V> extends Pair<McPwnCallback<T>, McPwnCallback<V>> {

    @Getter
    private Context context;
    private boolean enabled = true;


    private CallBackPair(Context c, McPwnCallback<T> success, McPwnCallback<V> failure) {
        super(success, failure);
        this.context = c;
    }

    public static void create() {

    }

    public static <R, B> CallBackPair<R, B> duo(Context c, Consumer<R> consumer, Consumer<B> consumer2) {
        return new CallBackPair<>(c, McPwnCallback.then(c, consumer), McPwnCallback.then(c, consumer2));
    }

    public void success(T thing) {
        if (enabled) this.first.accept(thing);
    }

    public void failure(V thing) {
        this.second.accept(thing);
    }

    void disable() {
        this.enabled = false;
    }
}
