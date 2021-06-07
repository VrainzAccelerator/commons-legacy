package com.sondeosglobal.helpers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class LogHelper {

	public static void logError(Logger LOGGER, Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String exceptionDetails = sw.toString();
		LOGGER.error(exceptionDetails);
	}

}
