package de.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class TestClient {
	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		Response entity = client.target("http://0.0.0.0:8080/")
				.path("testClass/test").queryParam("greeting", "Hi World!")
				.request(MediaType.TEXT_PLAIN_TYPE)
				.header("some-header", "true").get();

		System.out.println(entity.readEntity(String.class));

	}

}
