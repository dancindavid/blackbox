package com.mycompany.domain;

import java.util.concurrent.ExecutorService;
import java.util.function.Function;

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
	
	public Execution runManaged(ExecutorService service, Device device) {
		Execution execution = new Execution(this, service, device);
		execution.runManaged();
		
		return execution;
	}
	
	public Execution runUnmanaged(ExecutorService service, Device device) {
		Execution execution = new Execution(this, service, device);
		execution.runUnmanaged();
		
		return execution;
	}
}
