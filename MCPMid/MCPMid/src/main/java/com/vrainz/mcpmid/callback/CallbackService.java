package com.vrainz.mcpmid.callback;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.vrainz.helper.HttpConnectionHelper;
import com.vrainz.mcpmid.callback.parameters.RedirectCallbackParameters;
import com.vrainz.mcpmid.config.MCPMidConfig;
import com.vrainz.mcpmid.dbill.request.DBillRequest;
import com.vrainz.mcpmid.dbill.response.DBillResponse;

@Path("/callback")
public class CallbackService {
	Logger logger = Logger.getLogger(CallbackService.class);
	
	@GET
	@Path("/redirect")
	public Response redirect(@BeanParam RedirectCallbackParameters params) {
		logger.info("parameters: " + params);
		
		//hit dbill billing service
		hitDbillService();
		
		String redirectURL = MCPMidConfig.getInstance().getCallbackRedirectURL();
		
		URI uri = null;
		try {
			uri = new URI(redirectURL);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.seeOther(uri).build();
	}

	private void hitDbillService() {
		
		DBillResponse dbillResponse = new DBillResponse();
		try {
			String urlDbill = MCPMidConfig.getInstance().getDbillAPIURL();
			String body = buildDBillRequest();
			String contentType = MediaType.APPLICATION_XML;
			
			HttpConnectionHelper.doPost(urlDbill, body, contentType);
			String strResponse = HttpConnectionHelper.getResponseMessage();
			logger.debug("response: " + strResponse);
			
			dbillResponse = mapDBillResponse(strResponse);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private String buildDBillRequest() throws JAXBException {
		DBillRequest request = new DBillRequest();
		request.setRequestType("1002");
		request.setServiceNode("AVAN");
		request.setSequenceNo("12345");
		request.setCallingParty("9810599383");
		request.setServiceType("CRBT");
		request.setServiceId("CRBT");
		request.setBearerId("SMS");
		request.setChargeAmount("50");
		request.setPlanId("-1");
		request.setAsyncFlag("Y");
		request.setRenewalFlag("-1");
		request.setBundleType("-1");
		request.setServiceUsage("-1");
		request.setPromoId("-1");
		request.setSubscriptionFlag("-1");
		
		JAXBContext jaxbContext = JAXBContext.newInstance(DBillRequest.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		StringWriter sw = new StringWriter();
		
		marshaller.marshal(request, sw);
		
		return sw.toString();
	}

	private DBillResponse mapDBillResponse(String response) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(DBillResponse.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(response);
		DBillResponse dbillResponse = (DBillResponse) unmarshaller.unmarshal(reader);
		
		logger.debug("response xml: " + dbillResponse);
		
		return dbillResponse;
		
	}

}

