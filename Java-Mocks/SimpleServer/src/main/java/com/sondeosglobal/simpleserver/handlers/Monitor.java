package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;


public class Monitor implements IMesssageListener {
	private Logger	log = Logger.getLogger(this.getClass());

	protected Map<String, Date> pedidos = new HashMap<String, Date>();
	private long maxResponsTime;
	
	public boolean allReady() {
		return pedidos.isEmpty();
	}
	
	public void addPedido(String ani) {
		pedidos.put(ani, Calendar.getInstance().getTime());
	}
	
	public long processRespuesta(String ani) {
		long tiempoRespuesta = 0;
		long t1 = System.currentTimeMillis();
		Date pedido = pedidos.get(ani);
		long t2 = System.currentTimeMillis();
		
		log.info("ani: " + ani + " encontrado en: " + (t2 - t1) +" ms");
		log.info("tamaño del jashmap: " + pedidos.size() );
		
		if(pedido != null) {
			log.info(ani + ".pedido.getTime() " + pedido.getTime());
			tiempoRespuesta = Calendar.getInstance().getTimeInMillis() - pedido.getTime();
		}
		pedidos.remove(ani);
		log.info("Tiempo de respuesta para: " + ani + " " + tiempoRespuesta + "ms");
		return tiempoRespuesta;
	}
	
	public void notify(HttpExchange t) {
		String ani = findAni(t.getRequestBody());
		long responseTime = processRespuesta(ani);
		if (responseTime > getMaxResponsTime()) {
			setMaxResponsTime(responseTime); 
		}

	}

	private String findAni(InputStream requestBody) {
		String ani = "";
		try {
			long t1 = System.currentTimeMillis();
			BufferedReader br = new BufferedReader(new InputStreamReader(requestBody, "UTF-8"));
			String str = readBigStringIn(br);
			//MSISDN=549955557626&USR=
//			log.info( "str: " + str );
			int msisdn	= str.indexOf("MSISDN=") + 7;
			int usr		= str.indexOf("&USR=");
			ani = str.substring(msisdn, usr).trim();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ani;
	}

	
	public String readBigStringIn(BufferedReader buffIn) throws IOException {
	    StringBuilder everything = new StringBuilder();
	    String line;
	    while( (line = buffIn.readLine()) != null) {
	       everything.append(line);
	    }
	    return everything.toString();
	}

	public long getMaxResponsTime() {
		return maxResponsTime;
	}

	public void setMaxResponsTime(long maxResponsTime) {
		this.maxResponsTime = maxResponsTime;
	}
	
	public int getPedidosSize() {
		return this.pedidos.size();
	}
	
}

