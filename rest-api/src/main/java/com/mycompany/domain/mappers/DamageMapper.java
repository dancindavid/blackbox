package com.mycompany.domain.mappers;



import java.util.Optional;
import java.util.function.Function;

import com.mycompany.domain.Damage;
import com.mycompany.shareddomain.dtos.DamageDto;

public class DamageMapper {
	
	public static DamageDto toDto(Damage d) {
		if(d == null) { return null; }
		
		DamageDto dto = DamageDto.builder()
					.value(d.getValue())
					.timeElapsed(d.getTimeElapsed())
					.build();
		
		return dto;		
	}
	

//	public static Optional<DamageDto> toDto(Optional<Damage> damage) {
//		Optional<DamageDto> dto = damage.map(
//				d -> DamageDto.builder()
//					.value(d.getValue())
//					.timeElapsed(d.getTimeElapsed())
//					.build());
//		
//		return dto;		
//	}
	
	
	public static Damage fromDto(DamageDto dto) {
		Damage d = Damage.builder().value(dto.getValue()).timeElapsed(dto.getTimeElapsed()).build();
		
		return d;
	}
}
