package ru.gbhw.service;

import ru.gbhw.domain.HttpRequest;
import ru.gbhw.domain.HttpRequestParser;
import ru.gbhw.logger.ConsoleLogger;
import ru.gbhw.logger.Logger;
import ru.gbhw.logger.MyLogger;
import ru.gbhw.service.handle.HandleResponses;

public class HandleRequest extends Thread {

    private final SocketService socketService;
    private final String WWW;
    private final Logger log = MyLogger.createLogger(ConsoleLogger.get());

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

        socketService.writeResponse(
                HandleResponses.create().createResponse(WWW, request)
        );
        return;
    }
}

