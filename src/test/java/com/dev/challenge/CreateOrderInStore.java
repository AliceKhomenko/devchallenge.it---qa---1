package com.dev.challenge;

import com.dev.challenge.ApiMethods.StoreApiMethods;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class CreateOrderInStore extends StoreApiMethods {

    @Test
    public void main() throws UnirestException {
        String petId = createPet(); //input ID value of pet for creating pet with specific ID or left empty for creating it with default ID from server
        checkCreatedPet(petId);

        String orderId=createOrder(petId); //input ID value of pet for creating order and input order ID for using specific value or leave empty it for creating it with default Id from server
        checkCreatedOrder(orderId);

    }
}