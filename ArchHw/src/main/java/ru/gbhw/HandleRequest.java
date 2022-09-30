package ru.gbhw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HandleRequest extends Thread {

    private Socket socket;
    private String WWW;

    public HandleRequest(Socket socket, String address) {
        this.socket = socket;
        this.WWW = address;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter output = new PrintWriter(socket.getOutputStream())
        ) {
            while (!input.ready()) ;
            String filePath = getFilePathFromInput(input);
            Path path = Paths.get(WWW, filePath);
            if (!Files.exists(path)) {
                new Header(output).sendHeader(HttpStatus.NOT_FOUND);
                return;
            }
            new Header(output).sendHeader(HttpStatus.OK);
            Files.newBufferedReader(path).transferTo(output);
            System.out.println("Client disconnected!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilePathFromInput(BufferedReader bufferedReader) throws IOException {
        String firstLine = bufferedReader.readLine();
        String[] parts = firstLine.split(" ");
        System.out.println(firstLine);
        while (bufferedReader.ready()) {
            System.out.println(bufferedReader.readLine());
        }
        return parts[1];
    }
}

