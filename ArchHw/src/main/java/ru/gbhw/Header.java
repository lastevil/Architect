package ru.gbhw;

import java.io.PrintWriter;
import java.util.HashMap;

public class Header {
    private PrintWriter output;
    private Status status;

    public Header(PrintWriter output) {
        this.output = output;
        status = new Status();
    }

    public void sendHeader(HttpStatus httpErrors) {
        output.println(status.getStatus(httpErrors));
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        if (status.existStatusMessage(httpErrors)) {
            output.println(status.getStatusMessage(httpErrors));
        }
        output.flush();
    }
}
