package com.mycompany.restresources;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.mycompany.domain.Execution;
import com.mycompany.domain.mappers.ExecutionMapper;
import com.mycompany.repositories.ExecutionRepository;
import com.mycompany.shareddomain.dtos.ExecutionDto;


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
	@Path("/{id}")
	public Response findById(@PathParam("id") String id) {
		Optional<Execution> execution = repository.findById(UUID.fromString(id));
		
		if(execution.isEmpty()) {
			return Response.status(404).build();
		}
		
		return Response.ok(ExecutionMapper.toDto(execution.get())).build();
	}
	
	@GET
	public Response findAll() {
		Iterable<Execution> executions = repository.findAll();
		
		List<ExecutionDto> dtos = StreamSupport.stream(executions.spliterator(), false)
				.map(ExecutionMapper::toDto).collect(Collectors.toList());
		
		return Response.ok(dtos).build();
	}
	
	@DELETE
	@Path("/{uuid}")
	public Response deleteById(@PathParam("id") String id) {
		repository.deleteById(UUID.fromString(id));
		
		return Response.ok().build();
		
	}
	
	@DELETE
	@Path("/all")
	public Response deleteAll() {
		repository.deleteAll();
		
		return Response.ok().build();
		
	}
}
