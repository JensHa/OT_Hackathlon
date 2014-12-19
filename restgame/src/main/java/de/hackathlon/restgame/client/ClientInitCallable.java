package de.hackathlon.restgame.client;

import java.util.concurrent.Callable;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class ClientInitCallable implements Callable<Client>{

	public Client call() throws Exception {
		Client client = ClientBuilder.newClient();
		return client;
	}

}
