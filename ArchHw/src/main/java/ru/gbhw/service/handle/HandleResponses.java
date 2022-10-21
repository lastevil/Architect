package ru.gbhw.service.handle;

import org.reflections.Reflections;
import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HandleResponses {
    private final Map<String, MethodHandler> RESPONSE_MAP = new HashMap<>();

    private HandleResponses() {
        Reflections reflections = new Reflections("ru.gbhw.service.handle");
        Set<Class<?>> handles = reflections
                .getTypesAnnotatedWith(HttpMethod.class);

        for (Class<?> clazz : handles) {
            String myAnnotation = clazz.getDeclaredAnnotation(HttpMethod.class).name();
            try {
                RESPONSE_MAP.put(myAnnotation, (MethodHandler) clazz.getDeclaredMethod("create").invoke(null));
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static HandleResponses create() {
        return new HandleResponses();
    }

    public HttpResponse createResponse(String folder, HttpRequest request) {
        if (!RESPONSE_MAP.keySet().contains(request.getMethod())) {
            return RESPONSE_MAP.get("NOT_ALLOWED").handel(folder, request);
        }
        return RESPONSE_MAP.get(request.getMethod()).handel(folder, request);
    }
}
