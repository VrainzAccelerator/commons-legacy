package com.sondeosglobal.simpleserver.handlers;


import com.sondeosglobal.utils.RandomUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Vector;

public class ClaroColombiaAltaSuscripcionHandler implements HttpHandler {

    Logger log = Logger.getRootLogger();
    private static String RESPONSE = "<?xml version=\"1.0\" encoding=\"utf-8\" ?><soapenv:Envelope xmlns:" +
            "soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
            "<soapenv:Body><ns1:subscibeProductResponse xmlns:ns1=\"http://www.csapi.org/schema/parlayx/subscribeproduct/v1_0/local\">" +
            "<ns1:resultCode>%d</ns1:resultCode></ns1:subscibeProductResponse></soapenv:Body></soapenv:Envelope>";
    private static int[] responseCodes = {0, 7006, 7007, 7008, 7009, 7014, 7017, 7024, 7028, 7034, 7044, 7201, 7203, 7219, 7284, 7330, 7306, 7332, 7363, 7503, 7504, 7505, 7629, 7657, 7679, 7999};


    protected Vector<IMesssageListener> listeners = new Vector<IMesssageListener>();

    public void handle(HttpExchange t) throws IOException {
        log.info("Nueva request");

        log.info("getRandomResponse");
        String response = String.format(RESPONSE, RandomUtil.getRandomElement(responseCodes));
        log.info("Response: " + response);
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
