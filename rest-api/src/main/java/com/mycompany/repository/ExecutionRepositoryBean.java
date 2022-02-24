package com.mycompany.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.ejb.Singleton;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Execution;

import lombok.Data;

@Singleton
@Data
public class ExecutionRepositoryBean implements ExecutionRepository {

	Map<UUID, Execution> repository = new HashMap<UUID, Execution>();

	@Override
	public Optional<Execution> findById(UUID uuid) {
		Optional<Execution> execution = Optional.of(repository.get(uuid));

		return execution;
	}

	@Override
	public Execution save(Execution execution) {
		repository.put(execution.getUuid(), execution);
		return repository.get(execution.getUuid());
	}

	@Override
	public void deleteById(UUID uuid) {
		repository.remove(uuid);
	}
	
	@Override
	public Iterable<Execution> findAll() {
		return repository.values();
	}

}
