package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.getListSingleObjectResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationSingleObject {

        getListSingleObjectResponse listSingleObjectResponse;

        // Get list of all objects
        @Test
        public void getListSingleObject() {
                String json = "{\r\n" + //
                                "    \"id\": \"7\",\r\n" + //
                                "    \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                                "    \"data\": {\r\n" + //
                                "        \"year\": 2019,\r\n" + //
                                "        \"price\": 1849.99,\r\n" + //
                                "        \"CPU model\": \"Intel Core i9\",\r\n" + //
                                "        \"Hard disk size\": \"1 TB\"\r\n" + //
                                "    }\r\n" + //
                                "}";

                RestAssured.baseURI = "https://api.restful-api.dev";
                RequestSpecification requestSpecification = RestAssured.given();
                Response response = requestSpecification.log().all()
                                .pathParam("path", "objects")
                                .pathParam("idObject", 7)
                                .when()
                                .get("{path}/{idObject}");
                System.out.println("get Single Object" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                listSingleObjectResponse = jsonPath.getObject("", getListSingleObjectResponse.class);

                Assert.assertEquals(listSingleObjectResponse.id, "7");
                Assert.assertEquals(listSingleObjectResponse.name, "Apple MacBook Pro 16");
                Assert.assertEquals(listSingleObjectResponse.dataItem.year, 2019);
                Assert.assertEquals(listSingleObjectResponse.dataItem.price, 1849.99);
                Assert.assertEquals(listSingleObjectResponse.dataItem.cpuModel, "Intel Core i9");
                Assert.assertEquals(listSingleObjectResponse.dataItem.hardDiskSize, "1 TB");
        }
}