package com.mycompany.domain;

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
	
	public Execution run(Device device) {
		Execution execution = new Execution(this);
		
		execution.run(device);
		
		return execution;
	}
}
