package ru.gbhw.domain;

import ru.gbhw.domain.interfaces.RequestParser;

import java.util.List;

public class HttpRequestParser implements RequestParser {

    @Override
    public HttpRequest parse(List<String> rawRequest) {
        HttpRequest request = new HttpRequest();
        String[] method = rawRequest.get(0).split(" ");
        request.setMethod(method[0]);
        request.setPath(method[1]);
        for (int i = 1; i < rawRequest.size()-1; i++) {
            String[] header = rawRequest.get(i).split(": ");
            request.setHeaders(header[0],header[1]);
        }
        request.setBody(rawRequest.get(rawRequest.size()-1));
        return request;
    }
}
