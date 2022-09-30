package ru.gbhw;

import java.util.HashMap;

public class Status {
    private HashMap<HttpStatus, String[]> headerStatus;

    public Status() {
        headerStatus = new HashMap<>();
        headerStatus.put(HttpStatus.NOT_FOUND, new String[]{"HTTP/1.1 404 NOT_FOUND", "<h1>Файл не найден!</h1>"});
        headerStatus.put(HttpStatus.UNAUTHORISED, new String[]{"HTTP/1.1 401 UNAUTHORISED", "<h1>Авторизуйтесь</h1>"});
        headerStatus.put(HttpStatus.BAD_REQUEST, new String[]{"HTTP/1.1 400 BAD_REQUEST", "<h1>Не верный запрос</h1>"});
        headerStatus.put(HttpStatus.OK, new String[]{"HTTP/1.1 200 OK"});
    }

    public String getStatus(HttpStatus status) {
        return headerStatus.get(status)[0];
    }

    public String getStatusMessage(HttpStatus status) {
        if (existStatusMessage(status)) {
            return headerStatus.get(status)[1];
        }
        return "";
    }

    public boolean existStatusMessage(HttpStatus status){
        if (headerStatus.get(status).length > 1){
            return true;
        }
        return false;
    }
}
