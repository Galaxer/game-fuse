package info.ccook.gamefuse.splash;

import dagger.Module;
import dagger.Provides;
import info.ccook.gamefuse.AppConfig;
import info.ccook.gamefuse.PerActivity;

@Module
class SplashModule {

    @Provides
    @PerActivity
    SplashActivityPresenter provideMainActivityPresenter(AppConfig config) {
        return new SplashActivityPresenter(config);
    }
}