package com.mycompany.repository;

import java.util.List;
import java.util.UUID;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Execution;

public interface AlgorithmRepository {	
	Algorithm create();
	Algorithm getById(UUID uuid);
	Algorithm save(Algorithm algorithm);
	Algorithm deleteById(UUID uuid);
	Execution executeById(UUID uuid);
	List<Algorithm> getAll();
	
}
