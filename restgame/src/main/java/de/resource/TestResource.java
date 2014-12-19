package de.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/PreGame")
public class TestResource {
 
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
    	return Response.ok("Hello").build();
    			
        //return "Test";
    }
    
    
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/Login")
	public Response loginForUser(@Context HttpServletRequest htr,String test){
		System.out.println("SERVER: " +test + " from: " +htr.getRemoteHost().toString());
		return Response.ok().build();
	}
}