package info.ccook.gamefuse;

import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends
        MvpActivity<V, P> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    public void injectDependencies() {

    }
}