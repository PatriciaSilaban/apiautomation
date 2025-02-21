package scenario;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation.model.addObjectResponse;
import com.apiautomation.model.deleteObjectResponse;
import com.apiautomation.model.getListSingleObjectResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestE2ETest {
        /*
         * 1. Create new object (hit API add_object)
         * 2. Verify new object is added (hit API single_object)
         * 3. Delete product (hit API delete_object)
         * 4. Verify new object is deleted (hit API single_object)
         */

        @Test
        public void scenarioE2ETest() {
                String json = "{\n" + //
                                "   \"name\": \"Apple MacBook Pro 16\",\n" + //
                                "   \"data\": {\n" + //
                                "      \"year\": 2019,\n" + //
                                "      \"price\": 20000,\n" + //
                                "      \"CPU model\": \"Intel Core i9\",\n" + //
                                "      \"Hard disk size\": \"1 TB\"\n" + //
                                "   }\n" + //
                                "}";
                // Base URI
                RestAssured.baseURI = "https://api.restful-api.dev";

                // Step 1 : Create object (addObject)
                Response response = given()
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .body(json)
                                .contentType("application/json")
                                .when()
                                .post("{path}");

                System.out.println("add object" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                addObjectResponse objectResponse = jsonPath.getObject("", addObjectResponse.class);

                Assert.assertEquals(objectResponse.name, "Apple MacBook Pro 16");
                Assert.assertNotNull(objectResponse.createdAt);
                Assert.assertNotNull(objectResponse.id);
                Assert.assertEquals(objectResponse.dataItem.year, 2019);
                Assert.assertEquals(objectResponse.dataItem.price, 20000);
                Assert.assertEquals(objectResponse.dataItem.cpuModel, "Intel Core i9");
                Assert.assertEquals(objectResponse.dataItem.hardDiskSize, "1 TB");

                String idObject = objectResponse.id;

                // Step 2 : Verify Object Exists (singleObject)
                Response response2 = given()
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idObject", idObject)
                                .when()
                                .get("{path}/{idObject}");

                System.out.println("verify object exists" + response2.asPrettyString());

                JsonPath jsonPath2 = response2.jsonPath();
                getListSingleObjectResponse listSingleObjectResponse2 = jsonPath2.getObject("",
                                getListSingleObjectResponse.class);

                Assert.assertEquals(listSingleObjectResponse2.id, idObject);
                Assert.assertEquals(listSingleObjectResponse2.name, "Apple MacBook Pro 16");
                Assert.assertEquals(listSingleObjectResponse2.dataItem.year, 2019);
                Assert.assertEquals(listSingleObjectResponse2.dataItem.price, 20000);
                Assert.assertEquals(listSingleObjectResponse2.dataItem.cpuModel, "Intel Core i9");
                Assert.assertEquals(listSingleObjectResponse2.dataItem.hardDiskSize, "1 TB");

                // Step 3 : Delete Object
                Response response3 = given()
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idObject", idObject)
                                .when()
                                .delete("{path}/{idObject}");

                System.out.println("delete object" + response3.asPrettyString());

                JsonPath jsonPath3 = response3.jsonPath();
                deleteObjectResponse deleteObject3 = jsonPath3.getObject("",
                                deleteObjectResponse.class);
                Assert.assertEquals(deleteObject3.message,
                                "Object with id = " + idObject + " has been deleted.");

                // Step 4 : Verify Object is deleted
                Response response4 = given()
                                .log()
                                .all()
                                .pathParam("path", "objects")
                                .pathParam("idObject", idObject)
                                .when()
                                .get("{path}/{idObject}");

                System.out.println("verify object is deleted" + response4.asPrettyString());

                Assert.assertEquals(response4.statusCode(), 404);

        }

}