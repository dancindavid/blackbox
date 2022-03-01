package com.mycompany.domain;

import java.util.function.Function;

import javax.enterprise.concurrent.ManagedExecutorService;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NonNull;

@Data
public class Algorithm {

	@NonNull
	final String key;
	
	@JsonIgnore
	@NonNull
	final Function<Device, Damage> damageCalculation;
	
	public Execution run(ManagedExecutorService service, Device device) {
		Execution execution = new Execution(this, service, device);
		execution.run();
		
		return execution;
	}
}
