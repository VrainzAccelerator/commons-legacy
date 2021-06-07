package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vrainz.co.claro.ws.syncsubsc.SyncOrderRelationshipRequest;
import com.vrainz.co.claro.ws.syncsubsc.SyncOrderRelationshipRequestParse;

import com.vrainz.helper.HttpConnectionHelper;
import com.vrainz.helper.httpconnection.content.ContentType;

@SuppressWarnings("restriction")
public class ClaroColombiaSyncSubscriptionHandler implements HttpHandler {
	Logger log = Logger.getLogger(getClass());
    private String hermesURL = "http://localhost:8181";
    private String notifyURL = "";
    private Map<String, String> notificationValues;
    private static final String responsePattern = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <soapenv:Body> <ns3:syncOrderRelationshipResponse xmlns:ns3=\"http://www.csapi.org/schema/parlayx/syncsubscription/v1_0/local\"> <ns3:result> <resultCode>resultCodeValue</resultCode> <resultMessage>resultMessageValue</resultMessage> </ns3:result> </ns3:syncOrderRelationshipResponse> </soapenv:Body> </soapenv:Envelope>";

	public void handle(HttpExchange httpExchange) throws IOException {

		//String response = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <soapenv:Body> <ns3:syncOrderRelationshipResponse xmlns:ns3=\"http://www.csapi.org/schema/parlayx/syncsubscription/v1_0/local\"> <ns3:result> <resultCode>00000000</resultCode> <resultMessage>success</resultMessage> </ns3:result> </ns3:syncOrderRelationshipResponse> </soapenv:Body> </soapenv:Envelope>";
		
		InputStreamReader inputStreamReader = new InputStreamReader(httpExchange.getRequestBody(), "UTF-8");
		
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		SyncOrderRelationshipRequestParse parse = new SyncOrderRelationshipRequestParse();
		String linea = "";
		StringBuffer rq = new StringBuffer("");
		while ((linea = bufferedReader.readLine()) != null) {
			rq.append(linea);
		}
		log.info("Rquest: " + rq.toString());
		SyncOrderRelationshipRequest syncOrderRelationshipRequest = null;
		try {
			syncOrderRelationshipRequest = parse.parseToSyncOrderRelationshipRequest(rq.toString());
		} catch (Exception e1) {
			//Si hay un error de parseo se invoca al response con el mensaje correspondiente
			sendResponse(1000, httpExchange, bufferedReader);
			return;
			
		} 
		Integer resultCode = null;
		try {
			resultCode = hermesUpdate(syncOrderRelationshipRequest);
			
		} catch (Exception e) {
			resultCode = HttpConnectionHelper.getResponseCode();
		}
		
		
		
		sendResponse(resultCode, syncOrderRelationshipRequest.getOperation(), httpExchange, bufferedReader);
		
		
	}
	private void sendResponse(Integer resultCode,  HttpExchange httpExchange, BufferedReader bufferedReader) throws IOException{
		sendResponse(resultCode, null, httpExchange, bufferedReader);
		
	}
	private void sendResponse(Integer resultCode, SyncOrderRelationshipRequest.OPERATION operation, HttpExchange httpExchange, BufferedReader bufferedReader) throws IOException{
		String response = null;
		
		response = getSoapResponse(resultCode, operation);

		OutputStream os = httpExchange.getResponseBody();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
		bw.write(response);

		httpExchange.sendResponseHeaders(200, response.length());
		bw.close();
		bufferedReader.close();

		httpExchange.close();
	}
	
	private String getSoapResponse(Integer httpStatus, SyncOrderRelationshipRequest.OPERATION operation){
		
		String response  = new String(responsePattern);
		
		if(operation != null && !Integer.valueOf(200).equals(httpStatus)){
			if(SyncOrderRelationshipRequest.OPERATION.SUBSCRIBE.equals(operation)){
				httpStatus+=2000;
			}
			if(SyncOrderRelationshipRequest.OPERATION.UNSUBSCRIBE.equals(operation)){
				httpStatus+=3000;
			}
		}
		ResponseValues responseValues = ResponseValues.get(httpStatus);
		
		//Si no se encuentra el  httpStatus asumo que hay un error 500, aunque podría ser otro motivo
		//como un nuevo código desde hermes no contemplado en esta app.
		if(responseValues == null){
			responseValues = ResponseValues.get(500);
		}
			
		response = response.replaceAll("resultCodeValue", responseValues.getCode());
		response = response.replaceAll("resultMessageValue", responseValues.getMessage());

		return response;
		
	}

	private int hermesUpdate(SyncOrderRelationshipRequest syncOrderRelationshipRequest) throws Exception {

		
		String hermesSubscribe = "/api/suscription?ani=aniValue&servicio=serviceCodeValue";
		log.debug("serviceURL: " + hermesURL + hermesSubscribe);
		log.debug("Invocando a Hermes con ani: " + syncOrderRelationshipRequest.getUserID().getID() + " y servicio: " + syncOrderRelationshipRequest.getProductID());

		HashMap<String, String> values = new HashMap<String, String>();
		values.put("aniValue", syncOrderRelationshipRequest.getUserID().getID());
		values.put("serviceCodeValue", syncOrderRelationshipRequest.getProductID());

		String subscribeURL = replaceValues(hermesURL + hermesSubscribe, values);

		log.debug("serviceURL: " + subscribeURL);
		if(syncOrderRelationshipRequest.isToSubscribe()){
			HttpConnectionHelper.doPost(subscribeURL, "", ContentType.APPLICATION_JSON);
			
		}else{
			HttpConnectionHelper.doDelete(subscribeURL, "", ContentType.APPLICATION_JSON);	
		}
		
		return HttpConnectionHelper.getResponseCode();

	}
	
	
	private void notifyApps () throws Exception {
		String mensaje = "{\"servicio\":\"{servicio.codigo}\",\"telco\":\"{telco}\",\"fecha\":\"{fecha.now}\",\"ani\":\"{ani}\",\"nc\":\"{servicio.mt}\",\"tipo\":\"{operacion}\",\"palabra\":\"{servicio.palabra}\"}";
		
		HttpConnectionHelper.doPost(notifyURL, mensaje, ContentType.APPLICATION_JSON);
	}

	private String replaceValues(String template, Map<String, String> values) {
		String newString = template;
		for (String key : values.keySet()) {
			newString = newString.replaceAll(key, values.get(key));
		}
		return newString;
	}
	
	private void initializeNotificationValues(SyncOrderRelationshipRequest syncOrderRelationshipRequest){
		notificationValues = new HashMap<String, String>();
		notificationValues.put("ani", syncOrderRelationshipRequest.getUserID().getID());
		notificationValues.put("servicio.codigo", syncOrderRelationshipRequest.getProductID());
		notificationValues.put("telco", "co.claro");
		Date date = new Date();
		notificationValues.put("fecha.now", date.toString());
		notificationValues.put("servicio.mt", "35112");
		if (syncOrderRelationshipRequest.isToSubscribe()){
			notificationValues.put("operacion", "alta");
		} else {
			notificationValues.put("operacion", "baja");
		}
		notificationValues.put("servicio.palabra", " ");
	}



	}
