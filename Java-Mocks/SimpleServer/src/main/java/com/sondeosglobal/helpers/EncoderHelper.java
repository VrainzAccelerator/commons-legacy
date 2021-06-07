package com.sondeosglobal.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Random;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.StringUtils;


public class EncoderHelper {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
		String result = GenPwDgstForClaroColombia("Mk4KHcaVa5X/F7zHSlhcVJD", "2016-08-31T15:50:07Z", "Opt1c0m2");
		if (result.equals("EymKNgFxMTLU7guxVA1z5bj91Jw=")) {
			System.out.println("Correcto!!");
		} else {
			System.out.println("Error!!");
		}
		System.out.println(result);
	}

	public static String GenPwDgstForClaroColombia(String nonce, String dateCreated, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
	    StringBuffer data = new StringBuffer();
	    data.append(nonce);
	    data.append(dateCreated);
	    data.append(password);
	    MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    digest.update(data.toString().getBytes("utf8"));
	    
	    byte[] hased =  digest.digest();
	    byte[] b64 = Base64.encodeBase64(hased);
	    String result = new String(b64);
	    //String result = Base64.encode(hased);
	    return result;
	}
	
	public static String generateNonce(){
		 char[] pool = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/=".toCharArray();
	     int len = 23;
	     StringBuilder str = new StringBuilder();
	     Random random = new Random();
	     for (int i = 0; i < len; i++) {
	           char c = pool[random.nextInt(pool.length)];
	           str.append(c);
	        }
	        return str.toString();
	}
	
}
