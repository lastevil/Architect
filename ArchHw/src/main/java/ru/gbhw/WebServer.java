package ru.gbhw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private static String WWW = "/Users/macbook/IdeaProjects/first-geek-web-server/www";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new HandleRequest(socket,WWW).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}