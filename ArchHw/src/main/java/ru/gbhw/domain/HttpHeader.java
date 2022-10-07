package ru.gbhw.domain;

import ru.gbhw.domain.interfaces.ResponseSerializer;

public class HttpHeader implements ResponseSerializer {

    private HttpHeader(){}

    public static HttpHeader createHttpHeader(){
        return new HttpHeader();
    }
    @Override
    public String serialize(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/"+httpResponse.getHttpVersion()+" ");
        sb.append(httpResponse.getStatusCode()+" ");
        sb.append(httpResponse.getStatusMsg()+"\n");
        sb.append(httpResponse.getContentType()+"\n\n");
        return sb.toString();
    }
}
