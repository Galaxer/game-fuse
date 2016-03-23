package videogamessearch.android.ccook.info.videogamessearch.search;

import dagger.Module;
import dagger.Provides;
import videogamessearch.android.ccook.info.videogamessearch.config.ConfigManager;

@Module(includes = {SearchActivityViewModelModule.class})
public class SearchActivityModule {

    @Provides
    SearchActivityViewModel provideSearchViewModel(ConfigManager configManager) {
        return new SearchActivityViewModel(configManager);
    }
}