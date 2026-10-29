package com.devskillslearning.first_llm_call;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SpringBootApplication
public class FirstLlmCallApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		final String apiKey = System.getenv("OPENAI_API_KEY");

		final String requestBody = """
				{
					"model": "gpt-4o",
					"messages": [
						{
							"role": "system",
							"content": "you are the top movie review expert."
						},
						{
							"role": "user",
							"content": "Name a movie that a software engineer must watch."
						}
					]
				}
				""";

		final HttpClient httpClient = HttpClient.newHttpClient();

		final HttpRequest httpRequest = HttpRequest.newBuilder()
				.uri(URI.create("https://api.openai.com/v1/chat/completions"))
				.header("Authorization", "Bearer " + apiKey)
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(requestBody))
				.build();

		final HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

		System.out.println(httpResponse.body());

		SpringApplication.run(FirstLlmCallApplication.class, args);
	}

}
