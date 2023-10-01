package logger;

public class DebugLogger extends Logger {

    public DebugLogger(Logger nextLogger) {
        super(nextLogger);
    }

    public void log(int logLevel, String msg) {
        if (logLevel == DEBUG) {
            System.out.println("DEBUG: " + msg);
        } else {
            super.log(logLevel, msg);
        }
    }
}
