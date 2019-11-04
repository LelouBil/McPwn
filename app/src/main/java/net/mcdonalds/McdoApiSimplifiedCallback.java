package net.mcdonalds;

public interface McdoApiSimplifiedCallback<T> {
    void failure(String str, Throwable th);

    void success(T t);
}
