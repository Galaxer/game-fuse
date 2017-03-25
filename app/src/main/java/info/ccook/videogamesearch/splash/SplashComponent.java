package info.ccook.videogamesearch.splash;

import dagger.Component;
import info.ccook.videogamesearch.AppComponent;
import info.ccook.videogamesearch.PerActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = { SplashModule.class })
interface SplashComponent {
    void inject(SplashActivity splashActivity);
}