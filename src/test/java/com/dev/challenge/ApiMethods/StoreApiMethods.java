package com.dev.challenge.ApiMethods;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreApiMethods extends PetApiMethods {
    private HttpResponse<JsonNode> creatingResponse;
    private HttpResponse<JsonNode> checkingResponse;


    private JSONObject jsonObj2 = new JSONObject("{\n" +
            "  \"id\": 0,\n" +
            "  \"petId\": 0,\n" +
            "  \"quantity\": 0,\n" +
            "  \"shipDate\": \""+getCurrentDate()+"\",\n" +
            "  \"status\": \"placed\",\n" +
            "  \"complete\": true\n" +
            "}");

    /**
     * Create a new order with selected pet
     *
     *
     * @param petID
     * @return
     * @throws UnirestException
     */


    protected String createOrder(String petID) throws UnirestException {
        jsonObj2.put("petId",petID);
        creatingResponse = Unirest.post("http://petstore.swagger.io/v2/store/order")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(jsonObj2.toString())
                .asJson();
        checkResponseStatus(creatingResponse, 200);
        getResponse(creatingResponse);
        String idOrder=String.valueOf(creatingResponse.getBody().getObject().get("id"));
        return idOrder;

    }

    /**
     *
     * create a new order with selected pet and selected ID
     *
     *
     * @param petID
     * @param idOrder
     * @return
     * @throws UnirestException
     */
    protected String createOrder(String petID, String idOrder) throws UnirestException {

        System.out.println(jsonObj2.toString(2));
        System.out.println("Creating an order");
        jsonObj2.put("id",idOrder);
        jsonObj2.put("petId",petID);
        creatingResponse = Unirest.post("http://petstore.swagger.io/v2/store/order")
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(jsonObj2.toString())
                .asJson();
        checkResponseStatus(creatingResponse, 200);
        getResponse(creatingResponse);
        return idOrder;

    }


    /**
     *
     * Check created order with selected ID
     * @param idOrder
     * @throws UnirestException
     */
    protected void checkCreatedOrder(String idOrder) throws UnirestException {
        System.out.println("Check the order after creating");

        checkingResponse = Unirest.get("http://petstore.swagger.io/v2/store/order/" + idOrder)
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .asJson();

        checkResponseStatus(checkingResponse, 200);
        getResponse(checkingResponse);


        String json = creatingResponse.getBody().getObject().toString();
        String jsonToCompare = checkingResponse.getBody().getObject().toString();
        Assert.assertEquals(json, jsonToCompare);
        System.out.println("The order with ID=" + idOrder + " was created successfully");
    }
    /**
     *
     * Return current date un expected format
     *
     * @return
     */
    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


}