package io.androidninja;

public interface Response<T> {
    void onSuccess(T data);
    void onFailure(String errorMessage);
}
