package com.dev.challenge;

import com.dev.challenge.ApiMethods.PetApiMethods;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;

public class CreateReadUpdateDelete extends PetApiMethods {

    @Test
    public void main() throws UnirestException {

        String petId = createPet("100");
        checkCreatedPet(petId);
        updatePetName(petId, "Fluffy");
        deletePet(petId);
        checkPetAfterDeleting(petId);

    }


}
