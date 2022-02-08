package com.mycompany.weatherman.api;

import java.util.Hashtable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.mycompany.mainejb.services.LosAngelesWeather;

import javax.ws.rs.GET;


@RequestScoped
@Path("/algorithm")
@Produces("application/json")
@Consumes("application/json")
public class Algorithm {
	
	
	@PUT("{uuid}")
	public String createAlgorithm(UUID uuid) {
		
	}
}
