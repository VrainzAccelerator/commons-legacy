package com.vrainz.data.jpa.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vrains.persistence.oldmodel.mcp.Cliente;
import com.vrainz.data.jpa.ClienteRepository;

@Repository
public class ClienteService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	ClienteRepository repository;

	public List<Cliente> findAll() {
		return repository.findAll();
	}
}
