package feature.com.sondeosglobal.test.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Frontera2Steps {
	private static final String TEMPLATE_XML_BAJA_SUSCRIPTO = "<order><auth><usuario>{fr2.usuario}</usuario><password>{fr2.pass}</password></auth><service><provision>mcpgateway</provision><operacion>baja_suscripto</operacion></service><options/><parameters><servicio>{servicio.nombre}</servicio><ani>{ani}</ani><mt>{mt}</mt><telco>{telco}</telco></parameters></order>";
	private static final String TEMPLATE_XML_ALTA_SUSCRIPTO = "<order><auth><usuario>{fr2.usuario}</usuario><password>{fr2.pass}</password></auth><service><provision>mcpgateway</provision><operacion>alta_suscripto</operacion></service><options/><parameters><palabra>{palabra.nombre}</palabra><ani>{ani}</ani><mt>{mt}</mt><telco>{telco}</telco></parameters></order>";	
	//TODO generar un proyecto nuevo
	
	private String message;
	private String response;
	private int responseCode;	
	private final String globalURL = "http://192.168.10.221:8080/dnservices/dnservices/ctr/notificarEvento";
	private static final String FR2_URL = "http://192.168.10.6/service.php";
	private final String USER_AGENT = "Mozilla/5.0";
	private static List<String> messages;


	
	@Given("^the suscriptions with this data (\\d+), (.*?), (.*?), (.*?), (\\d+), (.*?)$")
	public void the_suscriptions_with_this_PersonalKids(Long ani, String palabra, String usuario, String pass, int mt, String telco) throws Throwable {
		String messageAlta = crearMensajeAlta(usuario, pass, ani, palabra, mt, telco);
		sendPost(FR2_URL, messageAlta, "text/xml");		
	    return;
	}

	@When("^I make the post to this frontera2 url \"(.*?)\" with (.*?), (.*?), (.*?), (\\d+), (\\d+), (.*?)$")
	public void i_make_the_post_to_this_frontera_url_with_TMS_TMS_PersonalKids_ar_personal_it(String url, String usuario, String pass, String servicio, Long ani, int mt, String telco) throws Throwable {
		String mensaje = crearMensajeBaja(usuario, pass, servicio, ani, mt, telco);
		sendPost(url, mensaje, "text/xml");
	    return;
	}
	

	@Then("^frontera2 return message contains \"(.*?)\"$")
	public void frontera_return_message_contains(String response) throws Throwable {
		assertThat(this.response, equalTo(response));
	    return;
	}	
	
	
	private String crearMensajeAlta(String usuario, String pass, Long ani, String palabra, int mt, String telco) {
		String message = TEMPLATE_XML_ALTA_SUSCRIPTO;
		message = message.replaceAll("\\{fr2.usuario\\}", usuario);
		message = message.replaceAll("\\{fr2.pass\\}", pass);
		message = message.replaceAll("\\{palabra.nombre\\}", palabra);
		message = message.replaceAll("\\{ani\\}", String.valueOf(ani));
		message = message.replaceAll("\\{mt\\}", String.valueOf(mt));
		message = message.replaceAll("\\{telco\\}", telco);
		return message;
	}	
	

	private String crearMensajeBaja(String usuario, String pass, String servicio, Long ani, int mt, String telco) {
		String message = TEMPLATE_XML_BAJA_SUSCRIPTO;
		message = message.replaceAll("\\{fr2.usuario\\}", usuario);
		message = message.replaceAll("\\{fr2.pass\\}", pass);
		message = message.replaceAll("\\{servicio.nombre\\}", servicio);
		message = message.replaceAll("\\{ani\\}", String.valueOf(ani));
		message = message.replaceAll("\\{mt\\}", String.valueOf(mt));
		message = message.replaceAll("\\{telco\\}", telco);
		return message;
	}

	
	@Given("^the frontera2 message \"(.*?)\"$")
	public void the_frontera2_message(String message) throws Throwable {
		this.message = message;
	}

	@When("^I make the post to this frontera2 url \"(.*?)\"$")
	public void i_make_the_post_to_this_frontera2_url(String strURL) throws Throwable {
		sendPost(strURL, message, "text/xml");
	}
	
	@When("^I make the json post to this frontera2 url \"(.*?)\"$")
	public void i_make_the_json_post_to_this_frontera2_url(String strURL) throws Throwable {
		sendPost(strURL, message, "application/json");
	}
	
	@Then("^frontera2 must return response code (\\d+)$")
	public void frontera2_must_return_response_code(int responseCode) throws Throwable {
		assertThat(this.responseCode, equalTo(responseCode));
	}
	
	
	@Then("^frontera2 return message contains one of$")
	public void frontera2_return_message_contains_one_of(List<String> msgs) throws Throwable {
	    boolean isExpectedResponse = false;
	    
	    Iterator<String> msgIterator = msgs.iterator();
	    while (msgIterator.hasNext()) {
			String msg = (String) msgIterator.next();
			
			if(response.contains(msg)) {
				isExpectedResponse = true;
			}
		}
	    
	    assertThat(isExpectedResponse, equalTo(true));
	}
	
	
	protected void sendPost(String url, String params, String contentType) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

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
		this.responseCode = con.getResponseCode();

		if (this.responseCode != 500) {
			//read response
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			this.response = response.toString();
		}
	}	
}
