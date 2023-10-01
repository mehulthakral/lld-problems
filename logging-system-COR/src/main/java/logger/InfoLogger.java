package logger;

public class InfoLogger extends Logger {

    public InfoLogger(Logger nextLogger) {
        super(nextLogger);
    }

    public void log(int logLevel, String msg) {
        if (logLevel == INFO) {
            System.out.println("INFO: " + msg);
        } else {
            super.log(logLevel, msg);
        }
    }

}
