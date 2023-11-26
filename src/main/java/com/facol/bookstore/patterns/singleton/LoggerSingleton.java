package com.facol.bookstore.patterns.singleton;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerSingleton {

    private static Logger logger;

    private LoggerSingleton(){
        logger = Logger.getLogger("MyLogger");
        logger.setLevel(Level.INFO);
    }

    public static Logger getLogger(){
        if(logger == null){
            synchronized (LoggerSingleton.class){
                if(logger == null){
                    new LoggerSingleton();
                }
            }
        }

        return logger;
    }
}
