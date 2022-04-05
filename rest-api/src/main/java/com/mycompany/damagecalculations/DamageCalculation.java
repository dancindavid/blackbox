package com.mycompany.damagecalculations;

import java.util.function.Function;

import com.mycompany.domain.Damage;
import com.mycompany.domain.Device;


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
