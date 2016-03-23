package videogamessearch.android.ccook.info.videogamessearch.config;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConfigManagerTest {

    @Test
    public void testParseConfigJsonNull() {
        ConfigManager configManager = new ConfigManager(null);
        assertNotNull(configManager.parseConfig(null));
    }

    @Test
    public void testReadConfigBufferedReaderNull() {
        ConfigManager configManager = new ConfigManager(null);
        assertEquals("", configManager.readConfig());
    }

    @Test
    public void testGetConfigSuccess() {
        String mockApiKey = "An API key";
        ConfigModel mockConfigModel = new ConfigModel();
        mockConfigModel.setApiKey(mockApiKey);
        Gson gson = new Gson();
        String mockFileData = gson.toJson(mockConfigModel);
        InputStream inputStream = new ByteArrayInputStream(mockFileData.getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ConfigManager configManager = new ConfigManager(bufferedReader);
        assertEquals(mockFileData, gson.toJson(configManager.getConfig()));
    }
}