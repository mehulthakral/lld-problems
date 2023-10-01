package logger;

public class Logger {

    Logger nextLogger;

    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int DEBUG = 3;

    public Logger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int logLevel, String msg) {
        if (nextLogger != null) {
            nextLogger.log(logLevel, msg);
        } else {
            System.out.println("Incorrect Logging Level specified");
        }
    }

}
