package com.mycompany.domain.mappers;

import java.util.Optional;
import java.util.function.Function;

import javax.ejb.EJB;

import com.mycompany.domain.Algorithm;
import com.mycompany.repositories.AlgorithmRepository;
import com.mycompany.shareddomain.dtos.AlgorithmDto;

public class AlgorithmMapper {
//	@EJB
//	private static AlgorithmRepository algorithmRepository;
	
	public static AlgorithmDto toDto(Algorithm a) {
		if(a == null) { return null; }
		
		AlgorithmDto dto = AlgorithmDto.builder()
				.key(a.getKey())
				.build();
		
		return dto;
	}
	
//	public static Optional<Algorithm> fromDto(AlgorithmDto dto) {
//		Optional<Algorithm> algorithm = algorithmRepository.findById(dto.getKey());
//		
//		
//		return algorithm;
//	}
//	
}
