package com.mycompany.domain.mappers;

import com.mycompany.domain.Device;
import com.mycompany.shareddomain.dtos.DeviceDto;

public class DeviceMapper {

	public static DeviceDto toDto(Device d) {
		if(d == null) { return null; }
		
		DeviceDto dto = DeviceDto.builder()
				.count(d.getCount())
				.value(d.getValue())
				.build();
		
		return dto;
	}
	
	public static Device fromDto(DeviceDto dto) {
		Device device = Device.builder().count(dto.getCount()).value(dto.getValue()).build();
		
		return device;
		
	}
}
