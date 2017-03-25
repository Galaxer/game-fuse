package info.ccook.videogamesearch.splash;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import info.ccook.videogamesearch.AppConfig;
import info.ccook.videogamesearch.PerActivity;
import info.ccook.videogamesearch.RetainedFragment;

@Module
class SplashModule {

    private static final String SPLASH_PRESENTER_FRAGMENT_TAG = "SplashPresenterFragment";

    private SplashView splashView;
    private FragmentManager fragmentManager;

    SplashModule(SplashView splashView, FragmentManager fragmentManager) {
        this.splashView = splashView;
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @PerActivity
    SplashActivityPresenter provideMainActivityPresenter(AppConfig config) {
        RetainedFragment existingFragment = (RetainedFragment) fragmentManager
                .findFragmentByTag(SPLASH_PRESENTER_FRAGMENT_TAG);
        if (existingFragment != null) {
            return (SplashActivityPresenter) existingFragment.get();
        } else {
            RetainedFragment<SplashActivityPresenter> newFragment = new RetainedFragment<>();
            newFragment.set(new SplashActivityPresenter(splashView, config));
            fragmentManager.beginTransaction().add(newFragment, SPLASH_PRESENTER_FRAGMENT_TAG)
                    .commit();
            return newFragment.get();
        }
    }
}