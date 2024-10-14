package ir.snappay.walletservice.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomLogger {
    private static org.slf4j.Logger logger= LoggerFactory.getLogger("custom.logger");
    public static Logger get(){
        return logger;
    }
}
