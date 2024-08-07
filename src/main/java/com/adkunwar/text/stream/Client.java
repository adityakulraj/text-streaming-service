package com.adkunwar.text.stream;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client {
    private static final String BASE_URL = "http://localhost:8080/api/stream";

    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        // Switch provider
        HttpRequest switchRequest = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/switch-provider?provider=ProviderA"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();
        client.send(switchRequest, HttpResponse.BodyHandlers.ofString());

        // Stream text
        HttpRequest streamRequest = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/text?text=Hello"))
                .build();

        HttpResponse<String> response = client.send(streamRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
