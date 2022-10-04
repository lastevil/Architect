package ru.gbhw;

import ru.gbhw.domain.*;
import ru.gbhw.logger.ConsoleLogger;
import ru.gbhw.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HandleRequest extends Thread {

    private SocketService socketService;
    private String WWW;

    private Logger log = new ConsoleLogger();

    public HandleRequest(SocketService socketService, String folder) {
        this.socketService = socketService;
        this.WWW = folder;
    }

    @Override
    public void run() {


        // TODO use here implementation of interface RequestParser
        HttpRequest request = new HttpRequestParser().parse(socketService.readRequest());
        Path path = Paths.get(WWW,request.getPath());
        if (!Files.exists(path)) {
            socketService.writeResponse(new HttpHeader().serialize(new HttpResponse(HttpStatus.NOT_FOUND)),
                    new StringReader("<h1>Файл не найден!</h1>\n")
            );
            return;
        }
        try {
            //use implementation of interface ResponseSerializer
            socketService.writeResponse(new HttpHeader().serialize(new HttpResponse(HttpStatus.OK)),
                    Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Client disconnected!");
    }
}

