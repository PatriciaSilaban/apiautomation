package stepdefinitions;

import com.apiautomation.model.addObjectResponse;
import com.apiautomation.model.getListOfAllObjectResponse;
import com.apiautomation.model.getListSingleObjectResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import apiengine.Assertion;
import apiengine.Endpoints;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import resources.DataRequest;

public class StepDefinition_Implementation {

        Response response;
        Endpoints endpoints;
        DataRequest dataRequest;
        String json;
        String idObject;
        Assertion assertion;

        @BeforeStep
        public void setUp() {
                endpoints = new Endpoints();
                assertion = new Assertion();
        }

        @Given("A list of item are available")
        public void getListOfAllObjects() {
                response = endpoints.getListOfAllObjects("objects");
                System.out.println("response migration" + response.asPrettyString());
                assertion.assertListAllObject(response);
        }

        @When("I add item to list")
        public void addObject() {
                String json = "{\r\n" + //
                                "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                                "   \"data\": {\r\n" + //
                                "      \"year\": 2019,\r\n" + //
                                "      \"price\": 1849.99,\r\n" + //
                                "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                                "      \"Hard disk size\": \"1 TB\"\r\n" + //
                                "   }\r\n" + //
                                "}";
                response = endpoints.addObject("objects", json);
                System.out.println("response migration" + response.asPrettyString());
                addObjectResponse addResponse = response.as(addObjectResponse.class);
                assertion.assertAddObject(addResponse);
        }

        @When("I add item to list {string}")
        public void addNewObject(String payload) throws JsonMappingException, JsonProcessingException {
                dataRequest = new DataRequest();
                String json = "{\r\n" + //
                                "   \"name\": \"Apple MacBook Pro 15\",\r\n" + //
                                "   \"data\": {\r\n" + //
                                "      \"year\": 2019,\r\n" + //
                                "      \"price\": 1849.99,\r\n" + //
                                "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                                "      \"Hard disk size\": \"1 TB\"\r\n" + //
                                "   }\r\n" + //
                                "}";
                response = endpoints.addObject("objects", json);
                System.out.println("response new object migration" + response.asPrettyString());
                addObjectResponse addResponse = response.as(addObjectResponse.class);
                assertion.assertAddObject(addResponse);
        }

        @Then("The item is available")
        public void getListSingleObject() {
                response = endpoints.getSingleObject("objects", "7");
                System.out.println("response migration" + response.asPrettyString());
                getListSingleObjectResponse singleObjectResponse = response.as(getListSingleObjectResponse.class);
                assertion.assertSingleObject(singleObjectResponse);
        }
}