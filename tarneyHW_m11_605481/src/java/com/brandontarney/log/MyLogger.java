/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandontarney.log;

import java.io.IOException;
import java.util.Formatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Tarney
 */
public class MyLogger {
    static private FileHandler logFile;
    static private SimpleFormatter formatterTxt;

    
    static public void setup(String filePath) throws IOException {
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        
        logger.setLevel(Level.INFO);
        logFile = new FileHandler(filePath);
        logFile.setFormatter(new SimpleFormatter());
        logger.addHandler(logFile);
   
    }
}
