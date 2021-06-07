package com.vrainz.mcpmid.consents;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.vrainz.helper.HttpConnectionHelper;
import com.vrainz.mcpmid.consents.response.JsonResponse;

@Path("/")
public class ConsentService {
	Logger logger = Logger.getLogger(ConsentService.class);
	
	@POST
	@Path("/consents")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JsonResponse consents(String jsonString) {
		logger.debug("json entrada: " + jsonString);
		/*
		http://<IP>:<PORT>/API/CCG?MSISDN=xxxxxxxxxx&productID=0145019900&pName=Photo+Storie
s+Weekly&pPrice=3500&pVal=7&CpId=shf&CpPwd=shf%401920&CpName=Shotformats&reqMode=
WAP&reqType=Subscription&ismID=17&transID=xxxxxxx1728155&srcIP=180.151.100.230&pValUnit
=D&pDesc=Photo+Stories+Weekly&Wap_mdata=%23ffffff%7Chttp%3A%2F%2F180.151.100.230%2
Fcmscommonweb%2Fother%2F112325_150x100.jpg%7CNA%7CNA%7C176X160"
*/
		
		String url = "http://localhost:8000/api/CCG?MSISDN=xxxxxxxxxx&productID=0145019900&pName=Photo+Storie s+Weekly&pPrice=3500&pVal=7&CpId=shf&CpPwd=shf%401920&CpName=Shotformats&reqMode= WAP&reqType=Subscription&ismID=17&transID=xxxxxxx1728155&srcIP=180.151.100.230&pValUnit =D&pDesc=Photo+Stories+Weekly&Wap_mdata=%23ffffff%7Chttp%3A%2F%2F180.151.100.230%2 Fcmscommonweb%2Fother%2F112325_150x100.jpg%7CNA%7CNA%7C176X160";
		
		try {
			HttpConnectionHelper.doGet(url, "", MediaType.TEXT_HTML);
			logger.debug(HttpConnectionHelper.getResponseMessage());
			logger.debug("pegada exitosa");
		} catch (Exception e) {
			//TODO aca se arma la respuesta erronea
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonResponse response = new JsonResponse();
		
		response.setMessage(HttpConnectionHelper.getResponseMessage());
		response.setStatus(String.valueOf(HttpConnectionHelper.getResponseCode()));
		
		return response;

	}
}

