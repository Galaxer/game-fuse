package videogamessearch.android.ccook.info.videogamessearch;

import android.app.Application;

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.Initializer.init(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}