package com.vrainz.co.claro.ws.syncsubsc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "soapenv:Envelope")
public class SyncSubscriptionService{
	@XmlElement(name = "soapenv:Header")
	private String header;
//	@XmlElement(name = "soapenv:Body")
//	private Body body;
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
//	public Body getBody() {
//		return body;
//	}
//	public void setBody(Body body) {
//		this.body = body;
//	}
	
 }
