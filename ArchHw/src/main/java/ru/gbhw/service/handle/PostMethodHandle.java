package ru.gbhw.service.handle;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpResponse;
import ru.gbhw.domain.HttpStatus;

import java.io.StringReader;

@HttpMethod(name = "POST")
public class PostMethodHandle implements MethodHandler{
    private PostMethodHandle(){}
    public static MethodHandler create(){
        return new PostMethodHandle();
    }
    @Override
    public HttpResponse handel(String folder, HttpRequest request) {
        // Path path = Paths.get(folder, request.getPath());
        return HttpResponse.buildHttpResponse()
                .withStatus(HttpStatus.OK)
                .withBody(new StringReader("<h1>Метод POST</h1>\n"))
                .build();
    }
}
