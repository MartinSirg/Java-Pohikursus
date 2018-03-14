package ee.ttu.iti0202.logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");

    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append("[").append(record.getLevel()).append("] - ");
        builder.append(formatMessage(record)).append("~~~[METHOD: ");
        builder.append(record.getSourceMethodName()).append("]");
        //builder.append("\t \t [").append(record.getSourceClassName()).append(".");
        //builder.append(DATE_FORMAT.format(new Date(record.getMillis()))).append(" - ");
        builder.append("\n");
        return builder.toString();
    }

    public String getHead(Handler h) {
        return super.getHead(h);
    }

    public String getTail(Handler h) {
        return super.getTail(h);
    }
}
