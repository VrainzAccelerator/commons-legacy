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

import com.sondeosglobal.simpleserver.main.SimpleHTTPServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class PersonalCHKAndBillRandomResponseHandler implements HttpHandler {
	Logger	log = Logger.getRootLogger();
	
	private static String CODIGO_0		= "Ret_code=0 Ret_msg=Pedido de Billing Aceptado Ret_type=S Client_tid=20160425135245521056 Rebill=0 TokenC= ";
	private static String CODIGO_004	= "Ret_code=004 Ret_type=T Ret_msg=Transaccion incorrecta. No se pudo enviar el SMS. Client_Tid=23353 ";
	private static String CODIGO_6		= "Ret_code=6 Ret_msg=Credito Insuficiente Ret_type=S Client_tid=20160425135627521064 Rebill=1 TokenC= ";
	private static String CODIGO_49		= "Ret_code=49 Ret_msg=Usuario Bloqueado Ret_type=S Client_tid=20160425134517521774 Rebill=1 TokenC= ";
	private static String CODIGO_201	= "Ret_code=201 Ret_msg=Pedido Aceptado para Cobro Parcial Ret_type=S Client_tid=20160425131214521573 Rebill=1 TokenC= ";
	private static String CODIGO_1001	= "Ret_code=1001 Ret_msg=Error de Sistema Ret_type=T Client_tid=20160425130412521449 Rebill=0 TokenC= ";
	private static String CODIGO_1104	= "Ret_code=1104 Ret_msg=Usuario ya suscripto. Ret_type=P Client_tid=20160425135150521208 Rebill=0 TokenC= ";
	
	private String[] respuestasPosibles = {CODIGO_0,CODIGO_004, CODIGO_6, CODIGO_49, CODIGO_201, CODIGO_1001, CODIGO_1104};

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
