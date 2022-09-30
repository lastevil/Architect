package ru.gbhw;

import java.io.PrintWriter;
import java.util.HashMap;

public class Header {

    private HashMap<HttpStatus, String[]> headerStatus;
    private PrintWriter output;

    public Header(PrintWriter output) {
        this.output = output;
        init();
    }

    private void init() {
        headerStatus = new HashMap<>();
        headerStatus.put(HttpStatus.NOT_FOUND, new String[]{"HTTP/1.1 404 NOT_FOUND", "<h1>Файл не найден!</h1>"});
        headerStatus.put(HttpStatus.UNAUTHORISED, new String[]{"HTTP/1.1 401 UNAUTHORISED", "<h1>Авторизуйтесь</h1>"});
        headerStatus.put(HttpStatus.BAD_REQUEST, new String[]{"HTTP/1.1 400 BAD_REQUEST", "<h1>Не верный запрос</h1>"});
        headerStatus.put(HttpStatus.OK, new String[]{"HTTP/1.1 200 OK"});
    }

    public void sendHeader(HttpStatus httpErrors) {
        output.println(headerStatus.get(httpErrors)[0]);
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        if (headerStatus.get(httpErrors).length > 1) {
            output.println(headerStatus.get(httpErrors)[1]);
        }
        output.flush();
    }
}
