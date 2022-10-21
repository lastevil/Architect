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
    private final HandleResponses responses;
    private final Logger log = MyLogger.createLogger(ConsoleLogger.get());

    private HandleRequest(SocketService socketService, String folder, HandleResponses responses) {
        this.socketService = socketService;
        this.WWW = folder;
        this.responses = responses;
    }

    public static HandleRequest createHandleRequest(SocketService socketService, String folder, HandleResponses responses) {
        return new HandleRequest(socketService, folder, responses);
    }

    @Override
    public void run() {
        HttpRequest request = HttpRequestParser
                .createHttpRequestParser()
                .parse(socketService.readRequest());
        socketService.writeResponse(
                responses.createResponse(WWW, request)
        );
        return;
    }
}

