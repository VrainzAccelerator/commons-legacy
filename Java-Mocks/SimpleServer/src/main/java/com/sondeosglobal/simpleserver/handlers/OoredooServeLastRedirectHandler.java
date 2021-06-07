package com.sondeosglobal.simpleserver.handlers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class OoredooServeLastRedirectHandler implements HttpHandler {

	public void handle(HttpExchange t) throws IOException {
		Headers h = t.getResponseHeaders();
		h.set("Content-Type", "text/html");

		File file = new File("lastredirect.html");
		byte[] bytearray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		bis.read(bytearray, 0, bytearray.length);

		t.sendResponseHeaders(200, file.length());
		OutputStream os = t.getResponseBody();
		os.write(bytearray, 0, bytearray.length);
		os.close();

	}
}
