package com.mycompany.domain;

import java.util.function.Function;

public class DamageCalculation {
	public static Function<Device, Damage> sqrt = 
			(device) -> {
				Damage damage = new Damage();
				for(int i = 0; i < device.getCount(); ++i) {
					damage.setValue(Math.sqrt(device.getValue()));
				}
		
				return damage;
			};
}
