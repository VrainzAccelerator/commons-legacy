package com.vrainz.data.jpa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vrainz.data.jpa.services.ClienteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestClienteService {
	
	@Autowired
	ClienteService clienteService;

	@Test
	public void testNotNull() {
		Assert.assertNotNull(clienteService);
		
	}

}
