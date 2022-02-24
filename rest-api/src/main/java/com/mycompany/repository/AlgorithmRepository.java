package com.mycompany.repository;

import java.util.Optional;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Execution;

public interface AlgorithmRepository {	
	Optional<Algorithm> findById(String key);
	Algorithm save(Algorithm algorithm);
	void deleteById(String key);
	Iterable<Algorithm> findAll();
	
}
