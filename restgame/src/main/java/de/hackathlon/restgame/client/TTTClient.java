package de.hackathlon.restgame.client;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;


public class TTTClient {
	Client client;
	Future<Client> clienta;
	
	public TTTClient(Future<Client> jerseyClient){
		clienta=jerseyClient;
	}

	public void setTarget(String ipAdressOfServer) throws InterruptedException, ExecutionException {
		client=clienta.get();
		
		
		UriBuilder builder = UriBuilder.fromUri("http://"+ipAdressOfServer).port(8080);
		URI uri = builder.build();
		
		WebTarget target = client.target(uri).path("testClass/test");
		Response resp=target.request(MediaType.TEXT_PLAIN_TYPE).get();
		System.out.println(resp.readEntity(String.class));
		
//		//client.target(ipAdressOfServer);
//		Response entity=client.target("").path("testClass/test").queryParam("greeting", "Hi World!")
//		.request(MediaType.TEXT_PLAIN_TYPE)
//		.header("some-header", "true").get();

//		Configuration a = client.getConfiguration();
//		System.out.println("im here and my target is" + client.getConfiguration());
	}
	

}
