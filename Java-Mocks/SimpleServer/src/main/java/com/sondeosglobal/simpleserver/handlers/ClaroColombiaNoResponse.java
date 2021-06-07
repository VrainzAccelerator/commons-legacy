package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.sondeosglobal.helpers.EncoderHelper;
import com.sondeosglobal.helpers.LogHelper;
import com.sondeosglobal.helpers.PostHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class ClaroColombiaNoResponse implements HttpHandler {
	private static final String PASS_CLARO_COLOMBIA = "Opt1c0m2"; 
	private static final int SENDER_ADDRESS_LEN = 12;
	private static final String SENDER_ADDRESS_TEL = "senderAddress>tel:+";
	private static final String MESSAGE_INDICE_KEY = "<message>Indice</message>";
	private static final int SHORT_NUMBER_LEN = 5;
	private static final String SHORT_NUMBER_KEY = "<smsServiceActivationNumber>tel:57";
	protected static final String SEND_MESSAGE_TEMPLATE = "<soapenv:Envelope xmlns:tns=\"http://www.huawei.com/schema/osg/common/v2_1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:ns0=\"http://www.csapi.org/schema/parlayx/sms/send/v2_2/local\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">   <soapenv:Header>      <wsse:Security>        <wsse:UsernameToken>           <wsse:Username>PA00002117</wsse:Username>            <wsse:Password Type=\"...#PasswordDigest\">{pwdDigest}</wsse:Password>            <wsse:Nonce>{nonce}</wsse:Nonce>            <wsse:Created>{created}</wsse:Created>         </wsse:UsernameToken>      </wsse:Security>      <tns:RequestSOAPHeader>         <AppId>PERU</AppId>         <TransId>e370a385-dea3-4cf8-ab8f-2dfdf36bae84</TransId>         <tns:OA>tel:593985849998</tns:OA>         <tns:FA>tel:593985849998</tns:FA>         <tns:token/>      </tns:RequestSOAPHeader>   </soapenv:Header>   <soapenv:Body>      <ns0:sendSms>         <ns0:addresses>{ani}</ns0:addresses>         <ns0:senderName>57{shortNumber}</ns0:senderName>         <ns0:message>{message}</ns0:message>      </ns0:sendSms>   </soapenv:Body></soapenv:Envelope>";
	Logger log = Logger.getLogger(getClass());
	Map<String, String> indiceResponses;
	
	
	public ClaroColombiaNoResponse() {
		initIndiceResponses();
	}

	protected void initIndiceResponses() {
		indiceResponses = new HashMap<String, String>();
		indiceResponses.put("35437", "Nombre de Servicio: Club Yuppi, Valor: $3330, Periodicidad de cobro:Semanal, Linea de Soporte: cc@opticomsa.com.ar/soporte@opticomsa.com.ar ");
		indiceResponses.put("35188", "Nombre de Servicio: Club Wisdom, Valor: $3330, Periodicidad de cobro:Semanal, Linea de Soporte: cc@opticomsa.com.ar/soporte@opticomsa.com.ar ");
		indiceResponses.put("35112", "Nombre de Servicio: Club Desafio Fanatico, Valor: $3330, Periodicidad de cobro:Semanal, Linea de Soporte: cc@opticomsa.com.ar/soporte@opticomsa.com.ar ");
		indiceResponses.put("35677", "Nombre de Servicio: Club PreMatchGame, Valor: $3330, Periodicidad de cobro:Semanal, Linea de Soporte: cc@opticomsa.com.ar/soporte@opticomsa.com.ar ");

	}
	
	
	
	
	public void handle(HttpExchange t) throws IOException {
		
		String response = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));

        String linea = "";
        StringBuffer rq= new StringBuffer("");
        while ((linea = br.readLine()) != null) {
        	rq.append(linea);
        }
        log.info("Rquest: " + rq.toString());
        
        OutputStream os = t.getResponseBody();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(response);
        
        t.sendResponseHeaders(200, response.length());
        bw.close();
        br.close();
        
        int pos = rq.toString().indexOf(SHORT_NUMBER_KEY) + SHORT_NUMBER_KEY.length();
        String shortNumber = rq.substring(pos, pos + SHORT_NUMBER_LEN);
        String messageToSend = null;
        pos = rq.toString().indexOf(SENDER_ADDRESS_TEL) + SENDER_ADDRESS_TEL.length();
        String ani = rq.substring(pos, pos+ SENDER_ADDRESS_LEN);
        if(org.apache.commons.lang3.StringUtils.containsIgnoreCase(rq.toString(), MESSAGE_INDICE_KEY)) {
        	messageToSend = indiceResponses.get(shortNumber);
        }
        if(messageToSend != null){
        	String params = SEND_MESSAGE_TEMPLATE;
        	String nonce =  EncoderHelper.generateNonce();
        	params = params.replace("{nonce}", nonce);
        	
        	SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        	isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	String date = isoFormat.format(new Date());
        	params = params.replace("{created}", date);
        	String pass = PASS_CLARO_COLOMBIA;
        	try {
				params = params.replace("{pwdDigest}", EncoderHelper.GenPwDgstForClaroColombia(nonce, date, pass));
			} catch (NoSuchAlgorithmException e1) {
				log.error(e1.getMessage());
				LogHelper.logError(log, e1);
			}
            params = params.replace("{ani}", ani);
            params = params.replace("{message}", messageToSend);
            params = params.replace("{shortNumber}", shortNumber);
            log.info("Post: " + params);
            try {
            	//https://200.95.168.211:443/asg/services/SendSmsService
    			PostHelper.sendPost("https://200.95.168.211:443/asg/services/SendSmsService", params, "text");
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
        
        t.close();
	}
	

}