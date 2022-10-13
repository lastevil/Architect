package ru.gbhw.service.handle;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpResponse;
import ru.gbhw.domain.HttpStatus;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@HttpMethod(name = "GET")
public class GetMethodHandle implements MethodHandler {

    GetMethodHandle(){}

    @Override
    public HttpResponse handel(String folder, HttpRequest request) {
        Path path = Paths.get(folder, request.getPath());
        if (!Files.exists(path) || path.toFile().isDirectory()) {
            return HttpResponse.buildHttpResponse()
                    .withStatus(HttpStatus.NOT_FOUND)
                    .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                    .build();
        }
        try {
            return HttpResponse.buildHttpResponse()
                    .withStatus(HttpStatus.OK)
                    .withBody(Files.newBufferedReader(path))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
