package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class OoredooConsentGatewayMockServiceHandler implements HttpHandler {

	Logger	log = Logger.getRootLogger();
	
	public void handle(HttpExchange t) throws IOException {
		
		log.info("Nueva request");
			
		String response = "response";
	    BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));
	        
	    OutputStream os = t.getResponseBody();
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	    bw.write(response);
	        
	    t.sendResponseHeaders(200, response.length());
	    bw.close();
	    br.close();
	        
	    t.close();

	}
}
