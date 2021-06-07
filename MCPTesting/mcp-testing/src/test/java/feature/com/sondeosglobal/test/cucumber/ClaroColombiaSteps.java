package feature.com.sondeosglobal.test.cucumber;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ClaroColombiaSteps {
	
	@Given("^un usuario con el telefono '(\\d+)'$")
	public void un_usuario_con_el_telefono(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^envia un sms con la palabra 'ALTA' al numero '(\\d+)'$")
	public void envia_un_sms_con_la_palabra_ALTA_al_numero(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	//alta exito
	@Then("^numero de telefono '(\\d+)' debe estar dado de alta en el servicio 'ClubWisdom'$")
	public void numero_de_telefono_debe_estar_dado_de_alta_en_el_servicio_ClubWisdom(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^se debe recibir el mensaje 'El alta se realizo correctamente'$")
	public void se_debe_recibir_el_mensaje_El_alta_se_realizo_correctamente() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	//alta - ya dado de alta
	@Then("^se espera el mensaje 'el suscripto ya estaba dado de alta'$")
	public void se_espera_el_mensaje_el_suscripto_ya_estaba_dado_de_alta() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	//alta - error
	@Then("^numero de telefono '(\\d+)' no debe estar dado de alta en el servicio 'ClubWisdom'$")
	public void numero_de_telefono_no_debe_estar_dado_de_alta_en_el_servicio_ClubWisdom(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^se debe recibir el mensaje 'no se pudo realizar el alta'$")
	public void se_debe_recibir_el_mensaje_no_se_pudo_realizar_el_alta() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	
	@Given("^un suscripto dado de alta con el telefono '(\\d+)'$")
	public void un_suscripto_dado_de_alta_con_el_telefono(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^se realiza una solicitud de cobro$")
	public void se_realiza_una_solicitud_de_cobro() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	//cobro - exito
	@Then("^el suscripto con el numero de telefono '(\\d+)' debe registrar un cobro para el servicio 'ClubWisdom'$")
	public void el_suscripto_con_el_numero_de_telefono_debe_registrar_un_cobro_para_el_servicio_ClubWisdom(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^se debe recibir el mensaje 'El cobro se realizo correctamente'$")
	public void se_debe_recibir_el_mensaje_El_cobro_se_realizo_correctamente() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	//cobro - no suscripto
	@Given("^un suscripto que no esta dado de alta con el telefono '(\\d+)'$")
	public void un_suscripto_que_no_esta_dado_de_alta_con_el_telefono(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^se debe recibir el mensaje 'No se pudo registrar el cobro porque el numero de telefono no esta dado de alta'$")
	public void se_debe_recibir_el_mensaje_No_se_pudo_registrar_el_cobro_porque_el_numero_de_telefono_no_esta_dado_de_alta() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^numero de telefono '(\\d+)' no debe registrar un cobro para el servicio 'ClubWisdom'$")
	public void numero_de_telefono_no_debe_registrar_un_cobro_para_el_servicio_ClubWisdom(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	//cobro - error
	@Then("^se debe recibir el mensaje 'No se pudo realizar el cobro'$")
	public void se_debe_recibir_el_mensaje_No_se_pudo_realizar_el_cobro() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	//baja - exito

	@When("^envia un sms con la palabra 'BAJA' al numero '(\\d+)'$")
	public void envia_un_sms_con_la_palabra_BAJA_al_numero(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^numero de telefono '(\\d+)' debe estar dado de baja en el servicio 'ClubWisdom'$")
	public void numero_de_telefono_debe_estar_dado_de_baja_en_el_servicio_ClubWisdom(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^se debe recibir el mensaje 'El suscripto fue dado de baja exitosamente'$")
	public void se_debe_recibir_el_mensaje_El_suscripto_fue_dado_de_baja_exitosamente() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	//baja - no suscripto
	@Given("^un suscripto no dado de alta con el telefono '(\\d+)'$")
	public void un_suscripto_no_dado_de_alta_con_el_telefono(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^debe recibir el mensaje 'el suscripto no se encontraba dado de alta al servicio'$")
	public void debe_recibir_el_mensaje_el_suscripto_no_se_encontraba_dado_de_alta_al_servicio() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
	//baja -error
	@Then("^numero de telefono '(\\d+)' no debe estar dado de baja en el servicio 'ClubWisdom'$")
	public void numero_de_telefono_no_debe_estar_dado_de_baja_en_el_servicio_ClubWisdom(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^se debe recibir el mensaje 'no se pudo realizar la baja'$")
	public void se_debe_recibir_el_mensaje_no_se_pudo_realizar_la_baja() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
