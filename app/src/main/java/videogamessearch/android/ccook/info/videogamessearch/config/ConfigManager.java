package videogamessearch.android.ccook.info.videogamessearch.config;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConfigManager {

    private static final String fileName = "config.json";
    private static final String charEncoding = "UTF-8";

    public ConfigModel getConfig(Context context) {
        return parseConfig(readConfig(context));
    }

    public ConfigModel parseConfig(String json) {
        return new Gson().fromJson(json, ConfigModel.class);
    }

    public String readConfig(Context context) {
        String result = null;
        StringBuilder data = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charEncoding));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
            result = data.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
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