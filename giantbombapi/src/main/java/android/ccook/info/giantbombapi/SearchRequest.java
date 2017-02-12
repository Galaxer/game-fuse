package android.ccook.info.giantbombapi;

import android.ccook.info.giantbombapi.models.SearchResults;

public class SearchRequest extends APIRequest {

    private final int limit;
    private final String resources;
    private final String fieldList;
    private final String query;
    private Callback<SearchResults> callback;

    public static final String RESOURCE_GAME = "game";

    public static final String FIELD_NAME = "name";


    private SearchRequest(Builder builder) {
        super(builder);
        limit = builder.limit;
        resources = builder.resources;
        query = builder.query;
        fieldList = builder.fieldList;
        callback = builder.callback;
    }

    int getLimit() {
        return limit;
    }

    String getFieldList() {
        return fieldList;
    }

    String getResources() {
        return resources;
    }

    String getQuery() {
        return query;
    }

    Callback<SearchResults> getCallback() {
        return callback;
    }

    public static class Builder extends APIRequest.Builder<SearchRequest, SearchRequest.Builder> {

        private int limit = 10;
        private String resources = "";
        private StringBuilder resourcesBuilder = new StringBuilder();
        private StringBuilder fieldListBuilder = new StringBuilder();
        private String fieldList = "";
        private String query = "";
        private Callback<SearchResults> callback = new Callback<SearchResults>() {
            @Override
            public void onError(Throwable error) {
            }

            @Override
            public void onSuccess(SearchResults searchResults) {
            }
        };

        @Override
        Builder getThis() {
            return this;
        }

        @Override
        public SearchRequest build() {
            resources = resourcesBuilder.toString();
            fieldList = fieldListBuilder.toString();
            return new SearchRequest(this);
        }

        /**
         * Add a string to a string and separate with comma's
         * @param builder String builder to add string to
         * @param string String to add
         * @return String builder with string added or empty
         */
        private StringBuilder addStringToCommaSeparatedString(StringBuilder builder, String string) {
            if (builder == null) {
                builder = new StringBuilder();
            }

            if (string != null) {
                if (builder.length() == 0) {
                    builder.append(string);
                } else {
                    builder.append(",").append(string);
                }
            }
            return builder;
        }

        public SearchRequest.Builder setField(String field) {
            addStringToCommaSeparatedString(fieldListBuilder, field);
            return this;
        }

        public SearchRequest.Builder setLimit(int limit) {
            this.limit = limit;
            return this;
        }

        public SearchRequest.Builder setResource(String resource) {
            addStringToCommaSeparatedString(resourcesBuilder, resource);
            return this;
        }

        public SearchRequest.Builder setQuery(String query) {
            this.query = query;
            return this;
        }

        public SearchRequest.Builder setCallback(Callback<SearchResults> callback) {
            if (callback == null) {
                callback = new Callback<SearchResults>() {
                    @Override
                    public void onError(Throwable error) {
                    }

                    @Override
                    public void onSuccess(SearchResults searchResults) {
                    }
                };
            }
            this.callback = callback;
            return this;
        }
    }
}