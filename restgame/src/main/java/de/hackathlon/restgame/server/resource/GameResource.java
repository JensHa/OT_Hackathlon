package de.hackathlon.restgame.server.resource;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hackathlon.restgame.server.GenericPair;
import de.hackathlon.restgame.server.Pair;
import de.hackathlon.restgame.server.StaticObjects;

@Path("/Game")
public class GameResource {
 
    @GET
    @Path("/amIinARunningGame")
    @Produces(MediaType.TEXT_PLAIN)
    public Response amIinARunningGame(@Context HttpServletRequest htr) {
    	
    	for(Entry<String, String> entry:StaticObjects.runningGames.entrySet()){
    		
    		if(entry.getKey().toString().equals(htr.getRemoteHost().toString())||entry.getValue().equals(htr.getRemoteHost().toString())){
 
    			return Response.ok("yes").build();
    		}
    	
    	}
    	return Response.ok("no").build();
    }
    
	
    @GET
    @Path("/getStateOfBoard")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStateOfBoard(@Context HttpServletRequest htr) {
    	
    	for(Entry<String, String> entry:StaticObjects.runningGames.entrySet()){
    		
    		if(entry.getKey().toString().equals(htr.getRemoteHost().toString())||entry.getValue().equals(htr.getRemoteHost().toString())){
    			return Response.ok("yes").build();
    		}
    	
    	}
    	return Response.ok("no").build();
    }
    
    
    
    
}