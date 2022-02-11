package com.mycompany.repository;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;

import com.mycompany.domain.Algorithm;
import com.mycompany.domain.Execution;

@Stateless
public class AlgorithmRepositoryBean implements AlgorithmRepository {

	@Override
	public Algorithm create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Algorithm getById(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Algorithm save(Algorithm algorithm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Algorithm deleteById(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Execution executeById(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Algorithm> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
