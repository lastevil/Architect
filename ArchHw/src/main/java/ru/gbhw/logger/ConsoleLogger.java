package ru.gbhw.logger;

public class ConsoleLogger implements Logger{

    private ConsoleLogger(){}
    public static ConsoleLogger createLogger(){
        return new ConsoleLogger();
    }
    @Override
    public void info(String msg) {
        System.out.println(msg);
    }
}
