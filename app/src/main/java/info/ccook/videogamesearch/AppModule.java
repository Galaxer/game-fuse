package info.ccook.videogamesearch;

import android.app.Application;
import android.ccook.info.giantbombapi.AutoValueGsonTypeAdapterFactory;
import android.ccook.info.giantbombapi.Endpoints;
import android.content.Context;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class AppModule {

    private static final int TIMEOUT = 20; // In seconds
    private static final int CACHE_MAX_SIZE = 10; // In MB

    private Application application;

    AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Endpoints provideEndpoints(Context context, AppConfig config) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
                        .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(config.getGiantBombApiBaseUrl())
                .client(new OkHttpClient.Builder()
                        .cache(new Cache(context.getCacheDir(), CACHE_MAX_SIZE))
                        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                        .build())
                .build()
                .create(Endpoints.class);
    }

    @Provides
    @Singleton
    FirebaseRemoteConfig provideFirebaseRemoteConfig() {
        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setDefaults(R.xml.firebase_defaults);
        return firebaseRemoteConfig;
    }

    @Provides
    @Singleton
    AppConfig provideAppConfig(FirebaseRemoteConfig config) {
        return new AppConfig(config);
    }
}