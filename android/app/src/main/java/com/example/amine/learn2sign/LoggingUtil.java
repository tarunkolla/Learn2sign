package com.example.amine.learn2sign;

import android.os.Environment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

public class LoggingUtil{

    private Logger logger;

    public LoggingUtil(Class cls){

        //this.checkExternalStorageWritePermission()
        final LogConfigurator logConfigurator = new LogConfigurator();
        logConfigurator.setFileName(Environment.getExternalStorageDirectory().toString() + File.separator + "Learn2Sign/log/file.log");
        logConfigurator.setRootLevel(Level.ALL);
        logConfigurator.setLevel("org.apache", Level.ALL);
        logConfigurator.setUseFileAppender(true);
        logConfigurator.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
        logConfigurator.setMaxFileSize(1024 * 1024 * 5);
        logConfigurator.setImmediateFlush(true);
        logConfigurator.configure();
        logger = Logger.getLogger(cls);
    }

    public void logClick(String elementType, String elementText){

        logger.info("Click Event : "+elementType+" - "+elementText);
    }

}
