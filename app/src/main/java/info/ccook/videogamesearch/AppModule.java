package info.ccook.videogamesearch;

import android.app.Application;
import android.ccook.info.giantbombapi.AutoValueGsonTypeAdapterFactory;
import android.ccook.info.giantbombapi.Endpoints;
import android.content.Context;

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
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Endpoints provideEndpoints(Context context) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .registerTypeAdapterFactory(AutoValueGsonTypeAdapterFactory.create())
                        .create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://www.giantbomb.com/api/")
                .client(new OkHttpClient.Builder().cache(new Cache(context.getCacheDir(), 10))
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build())
                .build()
                .create(Endpoints.class);
    }
}