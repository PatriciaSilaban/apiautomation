package stepdefinitions;

import java.util.Map;

import org.testng.Assert;

import com.apiautomation.model.ResponseItem;
import com.apiautomation.model.request.RequestItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.DataRequest;

public class StepDefinitionsImpl {
    /*
     * A list of products are available
     * I add a new product to the etalase
     * The product is available
     */

    ResponseItem responseItem;
    DataRequest dataRequest;
    String json;
    RequestItem requestItem;
    Response response;

    // Step 1
    @Given("A list of products are available")
    public void getAllProduct() {
        System.out.println("getAllProducts");
        RestAssured.baseURI = "https://dummyjson.com";
        RequestSpecification requestSpecification = RestAssured
                .given();

        Response response = requestSpecification
                .log()
                .all()
                .when()
                .get("products");

        System.out.println("Hasilnya adalah" + response.asPrettyString());
    }

    // Step 2: Add new product (Hardcode JSON)
    @When("I add a new product to the etalase")
    public void addNewProduct() {
        System.out.println("Add new product");
        String json = "{\n" + //
                "  \"id\": 1,\n" + //
                "  \"title\": \"Le minerale\",\n" + //
                "  \"description\": \"The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.\",\n"
                + //
                "  \"category\": \"food\",\n" + //
                "  \"price\": 10000,\n" + //
                "  \"discountPercentage\": 5,\n" + //
                "  \"rating\": 5,\n" + //
                "  \"stock\": 15,\n" + //
                "  \"tags\": [\n" + //
                "    \"beauty\",\n" + //
                "    \"mascara\"\n" + //
                "  ],\n" + //
                "  \"dimensions\": {\n" + //
                "    \"width\": 23.17,\n" + //
                "    \"height\": 14.43,\n" + //
                "    \"depth\": 28.01\n" + //
                "  }\n" + //
                "}";

        RestAssured.baseURI = "https://dummyjson.com";
        RequestSpecification requestSpecification = RestAssured
                .given();

        Response response = requestSpecification
                .log()
                .all()
                .pathParam("path", "products")
                .pathParam("method", "add")
                .body(json)
                .contentType("application/json")
                .when()
                .post("{path}/{method}");

        System.out.println("add product" + response.asPrettyString());

        // Validation
        JsonPath addJsonPath = response.jsonPath();
        responseItem = addJsonPath.getObject("", ResponseItem.class);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(responseItem.title, "Le minerale");
        Assert.assertEquals(responseItem.price, 10000);
        Assert.assertEquals(responseItem.discountPercentage, 5);
        Assert.assertEquals(responseItem.stock, 15);
        Assert.assertEquals(responseItem.category, "food");
    }

    // Step 2: Add new product (dynamic payload)
    @When("I add a new {string} to the etalase")
    public void addNewProducts(String payload) throws JsonMappingException, JsonProcessingException {

        dataRequest = new DataRequest();
        // System.out.println("Add new product-1" + payload);

        RestAssured.baseURI = "https://dummyjson.com";
        RequestSpecification requestSpecification = RestAssured
                .given();

        for (Map.Entry<String, String> entry : dataRequest.addItemCollection().entrySet()) {
            if (entry.getKey().equals(payload)) {
                json = entry.getValue();
                break;
            }
        }

        Response response = requestSpecification
                .log()
                .all()
                .pathParam("path", "products")
                .pathParam("method", "add")
                .body(json)
                .contentType("application/json")
                .when()
                .post("{path}/{method}");

        // System.out.println("add products" + response.asPrettyString());

        // Object mapper untuk Convert JSON to POJO

        ObjectMapper requestAddItem = new ObjectMapper();
        requestItem = requestAddItem.readValue(json, RequestItem.class);

        // Validation
        JsonPath addJsonPath = response.jsonPath();
        responseItem = addJsonPath.getObject("", ResponseItem.class);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(responseItem.title, requestItem.title);
        Assert.assertEquals(responseItem.price, requestItem.price);
        Assert.assertEquals(responseItem.discountPercentage, requestItem.discountPercentage);
        Assert.assertEquals(responseItem.stock, requestItem.stock);
        Assert.assertEquals(responseItem.category, requestItem.category);

        System.out.println("ini adalah payload" + payload);
        System.out.println(requestItem.title);
        System.out.println(requestItem.price);
        System.out.println(requestItem.discountPercentage);
        System.out.println(requestItem.stock);
        System.out.println(requestItem.category);
    }

    // Step 3 : Get single product
    @Then("The product is available")
    public void getSingleProduct() {
        System.out.println("get single product");

        RestAssured.baseURI = "https://dummyjson.com";
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                .log()
                .all()
                .pathParam("idProduct", 1)
                .pathParam("path", "products")
                .when()
                .get("{path}/{idProduct}");
        System.out.println("ini adalah response" + response.asPrettyString());
        // Validation
    }
}
