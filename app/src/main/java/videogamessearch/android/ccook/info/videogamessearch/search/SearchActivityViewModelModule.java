package videogamessearch.android.ccook.info.videogamessearch.search;

import java.io.BufferedReader;

import dagger.Module;
import dagger.Provides;
import videogamessearch.android.ccook.info.videogamessearch.config.ConfigManager;
import videogamessearch.android.ccook.info.videogamessearch.config.ConfigManagerModule;

@Module(includes = {ConfigManagerModule.class})
public class SearchActivityViewModelModule {

    @Provides
    ConfigManager provideConfigManager(BufferedReader bufferedReader) {
        return new ConfigManager(bufferedReader);
    }
}