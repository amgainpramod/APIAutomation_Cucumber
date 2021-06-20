package stepdefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        //Execute this code only when place_id is null
        //Write a code that will give you place_id

        StepDefs stepDefs = new StepDefs(); //call static variable with Class Name not with Object
        if (StepDefs.place_id == null) {
            stepDefs.addPlacePayloadWithAnd("Earth", "Earth, Milky Way Galaxy", "Earthing");
            stepDefs.userCallsWithHttpRequest("addPlaceAPI", "POST");
            stepDefs.verifyCreatedMapsToUsing("place_id", "Earth", "getPlaceAPI");
        }
    }
}
