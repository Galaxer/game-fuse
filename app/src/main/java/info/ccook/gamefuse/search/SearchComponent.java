package info.ccook.gamefuse.search;

import dagger.Component;
import info.ccook.gamefuse.AppComponent;
import info.ccook.gamefuse.PerActivity;

@PerActivity
@Component(dependencies = AppComponent.class, modules = { SearchModule.class })
interface SearchComponent {
    void inject(SearchActivity searchActivity);
}