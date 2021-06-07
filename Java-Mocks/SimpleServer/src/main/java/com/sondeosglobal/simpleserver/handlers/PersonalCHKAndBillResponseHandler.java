package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.Vector;

import com.sondeosglobal.conf.Configuracion;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class PersonalCHKAndBillResponseHandler implements HttpHandler {
	protected Vector<IMesssageListener> listeners = new Vector<IMesssageListener>();
	
	public void addListener(IMesssageListener newListener) {
		listeners.add(newListener);
	}
	
	public void handle(HttpExchange t) throws IOException {
		notifyListeners(t);
		
		String response = Configuracion.getInstance().getRespuestaDefault();
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));
        
        OutputStream os = t.getResponseBody();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(response);
        
        t.sendResponseHeaders(200, response.length());
        bw.close();
        br.close();
        
        t.close();
		
	}
	
	private void notifyListeners(HttpExchange t) {
		for (Iterator<IMesssageListener> iterator = listeners.iterator(); iterator.hasNext();) {
			IMesssageListener listener = (IMesssageListener) iterator.next();
			listener.notify(t);
		}
	}
}
