package com.mycompany.restresources;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
import com.mycompany.domain.mappers.AlgorithmMapper;
import com.mycompany.domain.mappers.DeviceMapper;
import com.mycompany.domain.mappers.ExecutionMapper;
import com.mycompany.repositories.AlgorithmRepository;
import com.mycompany.repositories.ExecutionRepository;
import com.mycompany.shareddomain.dtos.AlgorithmDto;
import com.mycompany.shareddomain.dtos.DeviceDto;
import com.mycompany.shareddomain.dtos.ExecutionDto;

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
	ExecutorService managedService;
	
	ExecutorService unmanagedService = Executors.newFixedThreadPool(10);
	
	@GET
	@Path("/{key}")
	public Response findById(@PathParam("key") String key) {
		Optional<Algorithm> algorithm = algorithmRepository.findById(key);

		if(algorithm.isEmpty()) {
			return Response.status(404).build();
		}
		
		return Response.ok(AlgorithmMapper.toDto(algorithm.get())).build();
	}
	
	@POST
	@Path("/{key}/run-managed")
	public Response runAlgorithmManaged(@PathParam("key") String key, DeviceDto deviceDto) {
		return runAlgorithm(key, deviceDto, managedService);		
	}
	
	@POST
	@Path("/{key}/run-unmanaged")
	public Response runAlgorithmUnmanaged(@PathParam("key") String key, DeviceDto deviceDto) {
		return runAlgorithm(key, deviceDto, unmanagedService);	
	}
	
	private Response runAlgorithm(String key, DeviceDto deviceDto, ExecutorService service) {
		Optional<Algorithm> algorithm = algorithmRepository.findById(key);
		
		Optional<Execution> execution = algorithm.map(alg->alg.run(service, DeviceMapper.fromDto(deviceDto)));
		
		if(execution.isEmpty()) {
			return Response.status(404).build();
		}
		
		Execution newExecution = executionRepository.save(execution.get());

		return Response.ok(ExecutionMapper.toDto(newExecution)).build();
	}

	@GET
	public Response findAll() {
		Iterable<Algorithm> algorithms = algorithmRepository.findAll();
		
		List<AlgorithmDto> dtos = StreamSupport.stream(algorithms.spliterator(), false)
		.map(AlgorithmMapper::toDto).collect(Collectors.toList());
		
		
		return Response.ok(dtos).build();
	}
}
