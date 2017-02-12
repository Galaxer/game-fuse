package android.ccook.info.giantbombapi;

import android.text.TextUtils;

import java.util.List;

import okhttp3.HttpUrl;

class APIRequest {

    private final long timeoutSeconds;
    private String baseUrl;
    private final long maxCacheDirSize;
    static final String RESPONSE_FORMAT_JSON = "json";

    APIRequest(Builder builder) {
        timeoutSeconds = builder.timeoutSeconds;
        baseUrl = builder.baseUrl;
        maxCacheDirSize = builder.maxCacheDirSize;
    }

    long getTimeoutSeconds() {
        return timeoutSeconds;
    }

    String getBaseUrl() {
        return baseUrl;
    }

    long getMaxCacheDirSize() {
        return maxCacheDirSize;
    }

    String buildCommaSeparatedString(List<String> stringList) {
        return TextUtils.join(",", stringList);
    }

    static abstract class Builder<T, B> {

        private long timeoutSeconds = 20;
        private String baseUrl = "http://www.giantbomb.com/api/";
        private long maxCacheDirSize = 10;

        abstract B getThis();
        abstract public T build();

        /**
         * Set the timeout of the request. 0 means no timeout.
         * @param timeoutSeconds timeout in seconds
         * @return Builder
         */
        public B setTimeoutSeconds(long timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
            return getThis();
        }

        public B setBaseUrl(String baseUrl) {
            if (baseUrl == null || HttpUrl.parse(baseUrl) == null) {
                throw new IllegalArgumentException("Illegal URL: " + baseUrl);
            }
            this.baseUrl = baseUrl;
            return getThis();
        }

        /**
         * Set max cache directory size.
         * @param maxCacheDirSize Max cache directory size in MB.
         * @return Builder
         */
        public B setMaxCacheDirSize(long maxCacheDirSize) {
            this.maxCacheDirSize = maxCacheDirSize;
            return getThis();
        }
   }
}