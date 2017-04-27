package info.ccook.gamefuse.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

public class SearchActivityPresenterTest {

    @RunWith(MockitoJUnitRunner.class)
    public static class UpdateSearchViewStateTest {

        @Mock private GameSearchView view;

        private SearchActivityPresenter presenter;
        private String query = "Super Mario";

        @Before
        public void setUp() throws Exception {
            presenter = new SearchActivityPresenter(null, null);
            presenter.attachView(view);
        }

        @Test
        public void testNullQuery_HasFocus() {
            presenter.updateSearchViewState(null, true);
            Mockito.verifyZeroInteractions(view);
        }

        @Test
        public void testNullQuery_NoFocus() {
            presenter.updateSearchViewState(null, false);
            Mockito.verify(view, Mockito.only()).clearSearchViewFocus();
        }

        @Test
        public void testEmptyQuery_HasFocus() {
            presenter.updateSearchViewState("", true);
            Mockito.verifyZeroInteractions(view);
        }

        @Test
        public void testEmptyQuery_NoFocus() {
            presenter.updateSearchViewState(null, false);
            Mockito.verify(view, Mockito.only()).clearSearchViewFocus();
        }

        @Test
        public void testQuery_NoFocus() {
            presenter.updateSearchViewState(query, false);
            Mockito.verify(view, Mockito.times(1)).focusOnSearchViewAndShowKeyboard();
            Mockito.verify(view, Mockito.times(1)).setSearchQuery(query);
            Mockito.verify(view, Mockito.times(1)).clearSearchViewFocus();
        }

        @Test
        public void testQuery_HasFocus() {
            presenter.updateSearchViewState(query, true);
            Mockito.verify(view, Mockito.times(1)).focusOnSearchViewAndShowKeyboard();
            Mockito.verify(view, Mockito.times(1)).setSearchQuery(query);
            Mockito.verify(view, Mockito.times(0)).clearSearchViewFocus();
        }
    }
}