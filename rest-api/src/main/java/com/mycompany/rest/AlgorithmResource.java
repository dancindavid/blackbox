package com.mycompany.rest;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Device;
import com.mycompany.domain.Execution;
import com.mycompany.repository.AlgorithmRepository;
import com.mycompany.repository.ExecutionRepository;

@Stateless
@Path("/algorithm")
@Produces("application/json")
@Consumes("application/json")
public class AlgorithmResource {
	@EJB
	private AlgorithmRepository algorithmRepository;
	
	@EJB
	private ExecutionRepository executionRepository;
	
	@Resource(name="java:comp/DefaultManagedExecutorService")
	ExecutorService service;
	
	ExecutorService unmanagedService = Executors.newFixedThreadPool(10);
	
	@GET
	@Path("/{key}")
	public Response findById(@PathParam("key") String key) {
		Optional<Algorithm> algorithm = algorithmRepository.findById(key);

		if(algorithm.isEmpty()) {
			return Response.status(404).build();
		}
		
		return Response.ok(algorithm.get()).build();
	}
	
	@POST
	@Path("/{key}/run-managed")
	public Response runAlgorithmManaged(@PathParam("key") String key, Device device) {
		Optional<Algorithm> algorithm = algorithmRepository.findById(key);
		
		Optional<Execution> execution = algorithm.map(alg->alg.runManaged(service, device));
		
		if(execution.isEmpty()) {
			return Response.status(404).build();
		}
		
		Execution newExecution = executionRepository.save(execution.get());
		return Response.ok(newExecution).build();
		
	}
	
	@POST
	@Path("/{key}/run-unmanaged")
	public Response runAlgorithmUnmanaged(@PathParam("key") String key, Device device) {
		Optional<Algorithm> algorithm = algorithmRepository.findById(key);
		
		Optional<Execution> execution = algorithm.map(alg->alg.runUnmanaged(unmanagedService, device));
		
		if(execution.isEmpty()) {
			return Response.status(404).build();
		}
		
		Execution newExecution = executionRepository.save(execution.get());
		return Response.ok(newExecution).build();
		
	}

	@GET
	public Response findAll() {
		Iterable<Algorithm> algorithms = algorithmRepository.findAll();
		return Response.ok(algorithms).build();
	}
}
