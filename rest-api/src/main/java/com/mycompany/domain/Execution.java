package com.mycompany.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.json.bind.annotation.JsonbTransient;

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
	final ManagedExecutorService service;
	@NonNull
	final Device device;

	Damage damage;

	boolean done = false;
	boolean running = false;

	public void run() {
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
