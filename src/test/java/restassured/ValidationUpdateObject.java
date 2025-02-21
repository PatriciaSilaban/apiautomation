package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.updateObjectResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationUpdateObject {
        updateObjectResponse updateResponse;

        @Test
        public void updateObject() {

                String json = "{\r\n" + //
                                "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                                "   \"data\": {\r\n" + //
                                "      \"year\": 2019,\r\n" + //
                                "      \"price\": 20000,\r\n" + //
                                "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                                "      \"Hard disk size\": \"1 TB\",\r\n" + //
                                "      \"color\": \"silver\"\r\n" + //
                                "   }\r\n" + //
                                "}";

                RestAssured.baseURI = "https://api.restful-api.dev";
                RequestSpecification requestSpecification = RestAssured.given();
                Response response = requestSpecification.log().all()
                                .body(json)
                                .contentType("application/json")
                                .when()
                                .put("/objects/{idObject}", "ff808181932badb6019528a50c987a6b");
                System.out.println("update Object" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                updateResponse = jsonPath.getObject("", updateObjectResponse.class);

                Assert.assertNotNull(updateResponse.id);
                Assert.assertEquals(updateResponse.name, "Apple MacBook Pro 16");
                Assert.assertNotNull(updateResponse.updatedAt);
                Assert.assertEquals(updateResponse.dataItem.year, 2019);
                Assert.assertEquals(updateResponse.dataItem.price, 20000);
                Assert.assertEquals(updateResponse.dataItem.cpuModel, "Intel Core i9");
                Assert.assertEquals(updateResponse.dataItem.hardDiskSize, "1 TB");
                Assert.assertEquals(updateResponse.dataItem.color, "silver");

        }
}
