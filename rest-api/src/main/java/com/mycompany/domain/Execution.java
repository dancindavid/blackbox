package com.mycompany.domain;

import java.util.UUID;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;

@Data
public class Execution {
	UUID uuid = UUID.randomUUID();
	@NonNull
	final Algorithm algorithm;
	
	boolean done = false;
	boolean running = false;
	
	@JsonIgnore
	Future<Damage> future;
	
	@JsonIgnore
	@Resource
	ManagedExecutorService service;

	public Future<Damage> run(Device device) {		
		Future<Damage> localFuture = service.submit(() -> { 
			setDone(false);
			setRunning(true);
			Damage damage = algorithm.getDamageCalculation().apply(device); 
			setRunning(false);
			setDone(true);
			return damage;
		});
		
		setFuture(localFuture);
		return localFuture;
	}
}
