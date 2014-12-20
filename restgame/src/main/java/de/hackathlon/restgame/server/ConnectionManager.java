package de.hackathlon.restgame.server;

import java.util.Map.Entry;

public class ConnectionManager {
	
	public static void startConnectionManager(){
		new Thread(new Runnable() {

			public void run() {
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Server: Checking for inactive users!");
					for (Entry<String, GenericPair<Long, Boolean>> entry : StaticObjects.users.entrySet()) {
						if (System.currentTimeMillis() - entry.getValue().getFirstValue() > 10000) {
							StaticObjects.users.remove(entry.getKey());
							StaticObjects.gameInvitations.remove(entry.getKey());
							System.out.println("Server: User " + entry.getKey() + " was removed due inactivity!");
						}
					}
				}
			}
		}).start();
	}

}
