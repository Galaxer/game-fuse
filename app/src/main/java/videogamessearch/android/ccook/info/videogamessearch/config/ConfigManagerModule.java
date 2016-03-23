package videogamessearch.android.ccook.info.videogamessearch.config;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dagger.Module;
import dagger.Provides;
import videogamessearch.android.ccook.info.videogamessearch.AppModule;

@Module(includes = {AppModule.class})
public class ConfigManagerModule {

    @Provides
    InputStream provideInputStream(Context applicationContext) {
        try {
            return applicationContext.getAssets().open(ConfigManager.fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new InputStream() {
                @Override
                public int read() {
                    return -1;
                }
            };
        }
    }

    @Provides
    InputStreamReader provideInputStreamReader(InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

    @Provides
    BufferedReader provideBufferedReader(InputStreamReader inputStreamReader) {
        return new BufferedReader(inputStreamReader);
    }
}