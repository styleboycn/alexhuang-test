package com.alexhuang;

//import java.util.logging.LogManager;-->this is in rt.jar
//import org.apache.log4j.LogManager;-->this is in log4j-1.2.17.jar
import org.apache.logging.log4j.LogManager;//->this class in log4j-api-2.8.2.jar
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("Hello World!");
		logger.error("Hello World!");
		logger.warn("Hello World!");
	}
}
