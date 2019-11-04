package net.mcdonalds;

import retrofit2.Response;

public interface McdoApiCallback<T> {
    void failure(String str, Throwable th);

    void success(T t, Response response);
}
