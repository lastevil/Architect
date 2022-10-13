package ru.gbhw.service;

import ru.gbhw.domain.HttpHeader;
import ru.gbhw.domain.HttpResponse;
import ru.gbhw.logger.ConsoleLogger;
import ru.gbhw.logger.Logger;
import ru.gbhw.logger.MyLogger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SocketService implements Closeable {
    private static final Logger logger = MyLogger.createLogger(ConsoleLogger.get());
    private final Socket socket;

    SocketService(Socket socket) {
        this.socket = socket;
    }

    public static SocketService createSocketService(Socket socket) {
        return new SocketService(socket);
    }

    public List<String> readRequest() {
        try {
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), StandardCharsets.UTF_8));

            while (!input.ready()) ;

            List<String> request = new ArrayList<>();
            while (input.ready()) {
                String line = input.readLine();
                logger.info(line);
                request.add(line);
            }
            return request;
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void writeResponse(HttpResponse httpResponse) {
        try {
            PrintWriter output = new PrintWriter(socket.getOutputStream());
            output.print(HttpHeader.createHttpHeader().serialize(httpResponse));
            if (httpResponse.getBody() != null) {
                httpResponse.getBody().transferTo(output);
            }
            output.flush();
            output.close();
            close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void close() throws IOException {
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}
