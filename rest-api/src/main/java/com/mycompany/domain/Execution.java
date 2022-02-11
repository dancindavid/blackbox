package com.mycompany.domain;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.function.Function;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;

import lombok.Data;
import lombok.NonNull;

@Data
public class Execution implements Serializable {
	final UUID uuid = UUID.randomUUID();
	@NonNull
	final Algorithm algorithm;
	
	boolean done = false;
	boolean running = false;
	Future<Damage> future;
	
	@Resource
	ManagedExecutorService service;

	UUID run(Tspi tspi) {		
		Future<Damage> localFuture = service.submit(() -> { 
			setRunning(true);
			Damage damage = algorithm.getDamageCalculation().apply(tspi); 
			setRunning(false);
			setDone(true);
			return damage;
		});
		
		setFuture(localFuture);
		return getUuid();
	}
}
