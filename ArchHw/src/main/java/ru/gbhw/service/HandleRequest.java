package ru.gbhw.service;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpRequestParser;
import ru.gbhw.domain.HttpResponse;
import ru.gbhw.domain.HttpStatus;
import ru.gbhw.logger.ConsoleLogger;
import ru.gbhw.logger.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandleRequest extends Thread {

    private SocketService socketService;
    private String WWW;

    private Logger log = ConsoleLogger.createLogger();

    private HandleRequest(SocketService socketService, String folder) {
        this.socketService = socketService;
        this.WWW = folder;
    }

    public static HandleRequest createHandleRequest(SocketService socketService, String folder) {
        return new HandleRequest(socketService, folder);
    }

    @Override
    public void run() {
        HttpRequest request = HttpRequestParser
                .createHttpRequestParser()
                .parse(socketService.readRequest());

        Path path = Paths.get(WWW, request.getPath());
        if (!Files.exists(path)) {
            socketService.writeResponse(
                    HttpResponse.buildHttpResponse()
                            .withStatus(HttpStatus.NOT_FOUND)
                            .withBody(new StringReader("<h1>Файл не найден!</h1>\n"))
                            .build()
            );
            return;
        }
        try {
            socketService.writeResponse(
                    HttpResponse.buildHttpResponse()
                            .withStatus(HttpStatus.OK)
                            .withBody(Files.newBufferedReader(path))
                            .build()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Client disconnected!");
    }
}

