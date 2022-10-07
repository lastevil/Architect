package ru.gbhw.domain;

import java.util.Map;

public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    private HttpRequest() {
    }

    public static Builder buildHttpRequest(){
        return new HttpRequest.Builder();
    }
    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public static class Builder {
        private final HttpRequest request;

        public Builder() {
            this.request = new HttpRequest();
        }

        public Builder withMethod(String method) {
            this.request.method = method;
            return this;
        }

        public Builder withPath(String path) {
            this.request.path = path;
            return this;
        }

        public Builder withHeaders(Map<String, String> headers) {
            this.request.headers = headers;
            return this;
        }

        public Builder withBody(String body) {
            this.request.body = body;
            return this;
        }

        public HttpRequest build() {
            return this.request;
        }
    }


}