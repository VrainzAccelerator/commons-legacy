package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class EchoHandler implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {
        String response = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));
        
        OutputStream os = t.getResponseBody();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        String linea = null;
        
        while ((linea = br.readLine()) != null) {
        	System.out.println(linea);
        	response = response + linea;
        	bw.write(linea);
        }
        
        t.sendResponseHeaders(200, response.length());
        bw.close();
        br.close();
    }

}
