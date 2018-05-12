package com.dev.challenge;

import com.dev.challenge.ApiMethods.StoreApiMethods;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class CreateOrderInStore extends StoreApiMethods {

    @Test
    public void main() throws UnirestException {
        String id = createPet("100500");
        checkCreatedPet(id);

        String idOrder=createOrder(id);
        checkCreatedOrder(idOrder);

    }
}