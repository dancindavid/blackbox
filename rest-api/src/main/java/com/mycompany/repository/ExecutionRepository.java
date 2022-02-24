package com.mycompany.repository;

import java.util.Optional;
import java.util.UUID;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Execution;

public interface ExecutionRepository {
	Optional<Execution> findById(UUID uuid);
	Execution save(Execution execution);
	void deleteById(UUID uuid);
	Iterable<Execution> findAll();
}

