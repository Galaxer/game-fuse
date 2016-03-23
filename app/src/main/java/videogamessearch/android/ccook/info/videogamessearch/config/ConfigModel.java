package videogamessearch.android.ccook.info.videogamessearch.config;

public class ConfigModel {

    private String apiKey = "";

    public String getApiKey() {
        if (apiKey == null) {
            return "";
        }
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}