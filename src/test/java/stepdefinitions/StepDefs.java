package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import testresources.APIResources;
import testresources.TestDataBuild;
import testresources.TestSpecBuild;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefs extends TestSpecBuild {
    RequestSpecification requestSpec;
    Response response;
    static String place_id;
    TestDataBuild dataBuild = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} and {string}")
    public void addPlacePayloadWithAnd(String name, String address, String language) throws IOException {
        //splitting into request
        requestSpec = given()
                        .spec(requestSpecification())
                        .body(dataBuild.addPlacePayload(name, address, language)); //passing the object of AddPlace in the body
    }

    @When("User calls {string} with {string} Http request")
    public void userCallsWithHttpRequest(String resource, String httpMethod) {

        //splitting into response
        //Constructor will be called from APIResources class with the value of resource passed
        APIResources resourceAPI = APIResources.valueOf(resource);
        resourceAPI.getResource();

        if (httpMethod.equalsIgnoreCase("POST")) {
            response = requestSpec
                    .when()
                        .post(resourceAPI.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = requestSpec
                    .when()
                        .get(resourceAPI.getResource());
        }
//                        response.then()
//                        .spec(responseSpecification())
//                        .extract().response();
    }

    @Then("API call is success with status code {int}")
    public void apiCallIsSuccessWithStatusCode(int arg0) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String path, String expectedValue) { // <-- String status, String OK and String scope, String APP
        String actualValue = getJsonPath(response, path);
        Assert.assertEquals(actualValue, expectedValue);
    }

    @And("verify {string} created maps to {string} using {string}")
    public void verifyCreatedMapsToUsing(String path, String placeName, String resource) throws IOException {
        place_id = getJsonPath(response, path);
        System.out.println(place_id);

        requestSpec = requestSpec.queryParam("place_id", place_id);
        userCallsWithHttpRequest(resource, "get");

        String shopName = getJsonPath(response, "name");
        Assert.assertEquals(shopName, placeName);
        System.out.println(placeName);
        System.out.println(shopName);

    }

    @Given("Delete Place Payload")
    public void deletePlacePayload() throws IOException {
        requestSpec = given().
                        spec(requestSpecification()).
                        body(dataBuild.deletePlacePayload(place_id));

    }
}
