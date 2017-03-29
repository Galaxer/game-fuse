package info.ccook.gamefuse.splash;

import dagger.Component;
import info.ccook.gamefuse.AppComponent;
import info.ccook.gamefuse.PerActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = { SplashModule.class })
interface SplashComponent {
    void inject(SplashActivity splashActivity);
}