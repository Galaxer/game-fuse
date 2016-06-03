package info.ccook.videogamesearch.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkManager {

    private Context context;
    public static final long MAX_CACHE_DIR_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final long TIMEOUT_SECONDS = 20;

    public NetworkManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}