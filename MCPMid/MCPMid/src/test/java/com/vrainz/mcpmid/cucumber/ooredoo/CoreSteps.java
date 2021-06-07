package com.vrainz.mcpmid.cucumber.ooredoo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import com.vrainz.helper.HttpConnectionHelper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CoreSteps {

	protected int responseCode;
	protected String message;
	protected HttpConnectionHelper postHelper; 
	
	@Given("^the message \"(.*?)\"$")
	public void the_message(String message) throws Throwable {
	    this.message = message;
	}

	@When("^I make the post to this url \"(.*?)\"$")
	public void i_make_the_post_to_this_url(String url) throws Throwable {
		postHelper.doPost(url, message, "application/json");
	}

	@Then("^response code (\\d+) must be returned$")
	public void response_code_must_be_returned(int code) throws Throwable {
	    assertThat(postHelper.getResponseCode(), equalTo(code));
	}
	
	
}
