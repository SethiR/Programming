import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.logging.*;

public class LogDemo {

    static Logger logger = LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);  // static reference to a logger and it can now be used anywhere inside the application

    public static void main(String[] args) {
        logManagerDemo();
    }

    static void logManagerDemo(){
        logger.log(Level.INFO, "My first log message");
        logger.log(Level.INFO, "Another message");
    }


    // logp allows you to specify the class and method explicityly. log infers it.
    static void logpDemo(){
        logger.logp(Level.ALL, "LogDemo", "logpDemo", "Log message");  // logp supports parameters
    }


    // this method demostrates how you can piece the components of the logger yourself.
    // There are 3 components 1) Logger 2) Handler 3) Formatter
    // You can arrange them as shown below (check slides for diagram)
    static void logComponentsDemo(){
        Logger customLogger = Logger.getLogger("com.sethirajat");  // if this logger does not exist it will be created
        Handler h = new ConsoleHandler();  // using a built in handler which outputs to console.
        Formatter f = new SimpleFormatter(); // using a built in formatter
        h.setFormatter(f);
        customLogger.addHandler(h);
        customLogger.setLevel(Level.ALL);
        customLogger.log(Level.INFO, "We are logging this message");
    }


    // this method demonstrates how you can log to a file
    static void FileHandlerDemo() throws IOException {
        Logger customLogger = Logger.getLogger("com.sethirajat");
        FileHandler h = new FileHandler();  // also can do new FileHandler("%h/myapp_%g.log", 1000, 4) --> pattern for
                                            // file naming, limit, count (check official documentation by going to class or java docs.
        h.setFormatter(new SimpleFormatter());
        customLogger.addHandler(h);

        customLogger.log(Level.INFO, "logging");

    }

}
