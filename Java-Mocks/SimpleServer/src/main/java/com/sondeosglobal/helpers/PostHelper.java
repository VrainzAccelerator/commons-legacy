package com.sondeosglobal.helpers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

public class PostHelper {
	private static Logger	log = Logger.getLogger(PostHelper.class);

	static final String USER_AGENT = "Mozilla/5.0";
	
	public static int sendPost(String url, String params, String contentType) throws Exception {
		return sendPost(url, params, contentType, true);
	}
	
	public static int sendPost(String url, String params, String contentType, boolean ssl) throws Exception {

		long t1 = System.currentTimeMillis();
		HttpURLConnection con = null;
		
		URL obj = new URL(url);
		if (ssl) {
			con = (HttpsURLConnection) obj.openConnection();
			disableSslVerification((HttpsURLConnection) con);
		} else {
			con = (HttpURLConnection) obj.openConnection();
		}
	
		
		
		//add POST reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
		
		//add parameters
		String urlParameters = params; 
		
		// send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		//get response code (ej. 200)
		int responseCode = con.getResponseCode();
		String responseStr = "Error";
		if (responseCode != 500) {
			//read response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			responseStr = response.toString();
		}
		con.disconnect();
		
		long t2 = System.currentTimeMillis();
		log.info("tiempo respuesta post: " + (t2 - t1));
		
		return responseCode;
	}	
	
	private static void disableSslVerification(HttpsURLConnection con) {
	    try
	    {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	            }
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	            }
	        }
	        };

	        // Install the all-trusting trust manager
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, trustAllCerts, new java.security.SecureRandom());
	        con.setDefaultSSLSocketFactory(sc.getSocketFactory());

	        // Create all-trusting host name verifier
	        HostnameVerifier allHostsValid = new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        };

	        // Install the all-trusting host verifier
	        con.setDefaultHostnameVerifier(allHostsValid);
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (KeyManagementException e) {
	        e.printStackTrace();
	    }
	}

}
