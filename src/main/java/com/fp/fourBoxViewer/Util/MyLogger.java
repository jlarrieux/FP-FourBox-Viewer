package com.fp.fourBoxViewer.Util;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 * Created by jlarrieux on 5/16/2017.
 */
public class MyLogger {

    private static final Logger log = LogManager.getLogger();

    public static Logger getLogger(){
        return log;
    }


    public static void main(String[] args){

        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
        log.trace("TRACE");
    }

}
