package videogamessearch.android.ccook.info.videogamessearch.config;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigModelTest {

    @Test
    public void testGetApiKey() {
        ConfigModel configModel = new ConfigModel();
        configModel.setApiKey(null);
        assertNotNull(configModel.getApiKey());
    }
}