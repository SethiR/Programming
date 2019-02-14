import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LogDemo {

    static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);  // static reference to a logger and it can now be used anywhere inside the application

    public static void main(String[] args) {
        logManagerDemo();
    }

    static void logManagerDemo(){
        logger.log(Level.INFO, "My first log message");
        logger.log(Level.INFO, "Another message");
    }
}
