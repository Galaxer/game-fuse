package android.ccook.info.giantbombapi;

import android.ccook.info.giantbombapi.models.SearchResults;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GiantBombAPI {

    private String apiKey;
    private Context context;
    private Cache cache;
    private Retrofit.Builder retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    private GiantBombAPI(Builder builder) {
        context = builder.context;
        apiKey = builder.apiKey;
        cache = new Cache(context.getCacheDir(), builder.maxCacheDirSize);
    }

    public String getApiKey() {
        return apiKey;
    }

    public Context getContext() {
        return context;
    }

    private Endpoints createEndpoints(APIRequest request) {
        return retrofit
                .baseUrl(request.getBaseUrl())
                .client(new OkHttpClient.Builder()
                        .cache(cache)
                        .connectTimeout(request.getTimeoutSeconds(), TimeUnit.SECONDS)
                        .readTimeout(request.getTimeoutSeconds(), TimeUnit.SECONDS)
                        .build())
                .build()
                .create(Endpoints.class);
    }

    public void search(final SearchRequest request) {
        createEndpoints(request).search(apiKey, request.getQuery(),
                APIRequest.RESPONSE_FORMAT_JSON, request.getLimit(), request.getResources(),
                request.getFieldList())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResults>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable error) {
                        request.getCallback().onError(null);
                    }

                    @Override
                    public final void onNext(SearchResults searchResults) {
                        request.getCallback().onSuccess(searchResults);
                    }
                });
    }

    public static class Builder {

        private Context context;
        private String apiKey = "";
        private long maxCacheDirSize = 10;

        public Builder(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context is null");
            }
            this.context = context;
        }

        /**
         * Set max cache directory size.
         * @param maxCacheDirSize Max cache directory size in MB.
         * @return Builder
         */
        public Builder setMaxCacheDirSize(long maxCacheDirSize) {
            this.maxCacheDirSize = maxCacheDirSize;
            return this;
        }

        public Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public GiantBombAPI build() {
            return new GiantBombAPI(this);
        }
    }
}