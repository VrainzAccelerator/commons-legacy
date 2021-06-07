package com.vrainz.co.claro.ws.syncsubsc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.vrainz.co.claro.ws.syncsubsc.generated.Result;

@XmlRootElement(namespace="http://www.csapi.org/schema/parlayx/syncsubscription/v1_0/local")
public class SyncOrderRelationshipResponse {
	
	Result result;
	
	@XmlElement(namespace = "http://www.csapi.org/schema/parlayx/syncsubscription/v1_0/local")
	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
}
