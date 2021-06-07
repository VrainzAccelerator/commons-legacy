package com.vrainz.mcpmid.config;

import com.vrainz.utils.config.Config;
import com.vrainz.utils.file.PropertiesFileUtils;

public class MCPMidConfig extends Config {
	private static MCPMidConfig instance; 
	
	private String callbackRedirectURL;
	private String dbillAPIURL;

	public static MCPMidConfig getInstance() {
		if (instance == null) {
			instance = new MCPMidConfig();
		}
		
		return instance;
	}

	@Override
	protected void populateConfig() {
		callbackRedirectURL = PropertiesFileUtils.getStringProperty(pAppConf, "callback.redirect.url", "http://www.google.com");
		setDbillAPIURL(PropertiesFileUtils.getStringProperty(pAppConf, "dbillapi.url", "http://localhost"));
	}

	public String getCallbackRedirectURL() {
		return callbackRedirectURL;
	}

	public void setCallbackRedirectURL(String callbackRedirectURL) {
		this.callbackRedirectURL = callbackRedirectURL;
	}

	public String getDbillAPIURL() {
		return dbillAPIURL;
	}

	public void setDbillAPIURL(String dbillAPIURL) {
		this.dbillAPIURL = dbillAPIURL;
	}
	
}

