package info.ccook.gamefuse.splash;

import info.ccook.gamefuse.AppConfig;
import rx.Observer;

class SplashActivityPresenter {

    private SplashView view;
    private AppConfig config;

    SplashActivityPresenter(SplashView view, AppConfig config) {
        this.view = view;
        this.config = config;
    }

    void fetchConfig() {
        config.fetch().subscribe(new Observer<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.showError();
            }

            @Override
            public void onNext(Void nothing) {
                view.showSearch();
                view.hideLoadingIndicator();
            }
        });
    }
}