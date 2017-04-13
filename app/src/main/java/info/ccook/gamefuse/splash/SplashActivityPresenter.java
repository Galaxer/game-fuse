package info.ccook.gamefuse.splash;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import info.ccook.gamefuse.AppConfig;
import rx.Observer;

class SplashActivityPresenter extends MvpNullObjectBasePresenter<SplashView> {

    private AppConfig config;

    SplashActivityPresenter(AppConfig config) {
        this.config = config;
    }

    void fetchConfig() {
        config.fetch().subscribe(new Observer<Void>() {
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
}