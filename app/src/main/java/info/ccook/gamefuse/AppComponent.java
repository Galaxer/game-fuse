package info.ccook.gamefuse;

import android.ccook.info.giantbombapi.Endpoints;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    Endpoints endpoints();
    AppConfig appConfig();
}