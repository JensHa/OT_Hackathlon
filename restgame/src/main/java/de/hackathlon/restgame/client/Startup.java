package de.hackathlon.restgame.client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;

public class Startup {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService pool = Executors.newCachedThreadPool();
		final Future<Client> jerseyClient = pool.submit(new ClientInitCallable());
		final TTTClient client = new TTTClient(jerseyClient);
	
		
		pool.submit(new Runnable() {
			
			public void run() {
				GUI_Login frame = new GUI_Login(client);
				frame.setVisible(true);				
			}
		});


	}

}
