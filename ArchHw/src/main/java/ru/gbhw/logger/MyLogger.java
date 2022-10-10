package ru.gbhw.logger;

public class MyLogger implements Logger{
    LoggerPlace logger;

    private MyLogger(LoggerPlace place){
        this.logger = place;
    }

    private MyLogger(){}

    public static MyLogger createLogger(LoggerPlace place){
        return new MyLogger(place);
    }

    @Override
    public void info(String msg) {
        this.logger.write(msg);
    }
}
