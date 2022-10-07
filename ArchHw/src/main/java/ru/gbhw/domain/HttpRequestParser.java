package ru.gbhw.domain;

import ru.gbhw.domain.interfaces.RequestParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestParser implements RequestParser {
    private HttpRequestParser(){}

    public static HttpRequestParser createHttpRequestParser(){
        return new HttpRequestParser();
    }

    @Override
    public HttpRequest parse(List<String> rawRequest) {
        Map<String, String> map = new HashMap<>();
        String[] method = rawRequest.get(0).split(" ");
        for (int i = 1; i < rawRequest.size() - 1; i++) {
            String[] header = rawRequest.get(i).split(": ");
            map.put(header[0], header[1]);
        }
        HttpRequest request = HttpRequest.buildHttpRequest()
                .withMethod(method[0])
                .withPath(method[1])
                .withHeaders(map)
                .withBody(rawRequest.get(rawRequest.size() - 1))
                .build();
        return request;
    }
}
