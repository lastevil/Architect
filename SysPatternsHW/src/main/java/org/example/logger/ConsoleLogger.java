package org.example.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleLogger implements Logger{
    private ConsoleLogger(){}
    public static ConsoleLogger get(){
        return new ConsoleLogger();
    }
    @Override
    public void info(String msg) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");
        String time = LocalDateTime.now().format(formatter);
        System.out.println(time +": "+ msg);
    }
}
