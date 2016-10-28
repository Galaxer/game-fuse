package info.ccook.videogamesearch.search;

import dagger.Component;
import info.ccook.videogamesearch.AppComponent;
import info.ccook.videogamesearch.PerFragment;
import info.ccook.videogamesearch.network.NetworkModule;

@PerFragment
@Component(dependencies = AppComponent.class, modules = {
        SearchFragmentModule.class,
        NetworkModule.class
})
interface SearchComponent {
    void inject(SearchFragment searchFragment);
}