package com.mycompany.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

//@Resource(name="service", lookup="java:comp/DefaultManagedExecutorService", type=javax.enterprise.concurrent.ManagedExecutorService.class)

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Execution {
	@NonNull
	final UUID uuid = UUID.randomUUID();

	@NonNull
	final Algorithm algorithm;
	
	@JsonIgnore
	@NonNull
	final ExecutorService service;
	@NonNull
	final Device device;

	Optional<Damage> damage = Optional.empty();

	@Builder.Default
	boolean waiting = false;
	@Builder.Default
	boolean done = false;
	@Builder.Default
	boolean running = false;
	
	void run() {
		setWaiting(true);
		
		service.submit(() -> {
			
			setWaiting(false);
			setRunning(true);

			var begin = System.currentTimeMillis();
			Optional<Damage> localDamage = Optional.ofNullable(algorithm.getDamageCalculation().apply(device));
			var end = System.currentTimeMillis();

			localDamage.get().setElapsedTime(end - begin);
			setDamage(localDamage);
			setRunning(false);
			setDone(true);
		});
	}
}
