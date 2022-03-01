package com.mycompany.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.ejb.Singleton;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.DamageCalculation;

import lombok.Data;
import lombok.NonNull;

@Singleton
@Data
public class AlgorithmRepositoryBean implements AlgorithmRepository {
	@NonNull
	Map<String, Algorithm> repository = new HashMap<String, Algorithm>();

	public AlgorithmRepositoryBean() {
		Algorithm sqrtAlgorithm = new Algorithm("sqrt", DamageCalculation.sqrt);

		save(sqrtAlgorithm);
	}

	@Override
	public Optional<Algorithm> findById(String key) {
		Optional<Algorithm> algorithm = Optional.of(repository.get(key));

		return algorithm;
	}

	@Override
	public Algorithm save(Algorithm algorithm) {
		return repository.put(algorithm.getKey(), algorithm);
	}

	@Override
	public void deleteById(String key) {
		repository.remove(key);
	}

	@Override
	public Iterable<Algorithm> findAll() {
		return repository.values();
	}

}
