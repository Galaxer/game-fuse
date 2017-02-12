package android.ccook.info.giantbombapi;

public interface Callback<T> {
    void onError(Throwable error);
    void onSuccess(T response);
}