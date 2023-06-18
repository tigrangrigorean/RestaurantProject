package com.ordering_system.service.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorLogger {

    private static final Logger logger = LoggerFactory.getLogger(ErrorLogger.class.getName());

    public static void logError(String message) {
        logger.error(message);
    }
}