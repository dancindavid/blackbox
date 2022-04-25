package com.mycompany.restresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

@Path("/health")
public class HealthResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getHealth() {
		JSONObject json = new JSONObject();
		json.append("status", "ok");
		return json.toString();
	}
}
