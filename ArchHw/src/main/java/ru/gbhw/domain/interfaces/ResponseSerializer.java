package ru.gbhw.domain.interfaces;

import ru.gbhw.domain.HttpResponse;

public interface ResponseSerializer {
    String serialize(HttpResponse httpResponse);
}
