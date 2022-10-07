package ru.gbhw.config;

import ru.gbhw.WebServer;

import java.io.IOException;
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
