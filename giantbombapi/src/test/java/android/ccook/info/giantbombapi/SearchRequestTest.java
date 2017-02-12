package android.ccook.info.giantbombapi;

import org.junit.Assert;
import org.junit.Test;

public class SearchRequestTest {

    @Test(expected = IllegalArgumentException.class)
    public void setBaseUrlNullUrl() {
        new SearchRequest.Builder().setBaseUrl(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setBaseUrlInvalidUrl() {
        String invalidUrl = "ww.google.com";
        new SearchRequest.Builder().setBaseUrl(invalidUrl);
    }

    @Test
    public void setResourceNullResource() {
        SearchRequest searchRequest = new SearchRequest.Builder().setResource(null).build();
        Assert.assertEquals("", searchRequest.getResources());
    }

    @Test
    public void setResourceMultipleResources() {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .setResource("game")
                .setResource("character")
                .build();
        Assert.assertEquals("game,character", searchRequest.getResources());
    }

    @Test
    public void setFieldNullField() {
        SearchRequest searchRequest = new SearchRequest.Builder().setField(null).build();
        Assert.assertEquals("", searchRequest.getFieldList());
    }

    @Test
    public void setFieldMultipleFields() {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .setField("name")
                .setField("id")
                .build();
        Assert.assertEquals("name,id", searchRequest.getFieldList());
    }
}