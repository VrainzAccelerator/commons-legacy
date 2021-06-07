package com.sondeosglobal.simpleserver.handlers;

import com.sondeosglobal.conf.Configuracion;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vrainz.helper.HttpConnectionHelper;
import com.vrainz.helper.httpconnection.content.ContentType;
import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class DigicelRecepcionMensajes implements HttpHandler {
    private Logger logger = Logger.getLogger(getClass());
    private Configuracion configuracion = Configuracion.getInstance();

    private static final String VALIDATION_ERROR_MSG = "Servicio no encontrado o parametros invalidos";
    private static final String[] PARAMS = {"message", "shortcode", "msisdn"};
    private static final int STATUS_NOT_FOUND_CODE = 404;
    private static final int STATUS_OK_CODE = 200;
    private static final int STATUS_BAD_REQUEST_CODE = 400;
    private static final String NOT_FOUND_ERROR = "Not Found";
    private static final String SERVICE_NAME = "mensajes";
    private static final String URI = "/api/";
    private static final char DOT = '.';
    private static final char HYPHEN = '-';

    private static final String HERMES_URL_PROPERTY = "hermes.service.url";
    private static final String HERMES_URI_PROPERTY = "hermes.service.mensajes";
    private static final String HERMES_AUTH_PROPERTY = "hermes.service.auth";

    public void handle(HttpExchange httpExchange) throws IOException {
        String query = httpExchange.getRequestURI().getRawQuery();
        String path = httpExchange.getRequestURI().getRawPath();
        logger.info("Request: " + path + query);
        Map<String, String> requestParams = getQueryStringParams(httpExchange);

        try {
            validateURL(httpExchange, requestParams);
        } catch (IOException e) {
            return;
        }

        String clientOperator = getTelcoCodeFromURL(httpExchange).replace(HYPHEN, DOT);
        String ani = requestParams.get("msisdn");
        String message = requestParams.get("message");
        String shortCode = requestParams.get("shortcode");

        int responseCode = sendMessageHermes(message, shortCode, ani, clientOperator);
        if (responseCode == 200) {
            sendResponse(httpExchange, STATUS_OK_CODE, "OK");
        } else {
            sendResponse(httpExchange, STATUS_BAD_REQUEST_CODE, "Bad request");
        }

    }

    private int sendMessageHermes(String message, String shortCode, String ani, String clientOperator) {
        int responseCode = 404;
        String url = configuracion.getProperty(HERMES_URL_PROPERTY) + configuracion.getProperty(HERMES_URI_PROPERTY);
        url = url.replace("{mensaje}", message);
        url = url.replace("{ani}", ani);
        url = url.replace("{codigoCorto}", shortCode);
        url = url.replace("{telco}", clientOperator);

        String authParams = configuracion.getProperty(HERMES_AUTH_PROPERTY);
        String encodedAuth = DatatypeConverter.printBase64Binary(authParams.getBytes());
        logger.info("Conexion a Hermes: " + url);

        try {
            responseCode = HttpConnectionHelper.doPost(url, "", ContentType.APPLICATION_JSON, encodedAuth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    private void validateURL(HttpExchange httpExchange, Map<String, String> requestParams) throws IOException {
        if (!validatePath(httpExchange) || !containsAllParams(requestParams)) {
            logger.error(VALIDATION_ERROR_MSG);
            sendResponse(httpExchange, STATUS_NOT_FOUND_CODE, NOT_FOUND_ERROR);
            logger.info("Response sent: " + STATUS_NOT_FOUND_CODE + " " + NOT_FOUND_ERROR);
            throw new IOException(VALIDATION_ERROR_MSG);
        }
    }


    private void sendResponse(HttpExchange httpExchange, int responseCode, String responseContent) throws IOException {
        OutputStream os = httpExchange.getResponseBody();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os));
        bufferedWriter.write(responseContent);
        httpExchange.sendResponseHeaders(responseCode, responseContent.length());
        bufferedWriter.close();
    }

    private boolean containsAllParams(Map<String, String> params) {
        boolean result = true;
        int paramsNumber = PARAMS.length;
        for (int i = 0; i < paramsNumber; i++) {
            if (!params.containsKey(PARAMS[i])) {
                result = false;
                break;
            }
        }
        return result;
    }

    private Map<String, String> getQueryStringParams(HttpExchange httpExchange) {
        String query = httpExchange.getRequestURI().getRawQuery();
        Map<String, String> result = new HashMap<String, String>();
        if (query != null) {
            for (String param : query.split("&")) {
                String pair[] = param.split("=");
                if (pair.length > 1) {
                    result.put(pair[0], pair[1]);
                } else {
                    result.put(pair[0], "");
                }
            }
        }
        return result;
    }

    private boolean validatePath(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getRawPath();
        int clientOperatorSize = path.indexOf("/" + SERVICE_NAME) - (path.indexOf(URI) + URI.length());
        return clientOperatorSize > 0;
    }

    private String getTelcoCodeFromURL(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getRawPath();
        int begining = path.indexOf(URI) + URI.length();
        int end = path.indexOf("/" + SERVICE_NAME);
        String telco = path.substring(begining, end);
        logger.info("Codigo cliente operador recibido " + telco);
        return telco;
    }

}
