package com.bec.mqttserver.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {
    private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }


    public static void error(String message) {
        logger.error(message);
    }

}
