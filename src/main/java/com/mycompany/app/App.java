package com.mycompany.app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class App 
{
    public static void main( String[] args ) throws JsonMappingException, JsonProcessingException, IOException, InterruptedException
    {
        String email = "EMAIL-OR-USERNAME-HERE"; // replace with email or username given to you
        String password = "PASSWORD-HERE"; // replace with password given to you

        // Step 1: Login
        String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://challenger.code100.dev/login"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();
        
        HttpResponse<String> response = null;
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            System.out.println("Login failed");
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(response.body());
        String token = json.get("token").asText();
        System.out.println("Token: " + token);

        // Step 2: Get the puzzle
        request = HttpRequest.newBuilder()
            .uri(URI.create("https://challenger.code100.dev/getpuzzle"))
            .header("Authorization", "Bearer " + token)
            .GET()
            .build();

        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Puzzle: " + response.body());

        // Step 3: Solve the puzzle

        ////////////////////////////
        ////// YOUR CODE HERE //////
        ////////////////////////////

        String answerBody = "{ \"answer\": \"your answer here in requested format\" }";


        // Step 4: Submit the solution
        request = HttpRequest.newBuilder()
            .uri(URI.create("https://challenger.code100.dev/postanswer"))
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(answerBody))
            .build();

        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Result: " + response.body());

    }
}
