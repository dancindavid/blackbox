package com.mycompany.domain;

import java.io.Serializable;
import java.util.UUID;
import java.util.function.Function;

import lombok.Data;
import lombok.NonNull;

@Data
public class Algorithm {
	final UUID id = UUID.randomUUID();
	@NonNull
	final String name;
	
	@NonNull
	final Function<Tspi, Damage> damageCalculation;
	
	public Execution run() {
		Execution execution = new Execution(this);
		
		return execution;
	}
}
