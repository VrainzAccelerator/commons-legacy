package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sondeosglobal.conf.Configuracion;
import org.apache.log4j.Logger;

import com.sondeosglobal.helpers.PostHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.vrainz.helper.HttpConnectionHelper;
import com.vrainz.helper.httpconnection.content.ContentType;

import javax.xml.bind.DatatypeConverter;


@SuppressWarnings("restriction")
public class ClaroColombiaDefaultResponse implements HttpHandler {
    private static final String TIPO_BAJA = "BAJA";
    private static final String TIPO_ALTA = "ALTA";
    private static final String SERVICE_KEY = "<ns1:productID>";
    private static final int SERVICE_LEN = 14;

    private Configuracion configuracion = Configuracion.getInstance();

    private static final String HERMES_SUSCRIPCION_PROPERTY = "hermes.service.url";
    private static final String HERMES_URL_PROPERTY = "hermes.service.servicios";
    private static final String HERMES_AUTH_PROPERTY = "hermes.service.auth";

    private static final String ANI_KEY = "<ns1:userID><ID>";
    private static final int ANI_LEN = 12;

    protected static final String SEND_MESSAGE_TEMPLATE = "<soapenv:Envelope xmlns:tns=\"http://www.huawei.com/schema/osg/common/v2_1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" xmlns:ns0=\"http://www.csapi.org/schema/parlayx/sms/send/v2_2/local\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">   <soapenv:Header>      <wsse:Security>        <wsse:UsernameToken>           <wsse:Username>PA00002117</wsse:Username>            <wsse:Password Type=\"...#PasswordDigest\">r590X7S9nKilLWi+Ax2EV9gqDRU=</wsse:Password>            <wsse:Nonce>WScqanjCEAC4mQoBE07sAQ==</wsse:Nonce>            <wsse:Created>2003-07-16T01:24:32Z</wsse:Created>         </wsse:UsernameToken>      </wsse:Security>      <tns:RequestSOAPHeader>         <AppId>PERU</AppId>         <TransId>e370a385-dea3-4cf8-ab8f-2dfdf36bae84</TransId>         <tns:OA>tel:593985849998</tns:OA>         <tns:FA>tel:593985849998</tns:FA>         <tns:token/>      </tns:RequestSOAPHeader>   </soapenv:Header>   <soapenv:Body>      <ns0:sendSms>         <ns0:addresses>{ani}</ns0:addresses>         <ns0:senderName>57{shortNumber}</ns0:senderName>         <ns0:message>{message}</ns0:message>      </ns0:sendSms>   </soapenv:Body></soapenv:Envelope>";

    private Map<String, String> mensajes;
    private Map<String, String> mtServicio;

    Logger log = Logger.getLogger(getClass());

