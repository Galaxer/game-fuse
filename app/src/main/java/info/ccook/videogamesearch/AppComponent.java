package info.ccook.videogamesearch;

import android.ccook.info.giantbombapi.GiantBombAPI;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    Context applicationContext();
    GiantBombAPI.Builder giantBombApiBuilder();
}