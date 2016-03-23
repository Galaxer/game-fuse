package videogamessearch.android.ccook.info.videogamessearch.config;

import com.google.gson.Gson;

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
        if (json == null) {
            return new ConfigModel();
        }
        return new Gson().fromJson(json, ConfigModel.class);
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