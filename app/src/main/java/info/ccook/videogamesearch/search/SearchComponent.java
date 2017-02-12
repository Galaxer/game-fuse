package info.ccook.videogamesearch.search;

import dagger.Component;
import info.ccook.videogamesearch.AppComponent;
import info.ccook.videogamesearch.PerFragment;

@PerFragment
@Component(dependencies = AppComponent.class, modules = {SearchFragmentModule.class})
interface SearchComponent {
    void inject(SearchFragment searchFragment);
}