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
    public Response getOpenRequests(@Context HttpServletRequest htr) {
    	
    	for(Entry<String, String> entry:StaticObjects.runningGames.entrySet()){
    		
    		if(entry.getKey().toString().equals(htr.getRemoteHost().toString())||entry.getValue().equals(htr.getRemoteHost().toString())){
    			return Response.ok("yes").build();
    		}
    	
    	}
    	return Response.ok("no").build();
    }
    
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/inviteUser")
	public Response inviteUser(@Context HttpServletRequest htr,String opponent){
		
		if(StaticObjects.gameInvitations.containsKey(htr.getRemoteHost().toString())){
			return Response.status(400).entity("only one open request is allowed!").build();
		}
		StaticObjects.gameInvitations.put(htr.getRemoteHost().toString(),new GenericPair<String, Boolean>(opponent, false));
		
		
		System.out.println("Server: "+htr.getRemoteHost().toString()+" invites "+ opponent + " to play!");
		return Response.ok().build();
	}
    
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/Login")
	public Response loginForUser(@Context HttpServletRequest htr,String test){
		
		
		StaticObjects.users.put(htr.getRemoteHost().toString(),new GenericPair<Long, Boolean>(System.currentTimeMillis(), false));
		
		
		System.out.println("Server: "+htr.getRemoteHost().toString()+" logged in!");
		return Response.ok().build();
	}
	
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/answerToRequest")
	public Response answerToRequest(@Context HttpServletRequest htr,String opponentAndAnswer){
		StringTokenizer st = new StringTokenizer(opponentAndAnswer,";");
		String answer=st.nextToken();
		String opponent=st.nextToken();
		
		//opponent hat eingeladen
		System.out.println(htr.getRemoteHost().toString()+" "+ answer +" with " + opponent);
		
		if(answer.equals("no")){
			StaticObjects.gameInvitations.remove(opponent);
			StaticObjects.responsesToInvitations.put(opponent, "no");
			System.out.println("Server: "+htr.getRemoteHost().toString() +"declined a game with " + opponent);
		}else if(answer.equals("yes")){
			StaticObjects.gameInvitations.remove(opponent);
			StaticObjects.runningGames.put(opponent, htr.getRemoteHost().toString());
			StaticObjects.responsesToInvitations.put(opponent, "yes");
			System.out.println("Server: "+htr.getRemoteHost().toString() +"accepted a game with " + opponent);
		}
		
		return Response.ok().build();
	}
	
    @GET
    @Path("/getAnswersToRequest")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAnswersToRequest(@Context HttpServletRequest htr) {
    
    	for(Entry<String, String> entry:StaticObjects.responsesToInvitations.entrySet()){
    		if(entry.getKey().equals(htr.getRemoteHost().toString())){
    			Response returnValue=Response.ok(entry.getValue()).build();
    			StaticObjects.responsesToInvitations.remove(entry.getKey());
    			return returnValue;
    		}
    	}

    	
    	return Response.ok().build();
    }
    
    
    
}