package info.ccook.gamefuse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class RetainedFragment<T> extends Fragment {

    private T object;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }
}