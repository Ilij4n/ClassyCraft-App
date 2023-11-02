package raf.dsw.classycraft.app.Loggers;

public class SimpleLoggerFactory {
    public static Logger createLogger(String loggerType) {
        if (loggerType == null) {
            return null;
        }
        if (loggerType.equalsIgnoreCase("Console")) {
            return new ConsoleLogger();
        } else if (loggerType.equalsIgnoreCase("File")) {
            return new FileLogger();
        }
        return null;
    }

}
