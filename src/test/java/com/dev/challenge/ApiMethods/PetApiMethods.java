package com.dev.challenge.ApiMethods;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.junit.Assert;



public class PetApiMethods {
    private HttpResponse<JsonNode> creatingResponse;
    private HttpResponse<JsonNode> checkingResponse;
    private HttpResponse<JsonNode> updatingResponse;
    private HttpResponse<JsonNode> deletingResponse;

    private JSONObject jsonObj = new JSONObject("{\n" +
            "  \"id\": 0,\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"pig\"\n" +
            "  },\n" +
            "  \"name\": \"Pinky\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 0,\n" +
            "      \"name\": \"Cartman\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}");



    protected String createPet() throws UnirestException {
        System.out.println("Create a pet");


        creatingResponse = Unirest.post("http://petstore.swagger.io/v2/pet")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(jsonObj.toString())
                .asJson();
        checkResponseStatus(creatingResponse, 200);
        getResponse(creatingResponse);


        String id = String.valueOf(creatingResponse.getBody().getObject().get("id"));
        System.out.println("The created pet with ID=" + id);
        return id;
    }

    protected String createPet(String id) throws UnirestException {
        System.out.println("Create a pet");
        jsonObj.put("id", id);
        creatingResponse = Unirest.post("http://petstore.swagger.io/v2/pet")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(jsonObj.toString())
                .asJson();

        checkResponseStatus(creatingResponse, 200);
        getResponse(creatingResponse);

        return id;
    }

    protected void checkCreatedPet(String petID) throws UnirestException {
        System.out.println("Check a pet after creating");

        checkingResponse = Unirest.get("http://petstore.swagger.io/v2/pet/" + petID)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .asJson();

        checkResponseStatus(checkingResponse, 200);
        getResponse(checkingResponse);


        String json = creatingResponse.getBody().getObject().toString();
        String jsonToCompare = checkingResponse.getBody().getObject().toString();
        Assert.assertEquals(json, jsonToCompare);
        System.out.println("The pet with ID=" + petID + " was created successfully");
    }

    protected void updatePetName(String id, String newName) throws UnirestException {
        System.out.println("Update pet name to " + newName);
        jsonObj.put("id", id);
        jsonObj.put("name", newName);
        updatingResponse = Unirest.put("http://petstore.swagger.io/v2/pet")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(jsonObj.toString())
                .asJson();


        getResponse(updatingResponse);
        checkResponseStatus(updatingResponse, 200);


        String jsonToCompare = updatingResponse.getBody().getObject().getString("name");
        Assert.assertEquals(newName, jsonToCompare);

    }

    protected void deletePet(String id) throws UnirestException {

        System.out.println("Delete pet with ID=" + id);
        deletingResponse = Unirest.delete("http://petstore.swagger.io/v2/pet/" + id)
                .header("accept", "application/json")
                .header("content-type", "application/json").asJson();

        checkResponseStatus(deletingResponse, 200);
        getResponse(deletingResponse);
    }

    protected void checkPetAfterDeleting(String id) throws UnirestException {
        System.out.println("Check pet with ID=" + id + " after deleting");
        checkingResponse = Unirest.get("http://petstore.swagger.io/v2/pet/" + id)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .asJson();

        checkResponseStatus(checkingResponse, 404);
        getResponse(checkingResponse);

        String actualMessage = checkingResponse.getBody().getObject().getString("message");
        String expectedMessage = "Pet not found";
        Assert.assertEquals(expectedMessage, actualMessage);

    }


    protected void checkResponseStatus(HttpResponse<JsonNode> response, int expectedCode) {
        System.out.println("Response status is " + response.getStatus());
        Assert.assertEquals(expectedCode, response.getStatus());

    }

    protected void getResponse(HttpResponse<JsonNode> response) {
        System.out.println("The response body is:");
        System.out.println(response.getBody().getObject().toString(2));

    }
}
