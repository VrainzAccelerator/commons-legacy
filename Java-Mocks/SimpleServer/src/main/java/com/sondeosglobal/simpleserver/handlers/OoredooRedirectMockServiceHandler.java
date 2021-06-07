package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class OoredooRedirectMockServiceHandler implements HttpHandler {

	Logger	log = Logger.getRootLogger();
	
	public void handle(HttpExchange t) throws IOException {
		log.info("Recibimos un nuevo redirect");
		
        writeHTMLRedirect(t);
		
		String response = "SUCCESS";
	    BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));
	        
	    OutputStream os = t.getResponseBody();
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
	    bw.write(response);
	        
	    t.sendResponseHeaders(200, response.length());
	    bw.close();
	    br.close();
	        
	    t.close();

	}

	private void writeHTMLRedirect(HttpExchange t) {
		BufferedWriter writer = null;
        try {
            File logFile = new File("lastredirect.html");

            // This will output the full path where the file will be written to...
            log.info(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write("<html><head></head><body>");
            writer.write("<h3 style='font-family: sans-serif;font-size: 18px'>Ultimo redirect recibido: </h3>");
            writer.write("<div style='font-family: sans-serif;font-size: 14px;background: lightblue; border:1px dotted  blue; padding: 10px; word-wrap: break-word;'>");
            writer.write(t.getRequestURI().getQuery());
            writer.write("</div>");
            writer.write("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
	}
}
