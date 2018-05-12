package com.dev.challenge;

import com.dev.challenge.ApiMethods.StoreApiMethods;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class CreateOrderInStore extends StoreApiMethods {

    @Test
    public void main() throws UnirestException {
        String id = createPet("100500"); //input ID value of pet for creating pet with specific ID or left empty for creating it with default ID from server
        checkCreatedPet(id);

        String idOrder=createOrder(id,"200"); //input ID value of pet for creating order and input order ID for using specific value or leave empty for creating it with default Id from server
        checkCreatedOrder(idOrder);

    }
}