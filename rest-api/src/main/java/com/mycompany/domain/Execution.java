package com.mycompany.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;

//@Resource(name="service", lookup="java:comp/DefaultManagedExecutorService", type=javax.enterprise.concurrent.ManagedExecutorService.class)
@Data
public class Execution {
	UUID uuid = UUID.randomUUID();

	@NonNull
	final Algorithm algorithm;
	
	@JsonIgnore
	@NonNull
	final ExecutorService service;
	@NonNull
	final Device device;

	Damage damage;

	boolean done = false;
	boolean running = false;
	boolean managedThreading;

	public void runManaged() {
		managedThreading = true;
		run();
	}
	
	public void runUnmanaged() {
		managedThreading = false;
		run();
	}
	
	void run() {
		service.submit(() -> {
			Instant start;
			Instant end;
			setDone(false);
			setRunning(true);

			start = Instant.now();
			Damage localDamage = algorithm.getDamageCalculation().apply(device);
			end = Instant.now();

			localDamage.setTimeElapsed(Duration.between(start, end).toString());
			setDamage(localDamage);
			setRunning(false);
			setDone(true);
			return localDamage;
		});
	}
}
