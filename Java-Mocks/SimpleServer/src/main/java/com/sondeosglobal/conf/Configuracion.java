package com.sondeosglobal.conf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.sondeosglobal.utils.anis.AniGenerator;
import com.sondeosglobal.utils.file.PropertiesFileUtils;

public class Configuracion {

	private static Configuracion	instance;

	protected String			confFileName		= "config.properties";
	private Properties			pAppConf			= null;
	private String				respuestaDefault	= null;
	private List<String>		lineas				= new ArrayList<String>();
	
	private int puerto;

	public static Configuracion getInstance() {
		if (instance == null) {
			instance = new Configuracion();
		}
		return instance;
	}

	private Configuracion() {
		loadProperties();
		populateConfig();
	}

	private void loadProperties() {
		try {
			pAppConf = PropertiesFileUtils.readProperties(confFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void populateConfig() {
		setRespuestaDefault(PropertiesFileUtils.getStringProperty(pAppConf, "respuestaDefault", "Ok"));
		setPuerto(PropertiesFileUtils.getIntProperty(pAppConf, "server.port", 8000));
//		String nrosLinea = PropertiesFileUtils.getStringProperty(pAppConf, "lineas", null);
//		String[] lineasToken = nrosLinea.split(",");
//
//		for (String nroLinea : lineasToken) {
//			this.getLineas().add(nroLinea);
//		}
		
		AniGenerator gen = new AniGenerator();
//		this.lineas = gen.get500KAnis();
		this.lineas = gen.getAnis(500000);

	}

	public String getRespuestaDefault() {
		return respuestaDefault;
	}

	public void setRespuestaDefault(String respuestaDefault) {
		this.respuestaDefault = respuestaDefault;
	}

	public List<String> getLineas() {
		return lineas;
	}

	public void setLineas(List<String> lineas) {
		this.lineas = lineas;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getProperty(String key){
		return pAppConf.getProperty(key);
	}

}