package ru.gbhw.config;

class ConfigFromCli implements Config{
    private final String WWW;
    private final int PORT;

    public ConfigFromCli(String[] args) {
        if(args.length<2){
            throw new IllegalStateException("No parameters specified");
        }
        else{
            WWW = args[0];
            PORT = Integer.parseInt(args[1]);
        }

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
