package ee.ttu.iti0202.logger;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.FileHandler;

public class GlobalLogger {
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setupLogger() {
        MyFormatter myFormatter = new MyFormatter();
        LogManager.getLogManager().reset();
        ConsoleHandler ch = new ConsoleHandler();
        ch.setFormatter(myFormatter);
        ch.setLevel(Level.INFO);
        logger.addHandler(ch);

        // file logger here
        FileHandler fh;
        try {
            fh = new FileHandler("coffeeApp-log.%u.%g.txt",
                    true);
            fh.setFormatter(myFormatter);
            ch.setLevel(Level.FINEST);
            logger.addHandler(fh);
            logger.info("File handler is set up successfully");
        } catch (IOException e) {
            logger.warning("File handler not set up");
        }
        logger.info("Global Logger is set up.");
    }
}
