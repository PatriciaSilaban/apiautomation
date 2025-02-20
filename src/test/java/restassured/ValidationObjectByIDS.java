package restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.apiautomation.model.getListOfObjectByIDSResponse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationObjectByIDS {
        getListOfObjectByIDSResponse listObjectByIDSResponse;

        // Get list of all objects
        @Test
        public void getListOfObjectsByIDS() {
                String json = "[\r\n" + //
                                "   {\r\n" + //
                                "      \"id\": \"3\",\r\n" + //
                                "      \"name\": \"Apple iPhone 12 Pro Max\",\r\n" + //
                                "      \"data\": {\r\n" + //
                                "         \"color\": \"Cloudy White\",\r\n" + //
                                "         \"capacity GB\": 512\r\n" + //
                                "      }\r\n" + //
                                "   },\r\n" + //
                                "   {\r\n" + //
                                "      \"id\": \"5\",\r\n" + //
                                "      \"name\": \"Samsung Galaxy Z Fold2\",\r\n" + //
                                "      \"data\": {\r\n" + //
                                "         \"price\": 689.99,\r\n" + //
                                "         \"color\": \"Brown\"\r\n" + //
                                "      }\r\n" + //
                                "   },\r\n" + //
                                "   {\r\n" + //
                                "      \"id\": \"10\",\r\n" + //
                                "      \"name\": \"Apple iPad Mini 5th Gen\",\r\n" + //
                                "      \"data\": {\r\n" + //
                                "         \"Capacity\": \"64 GB\",\r\n" + //
                                "         \"Screen size\": 7.9\r\n" + //
                                "      }\r\n" + //
                                "   }\r\n" + //
                                "]\r\n" + //
                                "\r\n" + //
                                "";

                RestAssured.baseURI = "https://api.restful-api.dev";
                RequestSpecification requestSpecification = RestAssured.given();
                Response response = requestSpecification.log().all().get("objects?id=3&id=5&id=10");
                System.out.println("get List Of Objects By IDS" + response.asPrettyString());

                JsonPath jsonPath = response.jsonPath();
                List<getListOfObjectByIDSResponse> responseObject = jsonPath.getList("",
                                getListOfObjectByIDSResponse.class);

                // Validate the first object with ID 3 (Apple iPhone 12 Pro Max)
                listObjectByIDSResponse = responseObject.get(0);
                Assert.assertEquals(listObjectByIDSResponse.id, "3");
                Assert.assertEquals(listObjectByIDSResponse.name, "Apple iPhone 12 Pro Max");
                Assert.assertEquals(listObjectByIDSResponse.dataItem.color, "Cloudy White");
                Assert.assertEquals(listObjectByIDSResponse.dataItem.capacityGB, 512);

                // Validate the second object with ID 5 (Samsung Galaxy Z Fold2)
                listObjectByIDSResponse = responseObject.get(1);
                Assert.assertEquals(listObjectByIDSResponse.id, "5");
                Assert.assertEquals(listObjectByIDSResponse.name, "Samsung Galaxy Z Fold2");
                Assert.assertEquals(listObjectByIDSResponse.dataItem.price, 689.99, 0.01);
                Assert.assertEquals(listObjectByIDSResponse.dataItem.color, "Brown");

                // Validate the third object with ID 10 (Apple iPad Mini 5th Gen)
                listObjectByIDSResponse = responseObject.get(2);
                Assert.assertEquals(listObjectByIDSResponse.id, "10");
                Assert.assertEquals(listObjectByIDSResponse.name, "Apple iPad Mini 5th Gen");
                Assert.assertEquals(listObjectByIDSResponse.dataItem.Capacity, "64 GB");
                Assert.assertEquals(listObjectByIDSResponse.dataItem.screenSize, 7.9);

        }
}