package android.ccook.info.giantbombapi;

import org.junit.Test;

public class GiantBombAPITest {

    @Test(expected = IllegalArgumentException.class)
    public void constructWithNullContext() {
        new GiantBombAPI.Builder(null);
    }
}