package com.mycompany.domain;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Damage {
	double value;
	String timeElapsed;
}
