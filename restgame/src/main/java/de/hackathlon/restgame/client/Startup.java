package de.hackathlon.restgame.client;

import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.ws.rs.client.Client;

import de.hackathlon.restgame.server.Pair;
import de.hackathlon.restgame.server.StaticObjects;

public class Startup {
	static ExecutorService pool;

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {

		pool = Executors.newCachedThreadPool();
		// Init a client. Result is not available immediately, thus the future-object is needed.
		final Future<Client> futureClient = pool.submit(new ClientInitCallable());
		// the GUI is only working with the TTTClient-Objekt. DONT work on the javax.ws.rs.client.Client
		final TTTClient client = new TTTClient(futureClient);

		// This task is responsible for the GUI-Login. It is done by a thread from the pool - not the main thread.
		// So the main-thread is not hold by the GUI-construction
		pool.submit(new Runnable() {

			public void run() {
				GUI_Login frame = new GUI_Login(client);
				frame.setVisible(true);
			}
		});

		//This task is responsible for the 'alive-signal'. It will send all x seconds a post to the server, signaling that this client is still alive
		pool.submit(new Runnable() {

			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
						while (client.ready == false);
						client.postMethod("Refresh/imAlive",null);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});
	}

}
