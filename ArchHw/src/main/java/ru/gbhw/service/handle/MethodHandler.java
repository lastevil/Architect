package ru.gbhw.service.handle;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpResponse;

public interface MethodHandler {

    HttpResponse handel(String folder, HttpRequest request);
}
