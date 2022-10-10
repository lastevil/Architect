package ru.gbhw.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger implements LoggerPlace {

    private File file;

    private FileLogger() {

    }

    public static FileLogger get() {
        return new FileLogger();
    }

    @Override
    public void write(String msg) {
        file = new File("log/log.txt");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:ms");
        String time = LocalDateTime.now().format(formatter);
        try {
            FileWriter writer = new FileWriter(file.getAbsoluteFile(), true);
            writer.write(time + ": " + msg + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
