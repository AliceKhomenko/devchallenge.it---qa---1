package com.dev.challenge;

import com.dev.challenge.ApiMethods.PetApiMethods;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class CreateReadUpdateDeletePet extends PetApiMethods {

    @Test
    public void main() throws UnirestException {

        String petId = createPet("100"); //input ID value of pet for creating pet with specific ID or leave empty for creating it with default Id from server
        checkCreatedPet(petId);
        updatePetName(petId, "Fluffy");
        deletePet(petId);
        checkPetAfterDeleting(petId);

    }


}
