package de.hackathlon.restgame.server.resource;

import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.StyledEditorKit.BoldAction;
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

@Path("/Refresh")
public class RefreshResource {
 
	/**
	 * Use this method to refresh your current user-list
	 * @return
	 */
    @GET
    @Path("/Users")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {
    	String allUsers="";
    	for(Entry<String, GenericPair<Long, Boolean>> user:StaticObjects.users.entrySet()){
    		allUsers+=user.getKey()+";";
    	}
    	return Response.ok(allUsers).build();	
    }
    
    /**
     * Use this method to signal that your client is still active! You have to send at least all 5 seconds this post
     * @param htr
     * @param test
     * @return
     */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/imAlive")
	public Response loginForUser(@Context HttpServletRequest htr){
		
		//this check is neccesary, because the client could not be in the userlist. This happens when the check is done quite after entering the lobby
		if(StaticObjects.users.get(htr.getRemoteHost().toString())!=null){
		//get current state of the client(is he/she in a game or not?). This state is copied into the the new pair.	
		boolean lastState=StaticObjects.users.get(htr.getRemoteHost().toString()).getSecondValue();
		GenericPair<Long, Boolean> pair=new GenericPair<Long, Boolean>(System.currentTimeMillis(),lastState);
		StaticObjects.users.put(htr.getRemoteHost().toString(), pair);
		
		System.out.println("Server: "+htr.getRemoteHost().toString()+" gave an 'alive-post'");
		}
		return Response.ok().build();
	}
}