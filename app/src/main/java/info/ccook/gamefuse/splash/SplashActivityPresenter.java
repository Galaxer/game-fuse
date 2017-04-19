package info.ccook.gamefuse.splash;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

import info.ccook.gamefuse.AppConfig;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SplashActivityPresenter extends MvpNullObjectBasePresenter<SplashView> {

    private AppConfig config;
    private Subscription configFetch;

    SplashActivityPresenter(AppConfig config) {
        this.config = config;
    }

    private void fetchConfig() {
        configFetch = config.fetch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().showError();
            }

            @Override
            public void onNext(Void nothing) {
                getView().showSearch();
                getView().hideLoadingIndicator();
            }
        });
    }

    void continuedConfigFetch() {
        if (!isFetchingConfig()) {
            fetchConfig();
        }
    }

    private boolean isFetchingConfig() {
        return configFetch != null && !configFetch.isUnsubscribed();
    }
}