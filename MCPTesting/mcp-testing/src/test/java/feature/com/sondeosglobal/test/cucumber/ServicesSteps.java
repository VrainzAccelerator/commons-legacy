package feature.com.sondeosglobal.test.cucumber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ServicesSteps {

	private String message;
	private String response;
	private int responseCode;	
	private final String globalURL = "http://192.168.10.221:8080/dnservices/dnservices/ctr/notificarEvento";
	private final String USER_AGENT = "Mozilla/5.0";
	
	@Given("^the message \"(.*?)\"$")
	public void the_message(String message) throws Throwable {
		this.message = message;
	}

	@When("^I make the post$")
	public void i_make_the_post() throws Throwable {
		sendPost(globalURL, message);
	}

	@When("^I make the post to this url \"(.*?)\"$")
	public void i_make_the_post_to_this_url(String strURL) throws Throwable {
		sendPost(strURL, message);
	}
	
	@Then("^response should contain \"(.*?)\"$")
	public void response_should_contain(String response) throws Throwable {
		assertThat(this.response, equalTo(response));
	}
	
	@Then("^response code (\\d+) must be returned$")
	public void response_code_must_be_returned(int responseCode) throws Throwable {
		assertThat(this.responseCode, equalTo(responseCode));
	}

	/* Personal */
	
	@Given("^the personal argentina new subscription message$")
	public void the_personal_argentina_new_subscription_message() throws Throwable {
		this.message = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"urn:CdagNotificationService\"><soapenv:Header/><soapenv:Body><ns1:NotificarEvento><user>user</user><password>12345678</password><notifications><tid>1010</tid><tipoEvento>0</tipoEvento><datoEvento>3838|20141021|1964|amba21|33000|</datoEvento></notifications></ns1:NotificarEvento></soapenv:Body>";
	}

	@When("^a post to the personal argentina endpoint is made$")
	public void a_post_to_the_personal_argentina_endpoint_is_made() throws Throwable {
		String strURL = "http://localhost:8080/axis2/services/NotificationsService";
		sendPost(strURL, message);
	}
	
	@Then("^personal argentina ok response should be returned$")
	public void personal_argentina_ok_response_should_be_returned() throws Throwable {
		String expected = "<?xml version='1.0' encoding='utf-8'?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns1:NotificarEventoResponse xmlns:ns1=\"urn:CdagNotificationService\" /></soapenv:Body></soapenv:Envelope>";
		assertThat(this.response, equalTo(expected));
	}
	
	protected void sendPost(String url, String params) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add POST reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
		
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
