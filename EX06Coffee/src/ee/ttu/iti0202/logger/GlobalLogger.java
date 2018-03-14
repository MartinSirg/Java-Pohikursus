package ee.ttu.iti0202.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GlobalLogger {
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setupLogger() {
        MyFormatter myFormatter = new MyFormatter();
        LogManager.getLogManager().reset();
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(myFormatter);
        ch.setLevel(Level.FINER);
        logger.addHandler(ch);
        // file logger here
        logger.setLevel(Level.INFO);
        logger.info("Global Logger is set up.");
    }
}
