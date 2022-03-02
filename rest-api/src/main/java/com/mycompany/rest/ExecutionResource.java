package com.mycompany.rest;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.mycompany.domain.Device;
import com.mycompany.domain.Execution;
import com.mycompany.repository.ExecutionRepository;


@Stateless
@Path("/execution")
@Produces("application/json")
@Consumes("application/json")
public class ExecutionResource {
	@EJB
	ExecutionRepository repository;
	
//	@Resource
//	ExecutorService service;
	
//	@POST
//	@Path("/{uuid}/run")
//	public Response run(@PathParam("uuid") UUID uuid, Device device) {
//		Optional<Execution> execution = repository.findById(uuid);
//		if(execution.isEmpty()) {
//			return Response.status(404).build();
//		}
//		
//		execution.get().run();
//			
//		return Response.ok(execution).build();
//	}
	
	@GET
	@Path("/{uuid}")
	public Response findById(@PathParam("uuid") UUID uuid) {
		Optional<Execution> execution = repository.findById(uuid);
		
		if(execution.isEmpty()) {
			return Response.status(404).build();
		}
		
		return Response.ok(execution.get()).build();
	}
	
	@GET
	public Response findAll() {
		Iterable<Execution> executions = repository.findAll();
		return Response.ok(executions).build();
	}
	
	@DELETE
	@Path("/{uuid}")
	public Response deleteById(@PathParam("uuid") UUID uuid) {
		repository.deleteById(uuid);
		
		return Response.ok().build();
		
	}
}
