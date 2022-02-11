package com.mycompany.repository;

import com.mycompany.domain.Execution;

public interface ExecutionRepository {

	Execution create(Execution execution);
	Execution getById(String id);
	Execution save(Execution execution);
	Execution deleteById(String id);
	
}
