package com.vrainz.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vrains.persistence.oldmodel.mcp.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
