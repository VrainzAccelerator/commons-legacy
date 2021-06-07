package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class MovistarColombiaXMLResponse implements HttpHandler {
	Logger	log = Logger.getRootLogger();
	
	private static String NOVEDADES	= "<?xml version='1.0'?><methodResponse><params><resultcode>0</resultcode><desc>Ok</desc><transactionId>450</transactionId><subscription><subscriptionId>301</subscriptionId><media>WEBSERVICE</media><msisdn>573990000001</msisdn><datetime>20110901</datetime><action>ALTA</action></subscription><subscription><subscriptionId>18</subscriptionId><media>SMSMO</media><msisdn>573900000002</msisdn><datetime>20110902163045</datetime><action>BAJA</action></subscription></params></methodResponse>";
		
	private String[] respuestasPosibles = {NOVEDADES};

	protected Vector<IMesssageListener> listeners = new Vector<IMesssageListener>();
		
	public void handle(HttpExchange t) throws IOException {
		log.info("Nueva request");
		
		String response = getRandomResponse();
	    BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));
	        
	    OutputStream os = t.getResponseBody();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(response);
	        
	        
        t.sendResponseHeaders(200, response.length());
        bw.close();
        br.close();
	        
        t.close();
			
	}
		
	private String getRandomResponse() {
		log.info("getRandomResponse");
		Random rand = new Random();
		String response;
			
		int techo = respuestasPosibles.length;
		int indiceRand = rand.nextInt(techo) + 1;
			
		response = respuestasPosibles[indiceRand - 1];
			
		log.info("Response: " + response);
		return response;
		
	}
		
}



