package videogamessearch.android.ccook.info.videogamessearch.config;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;

import javax.inject.Inject;

public class ConfigManager {

    public static final String fileName = "config.json";
    private final BufferedReader bufferedReader;

    @Inject
    public ConfigManager(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public ConfigModel getConfig() {
        return parseConfig(readConfig());
    }

    public ConfigModel parseConfig(String json) {
        if (json == null || json.length() == 0) {
            return new ConfigModel();
        }

        try {
            return new Gson().fromJson(json, ConfigModel.class);
        } catch (JsonSyntaxException e) {
            return new ConfigModel();
        }
    }

    public String readConfig() {
        String result = "";

        if (bufferedReader != null) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}