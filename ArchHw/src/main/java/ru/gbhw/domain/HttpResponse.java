package ru.gbhw.domain;

public class HttpResponse {
    private double httpVersion;
    private int statusCode;
    private String contentType;
    private HttpStatus httpStatus;

    public HttpResponse(double httpVersion, int statusCode, HttpStatus httpStatus, String contentType) {
        this.httpVersion = httpVersion;
        this.statusCode = statusCode;
        this.contentType = contentType;
        this.httpStatus = httpStatus;
    }

    public HttpResponse(HttpStatus httpStatus) {
        this.httpVersion = 1.1;
        this.statusCode = httpStatus.getStatusCode();
        this.httpStatus = httpStatus;
        this.contentType = "Content-Type: text/html; charset=utf-8";
    }

    public double getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(double httpVersion) {
        this.httpVersion = httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getStatusMsg() {
        return httpStatus.name();
    }

    public void setStatusMsg(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}