package videogamessearch.android.ccook.info.videogamessearch.search;

import android.util.Log;

import javax.inject.Inject;

import videogamessearch.android.ccook.info.videogamessearch.config.ConfigManager;

public class SearchActivityViewModel {

    private ConfigManager configManager;

    @Inject
    public SearchActivityViewModel(ConfigManager configManager) {
        this.configManager = configManager;
    }

    public void getConfig() {
        String apiKey = configManager.getConfig().getApiKey();
        Log.d("stuff", apiKey);
    }
}