package com.mycompany.rest;

import java.util.Hashtable;
import java.util.List;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Execution;
import com.mycompany.repository.AlgorithmRepository;
import com.mycompany.repository.ExecutionRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

@Stateless
@RequestScoped
@Path("/algorithm")
@Produces("application/json")
@Consumes("application/json")
public class AlgorithmResource {
	@EJB
	private AlgorithmRepository algorithmRepository;
	
	@EJB
	private ExecutionRepository executionRepository;

	@GET
	@Path("/{uuid}")
	public Response getById(@PathParam("uuid") UUID uuid) {
		Algorithm algorithm = algorithmRepository.getById(uuid);
		
		GenericEntity<Algorithm>>
		
		return Response.ok(algorithm).build();
	}
	
	@PUT
	@Path("/{uuid}/run")
	public Response runAlgorithm(@PathParam("uuid") UUID uuid) {
		Algorithm algorithm = algorithmRepository.getById(uuid);
		Execution execution = algorithm.run();
		executionRepository.create(execution);
		return Response.ok(execution).build();
		
	}

	@GET
	public Response getAll() {
		List<Algorithm> algorithms = algorithmRepository.getAll();
		GenericEntity<List<Algorithm>> algorithmWrapper = new GenericEntity<List<Algorithm>>(algorithms) {};
		return Response.ok(algorithmWrapper).build();
	}
	
//	@POST
//	public Response save(final Algorithm algorithm) {
//		Algorithm persistedAlgorithm =  algorithmRepository.save(algorithm);
//		return Response.ok(persistedAlgorithm).build();
//	}
}
