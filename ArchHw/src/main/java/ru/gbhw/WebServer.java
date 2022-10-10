package ru.gbhw;

import ru.gbhw.config.Config;
import ru.gbhw.config.ConfigFactory;
import ru.gbhw.logger.FileLogger;
import ru.gbhw.logger.Logger;
import ru.gbhw.logger.MyLogger;
import ru.gbhw.service.HandleRequest;
import ru.gbhw.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private final static Logger log = MyLogger.createLogger(FileLogger.get());

    public static void main(String[] args) {
        Config config = ConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPrt())) {
            log.info("Server started at port: "+config.getPrt());

            while (true) {
                Socket socket = serverSocket.accept();
                log.info("New client connected!");
                HandleRequest.createHandleRequest(
                        SocketService.createSocketService(socket),
                        config.getWwwHome()
                ).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}