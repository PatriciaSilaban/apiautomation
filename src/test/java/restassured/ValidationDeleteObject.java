package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.apiautomation.model.deleteObjectResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationDeleteObject {

        deleteObjectResponse deleteObject;

        @Test
        public void deleteObject() {
                RestAssured.baseURI = "https://api.restful-api.dev";
                RequestSpecification requestSpecification = RestAssured
                                .given();

                Response response = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idObject", "ff808181932badb6019528a734e37a72")
                                .contentType("application/json")
                                .when()
                                .delete("{path}/{idObject}");

                System.out.println("delete Object" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                deleteObject = jsonPath.getObject("", deleteObjectResponse.class);
                Assert.assertEquals(deleteObject.message,
                                "Object with id = ff808181932badb6019528a734e37a72 has been deleted.");
        }
}
