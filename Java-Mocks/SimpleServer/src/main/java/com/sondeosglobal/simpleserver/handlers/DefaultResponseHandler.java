package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.sondeosglobal.conf.Configuracion;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class DefaultResponseHandler implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {
		Configuracion conf = Configuracion.getInstance();
        
		String response = conf.getRespuestaDefault();
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));
        
        OutputStream os = t.getResponseBody();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(response);
        
        
        t.sendResponseHeaders(200, response.length());
        bw.close();
        br.close();
		
	}

}
