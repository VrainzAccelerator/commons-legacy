package com.sondeosglobal.simpleserver.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class TimeCheckObserver extends AbstractMessageObserver {

	@Override
	public void messageArrived(HttpExchange httpExchange) {
		//TODO checkear si es PersonalChkAndBill
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println("llego un mensaje: " +  br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

