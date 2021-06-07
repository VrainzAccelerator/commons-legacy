package com.sondeosglobal.simpleserver.handlers;

import java.io.IOException;
import java.io.OutputStream;

import com.sondeosglobal.helpers.PostHelper;
import com.sondeosglobal.simpleserver.monitor.MessageMonitor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class PegadaInicioHandler implements HttpHandler{

	public void handle(HttpExchange t) throws IOException {

		//URL fronteral
		String url			= "http://192.168.10.6/service.php";
		String params		= "<order> <auth> <usuario>procesadorSMS</usuario> <password>123</password> </auth> <service> <provision>ProcesadorSMS</provision> <operacion>procesar</operacion> </service> <options /> <parameters> <sms> <id>3875900787</id> <servicetype /> <wappush>0</wappush> <sourceaddress>541164792263</sourceaddress> <destaddress>7979</destaddress> <replaceifpresentflag>0</replaceifpresentflag> <shortmessage>TRIVIASF4RSUSCRIPCION</shortmessage> <datosadjuntos>0</datosadjuntos> <messagepayloadpath>/</messagepayloadpath> <scheduledeliverytime /> <validityperiod /> <esmclass>0</esmclass> <protocolid>0</protocolid> <priorityflag>0</priorityflag> <registereddelivery>0</registereddelivery> <datacoding>0</datacoding> <smdefaultmsgid>0</smdefaultmsgid> <estado>0</estado> <procesado>1</procesado> <dateenviado>2000-01-01 00:00:00</dateenviado> <date>2013-07-25 15:30:58</date> <msgid>x</msgid> <entid /> <esmeid>22</esmeid> <prioridadenvio>9</prioridadenvio> <procid>859</procid> <io>I</io> <keyword /> <medio /> <fact>0</fact> <sesion_id>0</sesion_id> <payload_type>0</payload_type> <dest_network_type /> <source_network_type /> <destination_port>0</destination_port> <source_port>0</source_port> <message_payload>0</message_payload> <delivery_failure_reason>0</delivery_failure_reason> <message_state>0</message_state> <api>1</api> <processkey>Any</processkey> <clase>fronteraPHP</clase> </sms> </parameters> </order>";
		String contentType	= "text";
		
		// hace el post
		try {
			PostHelper.sendPost(url, params, contentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//se subscribe
		MessageMonitor.getInstance().notifyAllObservers(t);
		
		//devuelve resultado
        String response = "Pegada OK";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
        
	}

}
