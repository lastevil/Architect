package ru.gbhw;

import ru.gbhw.logger.ConsoleLogger;
import ru.gbhw.logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private static final int PORT = 8080;
    private static String WWW = "/Users/macbook/IdeaProjects/first-geek-web-server/www";
    private static Logger log = new ConsoleLogger();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.info("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                log.info("New client connected!");
                new HandleRequest(new SocketService(socket),WWW).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}