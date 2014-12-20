package de.hackathlon.restgame.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyServer {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(server, "/",ServletContextHandler.SESSIONS);
		ServletHolder sh = context.addServlet(ServletContainer.class, "/*");
		sh.setInitParameter("jersey.config.server.provider.packages","de.hackathlon.restgame.server.resource");
		server.start();
		
		ConnectionManager.startConnectionManager();

		
	}

}
