package info.ccook.videogamesearch;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import rx.Observable;
import rx.Subscriber;

public class AppConfig {

    private static final String GIANT_BOMB_API_KEY_KEY = "giantBombApiKey";
    private static final String GIANT_BOMB_API_BASE_URL_KEY = "giantBombApiBaseUrl";

    private FirebaseRemoteConfig config;

    AppConfig(FirebaseRemoteConfig config) {
        this.config = config;
    }

    public Observable<Void> fetch() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final Subscriber<? super Void> subscriber) {
                config.fetch().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            config.activateFetched();
                            subscriber.onNext(null);
                        } else {
                            subscriber.onError(new Throwable());
                        }
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    public String getGiantBombApiKey() {
        return config.getString(GIANT_BOMB_API_KEY_KEY);
    }

    String getGiantBombApiBaseUrl() {
        return config.getString(GIANT_BOMB_API_BASE_URL_KEY);
    }
}