package ru.gbhw.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

class ConfigFromFile implements Config{
    private final String WWW;
    private final int PORT;

    public ConfigFromFile(String path) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            throw new NullPointerException("Неверный путь к файлу");
        }
        WWW = properties.getProperty("www.path");
        Path serverDir = Paths.get(WWW);
        if (!serverDir.toFile().exists()) {
            try {
                Files.createDirectories(serverDir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        PORT = Integer.parseInt(properties.getProperty("port"));
    }

    @Override
    public String getWwwHome() {
        return WWW;
    }

    @Override
    public int getPrt() {
        return PORT;
    }
}
