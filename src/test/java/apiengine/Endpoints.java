package apiengine;

import com.apiautomation.constants.Constants;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Endpoints {
    RequestSpecification requestSpecification;

    public Endpoints() {
        RestAssured.baseURI = Constants.BASE_URL;
        requestSpecification = RestAssured
                .given();
    }

    public Response getListOfAllObjects(String path) {
        Response response = requestSpecification
                .get("objects");
        return response;
    }

    public Response addObject(String path, String json) {
        Response response = requestSpecification
                .pathParam("path", "objects")
                .body(json)
                .contentType("application/json")
                .when()
                .post("{path}");
        return response;
    }

    public Response addNewObject(String path, String json) {
        Response response = requestSpecification
                .pathParam("path", "objects")
                .body(json)
                .contentType("application/json")
                .when()
                .post("{path}");
        return response;
    }

    public Response getSingleObject(String path, String idObject) {
        Response response = requestSpecification
                .pathParam("path", "objects")
                .pathParam("idObject", idObject)
                .when()
                .get("{path}/{idObject}");
        return response;
    }

    public Response getListOfObjectByIDS(String path, String idObject) {
        Response response = requestSpecification
                .pathParam("path", "objects")
                .queryParam("id", idObject)
                .get("path");
        return response;
    }

    public Response updateObject(String path, String idObject, String json) {
        Response response = requestSpecification
                .body(json)
                .contentType("application/json")
                .when()
                .put("/objects/{idObject}", idObject);
        return response;
    }

    public Response partiallyUpdateObject(String json, String idObject) {
        Response response = requestSpecification.log().all()
                .body(json)
                .contentType("application/json")
                .when()
                .patch("/objects/{idObject}", idObject);
        return response;
    }

    public Response deleteObject(String path, String idObject) {
        Response response = requestSpecification
                .pathParam("path", path)
                .pathParam("idObject", idObject)
                .contentType("application/json")
                .when()
                .delete("{path}/{idObject}");
        return response;
    }
}
