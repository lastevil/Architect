package ru.gbhw.domain;

import java.io.Reader;

public class HttpResponse {
    private double httpVersion;
    private int statusCode;
    private String contentType;
    private String httpStatus;
    private Reader body;

    private HttpResponse() {
    }
    public static Builder buildHttpResponse(){
        return new HttpResponse.Builder();
    }

    public double getHttpVersion() {
        return httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public String getStatusMsg() {
        return httpStatus;
    }

    public Reader getBody() {
        return body;
    }

    public static class Builder {

        private final HttpResponse response;

        public Builder() {
            response = new HttpResponse();
        }

        public Builder withStatus(HttpStatus httpStatus) {
            this.response.statusCode = httpStatus.getStatusCode();
            this.response.httpStatus = httpStatus.name();
            return this;
        }

        public Builder withHttpVersion(Double httpVersion) {
            this.response.httpVersion = httpVersion;
            return this;
        }

        public Builder withContentType(String contentType) {
            this.response.contentType = contentType;
            return this;
        }

        public Builder withBody(Reader body) {
            this.response.body = body;
            return this;
        }

        public HttpResponse build() {
            if (this.response.contentType == null) {
                withContentType("Content-Type: text/html; charset=utf-8");
            }
            if (this.response.httpVersion == 0) {
                withHttpVersion(1.1);
            }
            if (this.response.httpStatus == null || this.response.statusCode == 0) {
                throw new IllegalStateException("Wrong response!");
            }
            return this.response;
        }
    }
}