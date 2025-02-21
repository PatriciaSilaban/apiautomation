package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.addObjectResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationAddObject {
        addObjectResponse objectResponse;

        @Test
        public void createObject() {
                String json = "{\n" + //
                                "   \"name\": \"Apple MacBook Pro 16\",\n" + //
                                "   \"data\": {\n" + //
                                "      \"year\": 2019,\n" + //
                                "      \"price\": 20000,\n" + //
                                "      \"CPU model\": \"Intel Core i9\",\n" + //
                                "      \"Hard disk size\": \"1 TB\"\n" + //
                                "   }\n" + //
                                "}";

                RestAssured.baseURI = "https://api.restful-api.dev";
                RequestSpecification requestSpecification = RestAssured
                                .given();

                Response response = requestSpecification
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .body(json)
                                .contentType("application/json")
                                .when()
                                .post("{path}");
                System.out.println("Add Object" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                objectResponse = jsonPath.getObject("", addObjectResponse.class);

                Assert.assertEquals(objectResponse.name, "Apple MacBook Pro 16");
                Assert.assertNotNull(objectResponse.createdAt);
                Assert.assertNotNull(objectResponse.id);
                Assert.assertEquals(objectResponse.dataItem.year, 2019);
                Assert.assertEquals(objectResponse.dataItem.price, 20000);
                Assert.assertEquals(objectResponse.dataItem.cpuModel, "Intel Core i9");
                Assert.assertEquals(objectResponse.dataItem.hardDiskSize, "1 TB");
        }
}
