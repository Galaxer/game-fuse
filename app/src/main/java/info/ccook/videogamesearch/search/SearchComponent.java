package info.ccook.videogamesearch.search;

import dagger.Component;
import info.ccook.videogamesearch.AppComponent;
import info.ccook.videogamesearch.PerActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = { SearchModule.class })
interface SearchComponent {
    void inject(SearchActivity searchActivity);
}