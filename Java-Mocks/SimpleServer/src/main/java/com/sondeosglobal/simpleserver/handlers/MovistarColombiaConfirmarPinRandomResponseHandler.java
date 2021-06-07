package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Vector;

import com.sondeosglobal.utils.RandomUtil;
import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class MovistarColombiaConfirmarPinRandomResponseHandler implements HttpHandler {
    Logger log = Logger.getRootLogger();

    private static String CODIGO_0 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>0</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1100 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1100</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1101 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1101</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1102 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1102</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1103 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1103</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1104 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1104</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1105 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1105</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1106 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1106</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1107 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1107</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1108 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1108</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1110 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1110</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1111 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1111</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1112 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1112</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1128 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1128</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1200 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1200</resultCode><desc>OK</desc></params></requestResponse>";
    private static String CODIGO_1999 = "<?xml version='1.0' encoding='ISO-8859-1'?><requestResponse><params><resultCode>1999</resultCode><desc>OK</desc></params></requestResponse>";

    private String[] respuestasPosibles = {CODIGO_0, CODIGO_1100, CODIGO_1101, CODIGO_1102, CODIGO_1103, CODIGO_1104, CODIGO_1105, CODIGO_1106, CODIGO_1107, CODIGO_1108, CODIGO_1110, CODIGO_1111, CODIGO_1112, CODIGO_1128, CODIGO_1200, CODIGO_1999};

    protected Vector<IMesssageListener> listeners = new Vector<IMesssageListener>();

    public void handle(HttpExchange t) throws IOException {
        log.info("Nueva request");

        log.info("getRandomResponse");
        String response = RandomUtil.getRandomElement(respuestasPosibles);
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