    public void handle(HttpExchange t) throws IOException {

        String response = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <soapenv:Body> <ns3:syncOrderRelationshipResponse xmlns:ns3=\"http://www.csapi.org/schema/parlayx/syncsubscription/v1_0/local\"> <ns3:result> <resultCode>00000000</resultCode> <resultMessage>success</resultMessage> </ns3:result> </ns3:syncOrderRelationshipResponse> </soapenv:Body> </soapenv:Envelope>";
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody(), "UTF-8"));

        String linea = "";
        StringBuffer rq = new StringBuffer("");
        while ((linea = br.readLine()) != null) {
            rq.append(linea);
        }
        log.info("Rquest: " + rq.toString());
        try {
            connectHermes(rq.toString());
            notificarApps(rq.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        OutputStream os = t.getResponseBody();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(response);

        t.sendResponseHeaders(200, response.length());
        bw.close();
        br.close();

        t.close();
    }

    private void notificarApps(String request) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd : hh:mm");
        Map<String, String> mapServicio = new HashMap<String, String>();
        mapServicio.put("MDSP2000017228", "{\"servicio\":\"MDSP2000017228\",\"telco\":\"co.claro\",\"fecha\":\"{fecha.now}\",\"ani\":\"{ani}\",\"nc\":\"35112\",\"tipo\":\"{tipo}\",\"palabra\":\"\"}");
        mapServicio.put("MDSP2000017224", "{\"servicio\":\"MDSP2000017224\",\"telco\":\"co.claro\",\"fecha\":\"{fecha.now}\",\"ani\":\"{ani}\",\"nc\":\"35188\",\"tipo\":\"{tipo}\",\"palabra\":\"\"}");
        mapServicio.put("MDSP2000017226", "{\"servicio\":\"MDSP2000017226\",\"telco\":\"co.claro\",\"fecha\":\"{fecha.now}\",\"ani\":\"{ani}\",\"nc\":\"35437\",\"tipo\":\"{tipo}\",\"palabra\":\"\"}");
        mapServicio.put("MDSP2000017229", "{\"servicio\":\"MDSP2000017229\",\"telco\":\"co.claro\",\"fecha\":\"{fecha.now}\",\"ani\":\"{ani}\",\"nc\":\"35677\",\"tipo\":\"{tipo}\",\"palabra\":\"\"}");
        Map<String, String> mapURLNotificacion = new HashMap<String, String>();
        mapURLNotificacion.put("MDSP2000017228", "http://claro.co.desafiofanatico.net/backend/subscriptions/receive");
        mapURLNotificacion.put("MDSP2000017224", "http://server.fun4ride.com/messaging/sondeos/claro_co/notification");
        mapURLNotificacion.put("MDSP2000017226", "http://dovahkiinappserver.appspot.com/api/v1/coclaro/sondeos/event");
        mapURLNotificacion.put("MDSP2000017229", "http://claro.co.prematchgame.net/backend/index.php/notifications/subscriptions/receive");

        String mensaje = "";


        int pos = request.toString().indexOf(SERVICE_KEY) + SERVICE_KEY.length();
        String service = request.substring(pos, pos + SERVICE_LEN);
        mensaje = mapServicio.get(service);

        pos = request.toString().indexOf(ANI_KEY) + ANI_KEY.length();
        String ani = request.substring(pos, pos + ANI_LEN);

        if (request.contains("commandForUnsub") || request.contains("<ns1:updateType>2</ns1:updateType>")) {
            mensaje = mensaje.replace("{tipo}", TIPO_BAJA);
        }

        if (request.contains("commandForSub") || request.contains("<ns1:updateType>1</ns1:updateType>")) {
            mensaje = mensaje.replace("{tipo}", TIPO_ALTA);
        }

        mensaje = mensaje.replace("{ani}", ani);
        mensaje = mensaje.replace("{fecha.now}", sdf.format(new Date()));
        log.info(mensaje);
        log.info(mapURLNotificacion.get(service));
        int responseCode = PostHelper.sendPost(mapURLNotificacion.get(service), mensaje, "application/json", false);
        log.info(responseCode);
    }

    private void connectHermes(String request) {
        int pos = request.toString().indexOf(SERVICE_KEY) + SERVICE_KEY.length();
        String service = request.substring(pos, pos + SERVICE_LEN);
        pos = request.toString().indexOf(ANI_KEY) + ANI_KEY.length();
        String ani = request.substring(pos, pos + ANI_LEN);

        String url = configuracion.getProperty(HERMES_URL_PROPERTY) + configuracion.getProperty(HERMES_SUSCRIPCION_PROPERTY);
        url = url.replace("{ani}", ani);
        url = url.replace("{servicio}", service);

        String authParams = configuracion.getProperty(HERMES_AUTH_PROPERTY);
        String encodedAuth = DatatypeConverter.printBase64Binary(authParams.getBytes());

        log.info("Conexion a Hermes: " + url);
        if (request.contains("commandForUnsub") || request.contains("<ns1:updateType>2</ns1:updateType>")) {
            try {
                HttpConnectionHelper.doDelete(url, "", ContentType.APPLICATION_JSON, encodedAuth);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (request.contains("commandForSub") || request.contains("<ns1:updateType>1</ns1:updateType>")) {
            try {
                HttpConnectionHelper.doPost(url, "", ContentType.APPLICATION_JSON, encodedAuth);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void enviarMensaje(String request) {

        mensajes = new HashMap<String, String>();
        mensajes.put("MDSP2000017228_ALTA", "Bienvenido a Club Desafio Fanatico! Para darte de baja manda BAJA al 35112");
        mensajes.put("MDSP2000017228_BAJA", "Te has dado de baja a Club Desafio Fanatico! Para darte de alta manda ALTA al 35112");

        mtServicio = new HashMap<String, String>();
        mtServicio.put("MDSP2000017228", "35112");
        mtServicio.put("MDSP2000017226", "35437");
        mtServicio.put("MDSP2000017224", "35188");
        mtServicio.put("MDSP2000017229", "35677");

        String params = SEND_MESSAGE_TEMPLATE;

        int pos = request.toString().indexOf(ANI_KEY) + ANI_KEY.length();
        String ani = request.substring(pos, pos + ANI_LEN);

        pos = request.toString().indexOf(SERVICE_KEY) + SERVICE_KEY.length();
        String service = request.substring(pos, pos + SERVICE_LEN);

        params = params.replace("{ani}", ani);
        if (request.contains("commandForUnsub")) {
            params = params.replace("{message}", mensajes.get(service + "_" + TIPO_BAJA));
        }
        if (request.contains("commandForSub")) {
            params = params.replace("{message}", mensajes.get(service + "_" + TIPO_ALTA));
        }

        params = params.replace("{shortNumber}", mtServicio.get(service));
        log.info("Post: " + params);

        try {
            //https://200.95.168.211:443/asg/services/SendSmsService
            PostHelper.sendPost("https://200.95.168.211:443/asg/services/SendSmsService", params, "text");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}