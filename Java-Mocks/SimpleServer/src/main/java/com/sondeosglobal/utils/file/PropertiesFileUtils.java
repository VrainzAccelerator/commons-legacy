package com.sondeosglobal.utils.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileUtils {

    /**
     * Read properties.
     *
     * @param filename the filename
     * @return the properties
     * @throws IOException Signals that an I/O exception has occurred.
     */
    static public Properties readProperties(String filename) throws IOException{
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream(filename);
        props.load(stream);
        return props;
    }
    

	/**
	 * Gets the string property.
	 *
	 * @param props the props
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the string property
	 */
	public static String getStringProperty(Properties props, String key, String defaultValue) {
		String rtn = props.getProperty(key, defaultValue);
		return rtn;
	}

	/**
	 * Gets the int property.
	 *
	 * @param props the props
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the int property
	 */
	public static int getIntProperty(Properties props, String key, int defaultValue) {
		String sDefault = String.valueOf(defaultValue);
		int iRtn = Integer.parseInt(props.getProperty(key, sDefault));

		return iRtn;
	}
	
	/**
	 * Gets the long property.
	 *
	 * @param props the props
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the long property
	 */
	public static long getLongProperty(Properties props, String key, long defaultValue) {
		String sDefault = String.valueOf(defaultValue);
		long lRtn = Long.parseLong(props.getProperty(key, sDefault));

		return lRtn;
	}
	
	

	/**
	 * Gets the boolean property.
	 *
	 * @param props the props
	 * @param key the key
	 * @param defaultValue the default value
	 * @return the string property
	 */
	public static boolean getBooleanProperty(Properties props, String key, String defaultValue) {
		boolean rtn = Boolean.parseBoolean(props.getProperty(key, defaultValue));
		return rtn;
	}
}