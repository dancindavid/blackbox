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
@Path("/health")
@Produces("application/json")
@Consumes("application/json")
public class Health {
	
	@GET
	public String getHealth() {
		return "{I'm alive}";
	}
	

}
