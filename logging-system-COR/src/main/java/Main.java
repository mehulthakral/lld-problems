import logger.DebugLogger;
import logger.ErrorLogger;
import logger.InfoLogger;
import logger.Logger;

public class Main {
    public static void main(String[] args) {

        Logger logger = new ErrorLogger(new InfoLogger(new DebugLogger(null)));

        logger.log(Logger.INFO, "for your info");
        logger.log(Logger.DEBUG, "debugging this");
        logger.log(Logger.ERROR, "got error");
    }
}
