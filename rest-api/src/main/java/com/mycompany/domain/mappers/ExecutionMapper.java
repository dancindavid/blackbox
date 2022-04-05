package com.mycompany.domain.mappers;


import com.mycompany.domain.Execution;
import com.mycompany.shareddomain.dtos.ExecutionDto;

public class ExecutionMapper {
	
	
	
	public static ExecutionDto toDto(Execution e) {
		if(e == null) return null;
		
		ExecutionDto dto = ExecutionDto.builder()
				.uuid(e.getUuid())
				.algorithm(AlgorithmMapper.toDto(e.getAlgorithm()))
				.device(DeviceMapper.toDto(e.getDevice()))
				.damage(DamageMapper.toDto(e.getDamage().orElse(null)))
				.waiting(e.isWaiting())
				.done(e.isDone())
				.running(e.isRunning())
				.build();
		
		return dto;
	}
	
//	public static Execution fromDto(ExecutionDto dto) {
//		Execution e = Execution.builder()
//				.algorithm(AlgorithmMapper.fromDto(dto.getAlgorithm()))
//				.device(DeviceMapper.fromDto(dto.getDevice()))
//				.damage(DamageMapper.fromDto(dto.getDamage()))
//				.done(dto.isDone())
//				.running(dto.isRunning())
//				.build();
//		
//		return e;
//		
//	}
}
