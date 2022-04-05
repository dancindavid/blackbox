package com.mycompany.domain;

import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@RequiredArgsConstructor
public class Algorithm {

	@NonNull
	final String key;
	
	@JsonIgnore
	@NonNull
	final Function<Device, Damage> damageCalculation;
	
	public Execution run(ExecutorService service, Device device) {
		Execution execution = new Execution(this, service, device);
		execution.run();
		
		return execution;
	}
}
