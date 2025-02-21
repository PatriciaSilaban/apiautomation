package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.updatePartiallyObjectResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationUpdatePartiallyObject {
        updatePartiallyObjectResponse updatePartiallyResponse;

        @Test
        public void partiallyUpdateObject() {
                String json = "{\r\n" + //
                                "   \"name\": \"Apple MacBook Pro 16 (Updated Name)\"\r\n" + //
                                "}";

                RestAssured.baseURI = "https://api.restful-api.dev";
                RequestSpecification requestSpecification = RestAssured.given();
                Response response = requestSpecification.log().all()
                                .body(json)
                                .contentType("application/json")
                                .when()
                                .patch("/objects/{idObject}", "ff808181932badb6019528a50c987a6b");
                System.out.println("partially update Object" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                updatePartiallyResponse = jsonPath.getObject("", updatePartiallyObjectResponse.class);

                Assert.assertNotNull(updatePartiallyResponse.id);
                Assert.assertEquals(updatePartiallyResponse.name, "Apple MacBook Pro 16 (Updated Name)");
                Assert.assertNotNull(updatePartiallyResponse.updatedAt);
                Assert.assertEquals(updatePartiallyResponse.dataItem.year, 2019);
                Assert.assertEquals(updatePartiallyResponse.dataItem.price, 20000);
                Assert.assertEquals(updatePartiallyResponse.dataItem.cpuModel, "Intel Core i9");
                Assert.assertEquals(updatePartiallyResponse.dataItem.hardDiskSize, "1 TB");
                Assert.assertEquals(updatePartiallyResponse.dataItem.color, "silver");
        }
}
