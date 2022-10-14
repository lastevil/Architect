package ru.gbhw.service.handle;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpResponse;
import ru.gbhw.domain.HttpStatus;

import java.io.StringReader;

@HttpMethod(name = "NOT_ALLOWED")
public class NotAllowedMethodHandle implements MethodHandler {
    private NotAllowedMethodHandle() {
    }
    public static MethodHandler create(){
        return new NotAllowedMethodHandle();
    }
    @Override
    public HttpResponse handel(String folder, HttpRequest request) {
        return HttpResponse.buildHttpResponse()
                .withStatus(HttpStatus.METHOD_NOT_ALLOWED)
                .withBody(new StringReader("<h1>Метод METHOD NOT ALLOWED</h1>\n"))
                .build();
    }
}
