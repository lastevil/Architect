package ru.gbhw.service.handle;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpResponse;
import ru.gbhw.domain.HttpStatus;

import java.io.StringReader;

@HttpMethod(name = "DELETE")
public class DeleteMethodHandle implements MethodHandler {
    private DeleteMethodHandle() {
    }
    public static MethodHandler create(){
        return new DeleteMethodHandle();
    }
    @Override
    public HttpResponse handel(String folder, HttpRequest request) {
        //Path path = Paths.get(folder, request.getPath()); для работы с запросом
        return HttpResponse.buildHttpResponse()
                .withStatus(HttpStatus.OK)
                .withBody(new StringReader("<h1>Метод DELETE</h1>\n"))
                .build();
    }
}
