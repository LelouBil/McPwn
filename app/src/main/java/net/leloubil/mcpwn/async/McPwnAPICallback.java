package net.leloubil.mcpwn.async;

import android.content.Context;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class McPwnAPICallback<T> implements Callback<T> {

    @Getter
    Context context;

    @NonFinal
    TriConsumer<Call<T>, Response<T>, Context> contextTriConsumer = (a, b, c) -> {
    };
    @NonFinal
    TriConsumer<Call<T>, Throwable, Context> failureTriConsumer = (a, b, c) -> {
    };


    public McPwnAPICallback<T> onResponse(TriConsumer<Call<T>, Response<T>, Context> contextTriConsumer) {
        this.contextTriConsumer = contextTriConsumer;
        return this;
    }

    public McPwnAPICallback<T> onFailure(TriConsumer<Call<T>, Throwable, Context> failureTriConsumer) {
        this.failureTriConsumer = failureTriConsumer;
        return this;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        contextTriConsumer.consume(call, response, context);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        failureTriConsumer.consume(call, t, context);
    }
}
