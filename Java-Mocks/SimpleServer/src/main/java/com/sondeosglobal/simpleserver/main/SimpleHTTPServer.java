package com.sondeosglobal.simpleserver.main;

import com.sondeosglobal.conf.Configuracion;
import com.sondeosglobal.helpers.PostHelper;
import com.sondeosglobal.simpleserver.handlers.*;
import com.sun.net.httpserver.HttpServer;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;

@SuppressWarnings("restriction")
public class SimpleHTTPServer {
    static Iterator<String> lineasIterator;

    public static void main(String[] args) throws Exception {
        Logger log = Logger.getLogger(SimpleHTTPServer.class);
        log.info("Inicio de SimpleHTTPServer");
        Monitor monitor = new Monitor();
        //HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        HttpServer server = HttpServer.create(new InetSocketAddress(Configuracion.getInstance().getPuerto()), 0);
        server.createContext("/echo", new EchoHandler());
        server.createContext("/test", new DefaultResponseHandler());
//        PersonalCHKAndBillResponseHandler chkandbillHandler = new PersonalCHKAndBillResponseHandler();
//        chkandbillHandler.addListener(monitor);
//        server.createContext("/chkandbill", chkandbillHandler);
        server.createContext("/randomchkandbill", new PersonalCHKAndBillRandomResponseHandler());
        server.createContext("/pegadainicio", new PegadaInicioHandler());
        server.createContext("/movistarcolombia", new MovistarColombiaXMLResponse());
        server.createContext("/movistarcolombiasolicitarpin", new MovistarColombiaSolicitarPinRandomResponseHandler());
        server.createContext("/movistarcolombiaconfirmarpin", new MovistarColombiaConfirmarPinRandomResponseHandler());
        server.createContext("/api/CCG", new OoredooConsentGatewayMockServiceHandler());
        server.createContext("/ooredo/redirect", new OoredooRedirectMockServiceHandler());
        server.createContext("/ooredo/lastredirect", new OoredooServeLastRedirectHandler());
        server.createContext("/ooredoo/DBill", new OoredooSolicitarBillingHandler());
        server.createContext("/clarocolombiasubscribe", new ClaroColombiaAltaSuscripcionHandler());
        server.createContext("/SyncSubscriptionService/services", new ClaroColombiaDefaultResponse());
        server.createContext("/SmsNotification", new ClaroColombiaNoResponse());
        server.createContext("/api/", new DigicelRecepcionMensajes());

        server.setExecutor(Executors.newCachedThreadPool()); // multihilo
        server.start();

//        iterateLineas(monitor);
//        
//        Thread.sleep(300000); //esperar un minuto
//
//        log.info("\n\tTiempo de respuesta maximo: " + monitor.getMaxResponsTime() + " ms");


    }

    private static void iterateLineas(Monitor monitor) {
        List<String> lineas = Configuracion.getInstance().getLineas();
        lineasIterator = lineas.iterator();

        while (lineasIterator.hasNext()) {
            hitFronteraInsertaSMS(monitor);
        }
    }


    private static void hitFronteraInsertaSMS(Monitor monitor) {
        if (monitor.getPedidosSize() < 11) {
            String linea = (String) lineasIterator.next();
            executePostForLinea(monitor, linea);
        }

    }


    private static void executePostForLinea(Monitor monitor, String linea) {
        String url = "http://192.168.10.6/service.php";
        String contentType = "text";

        monitor.addPedido(linea);

        String params = "<order><auth> <usuario>kidloom</usuario> <password>kidloom123</password> </auth> <service> <provision>recepcionSMS</provision> <operacion>insertar_sms</operacion> </service> <options /> <parameters> <destAddress>5437</destAddress> <sourceAddress>"
                + "" + linea + "</sourceAddress> <shortMessage>ALTA</shortMessage> <telco>ar.personal</telco> </parameters> </order>";

        // hace el post
        try {
            PostHelper.sendPost(url, params, contentType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}