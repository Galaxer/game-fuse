package info.ccook.videogamesearch.network;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    OkHttpClient provideOkHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .cache(new Cache(context.getCacheDir(), GiantBombService.MAX_CACHE_DIR_SIZE))
                .connectTimeout(GiantBombService.TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(GiantBombService.TIMEOUT_SECONDS, TimeUnit.SECONDS).build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(GiantBombService.BASE_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    @Provides
    GiantBombService provideGiantBombService(Retrofit retrofit) {
        return retrofit.create(GiantBombService.class);
    }

    @Provides
    NetworkManager provideNetworkManager(Context context) {
        return new NetworkManager(context);
    }
}