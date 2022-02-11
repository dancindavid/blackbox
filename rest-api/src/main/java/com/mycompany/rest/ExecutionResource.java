package com.mycompany.rest;

import java.util.Hashtable;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mycompany.repository.ExecutionRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;


@Stateless
@RequestScoped
@Path("/algorithm-execution")
@Produces("application/json")
@Consumes("application/json")
public class ExecutionResource {
	@EJB
	ExecutionRepository repository;
	
	@PUT
	@Path("/{id}/start")
	public Damage startAlgorithm(@PathParam("id") int id) {
		
	}
	
	@GET
	@Path("/{id}/done")
	boolean isDone(@PathParam("id") int id) {
		
		
	}
}
