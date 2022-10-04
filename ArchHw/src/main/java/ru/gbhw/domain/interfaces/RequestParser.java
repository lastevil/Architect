package ru.gbhw.domain.interfaces;

import ru.gbhw.domain.HttpRequest;

import java.util.List;

public interface RequestParser {
    HttpRequest parse(List<String> rawRequest);
}
