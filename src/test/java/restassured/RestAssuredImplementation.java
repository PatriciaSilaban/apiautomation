package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredImplementation {

    public static void main(String[] args) {
        getListOfAllObjects();
        getListOfObjectsByIDS();
        getsingleObject();
        addObject();
        updateObject();
        partiallyUpdateObject();
        deleteObject();
    }

    public static void getListOfAllObjects() {
        /*
         * Define baseURI = https://api.restful-api.dev/objects
         */

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all().get("objects");
        System.out.println("Hasilnya adalah" + response.asPrettyString());
    }

    public static void getListOfObjectsByIDS() {
        /*
         * Define baseURI = https://api.restful-api.dev/objects?id=3&id=5&id=10
         */

         /*
          * Karena ini termasuk dengan query param jadi bisa pakai 
          .queryparam y
          */

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification
                            .log()
                            .all()
                            .queryParam("id", 3,5,10)
                            .pathParam("path", "objects")
                            .get("{path}");
        System.out.println("get List Of Objects By IDS" + response.asPrettyString());
    }

    public static void getsingleObject() {
        /*
         * Define baseURI = https://api.restful-api.dev/objects/7
         */

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all()
                .pathParam("path", "objects")
                .pathParam("idObject", 7)
                .when()
                .get("{path}/{idObject}");
        System.out.println("get Single Object" + response.asPrettyString());
    }

    public static void addObject() {
        /*
         * Define baseURI = https://api.restful-api.dev/objects
         */

        String json = "{\n" + //
                "  \"name\": \"Apple MacBook Pro 16\",\n" + //
                "  \"data\": {\n" + //
                "    \"year\": 2019,\n" + //
                "    \"price\": 1849.99,\n" + //
                "    \"CPU model\": \"Intel Core i9\",\n" + //
                "    \"Hard disk size\": \"1 TB\"\n" + //
                "  }\n" + //
                "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all()
                .body(json)
                .contentType("application/json")
                .when()
                .post("/objects");
        System.out.println("Add Object" + response.asPrettyString());
    }

    public static void updateObject() {
        /*
         * Define baseURI =
         * https://api.restful-api.dev/objects/ff808181932badb601950fdcfa9239f9
         */

        String json = "{\r\n" + //
                "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                "   \"data\": {\r\n" + //
                "      \"year\": 2019,\r\n" + //
                "      \"price\": 3000.99,\r\n" + //
                "      \"color\": \"GOLD\"\r\n" + //
                "   }\r\n" + //
                "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all()
                .body(json)
                .contentType("application/json")
                .when()
                .put("/objects/{idObject}", "ff808181932badb601950fdcfa9239f9");
        System.out.println("update Object" + response.asPrettyString());
    }

    public static void partiallyUpdateObject() {
        /*
         * Define baseURI =
         * https://api.restful-api.dev/objects/ff808181932badb601950fdcfa9239f9
         */

        String json = "{\r\n" + //
                "   \"name\": \"Apple MacBook Pro 16 (Updated Name)\"\r\n" + //
                "}";

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all()
                .body(json)
                .contentType("application/json")
                .when()
                .patch("/objects/{idObject}", "ff808181932badb601950fdcfa9239f9");
        System.out.println("partially update Object" + response.asPrettyString());
    }

    public static void deleteObject() {
        /*
         * Define baseURI =
         * https://api.restful-api.dev/objects/ff808181932badb601950fdcfa9239f9
         */

        RestAssured.baseURI = "https://api.restful-api.dev";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.log().all()
                .contentType("application/json")
                .when()
                .delete("/objects/{idObject}", "ff808181932badb601950fdcfa9239f9");
        System.out.println("delete Object" + response.asPrettyString());
    }
}