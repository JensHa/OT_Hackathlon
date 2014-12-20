package de.hackathlon.restgame.server.resource;

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
import de.hackathlon.restgame.server.StaticObjects;

@Path("/Game")
public class GameResource {
 
	/**
	 * This method is used if the user wants to check if the condition for a game is complied => two users want to play a game with each other
	 * @param htr
	 * @return
	 */
    @GET
    @Path("/amIinARunningGame")
    @Produces(MediaType.TEXT_PLAIN)
    public Response amIinARunningGame(@Context HttpServletRequest htr) {
    	
    	for(Entry<String, String> entry:StaticObjects.runningGames.entrySet()){
    		
    		if(entry.getKey().toString().equals(htr.getRemoteHost().toString())||entry.getValue().equals(htr.getRemoteHost().toString())){
 
    			String player="";
    			String boardID=entry.getKey()+entry.getValue();
    			if(boardID.startsWith(htr.getRemoteHost().toString())){
    				player="player1";
    			}else{
    				player="player2";
    			}
    			System.out.println(entry.getKey()+entry.getValue());
    			StaticObjects.boardsState.put(boardID, new GenericPair<String[], Boolean>(new String[]{"-","-","-","-","-","-","-","-","-",}, true));
    			return Response.ok("yes;"+entry.getKey()+entry.getValue()+";"+player).build();
    		}
    	
    	}
    	return Response.ok("no;no").build();
    }
    
	
  /**
   * This method is used to get the current state of a game. So you will get the "X"s and "O"s of the board and the player which is currently activ.
   * You will get last mentioned condition from the included boolean-value. true = turn of player1 false=turn of player2 
   * @param htr
   * @return
   */
  @GET
  @Path("/getStateOfBoard")
  @Produces(MediaType.TEXT_PLAIN)
  public Response getStateOfBoard(@Context HttpServletRequest htr) {
  	
	  
	  for(Entry<String, GenericPair<String[], Boolean>> entry:StaticObjects.boardsState.entrySet()){
		  if(entry.getKey().contains(htr.getRemoteHost().toString())){
			  String command="";
			  for(int i=0; i<entry.getValue().getFirstValue().length;i++){
				  command+=entry.getValue().getFirstValue()[i]+";";
			  }
			  command+=entry.getValue().getSecondValue().toString();
			  return Response.ok(command).build();
		  }
	  }
  	return Response.ok("no").build();
  }
    /**
     * This method is used if a user finished his/her move. The server will get the result of this move.
     * @param htr
     * @param input
     * @return
     */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/postAnEntry")
	public Response postAnEntry(@Context HttpServletRequest htr,String input){
		
		StringTokenizer st = new StringTokenizer(input,";");
		
		int noOfBt=Integer.valueOf(st.nextToken());
		String value=st.nextToken();
		
		  for(Entry<String, GenericPair<String[], Boolean>> entry:StaticObjects.boardsState.entrySet()){
			  if(entry.getKey().contains(htr.getRemoteHost().toString())){
					System.out.println("its the turn of" + entry.getValue().getSecondValue().toString());

				  entry.getValue().getFirstValue()[noOfBt]=value;
			  }
			  
			  if(value.equals("X")){
				  entry.getValue().setSecondValue(false);
			  }else{
				  entry.getValue().setSecondValue(true);

			  }
		  }
		  return Response.ok().build();

	
	}
    
}