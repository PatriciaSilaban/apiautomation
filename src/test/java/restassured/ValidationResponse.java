package restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class ValidationResponse {
    /*
     * 1. Hit api get single product
     * 2. The validate the new product is created
     * Validate id, name, dsb
     */
    public static void main(String[] args) {
        getSingleProduct();
    }

    public static void getSingleProduct() {

        RestAssured.baseURI = "https://dummyjson.com";
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                .log()
                .all()
                .pathParam("idProduct", 1)
                .pathParam("path", "products")
                .when()
                .get("{path}/{idProduct}");

        ValidatableResponse validationResponse = response
                .then()
                .body("id", equalTo(1));

        System.out.println("validasi" + validationResponse.extract());

        JsonPath jsonPath = response.jsonPath();
        System.out.println("id" + jsonPath.get("id"));
        System.out.println("description" + jsonPath.get("description"));
        System.out.println("width" + jsonPath.get("dimensions.width"));

    }

    /*
     * JSONPATH
     * {
     * "id": 1,
     * "title": "Essence Mascara Lash Princess",
     * "description":
     * "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula."
     * ,
     * "category": "beauty",
     * "price": 9.99,
     * "discountPercentage": 7.17,
     * "rating": 4.94,
     * "stock": 5,
     * "tags": [
     * "beauty",
     * "mascara"
     * ],
     * "brand": "Essence",
     * "sku": "RCH45Q1A",
     * "weight": 2,
     * "dimensions": {
     * "width": 23.17,
     * "height": 14.43,
     * "depth": 28.01
     * },
     * "warrantyInformation": "1 month warranty",
     * "shippingInformation": "Ships in 1 month",
     * "availabilityStatus": "Low Stock",
     * "reviews": [
     * {
     * "rating": 2,
     * "comment": "Very unhappy with my purchase!",
     * "date": "2024-05-23T08:56:21.618Z",
     * "reviewerName": "John Doe",
     * "reviewerEmail": "john.doe@x.dummyjson.com"
     * },
     * {
     * "rating": 2,
     * "comment": "Not as described!",
     * "date": "2024-05-23T08:56:21.618Z",
     * "reviewerName": "Nolan Gonzalez",
     * "reviewerEmail": "nolan.gonzalez@x.dummyjson.com"
     * },
     * {
     * "rating": 5,
     * "comment": "Very satisfied!",
     * "date": "2024-05-23T08:56:21.618Z",
     * "reviewerName": "Scarlett Wright",
     * "reviewerEmail": "scarlett.wright@x.dummyjson.com"
     * }
     * ],
     * "returnPolicy": "30 days return policy",
     * "minimumOrderQuantity": 24,
     * "meta": {
     * "createdAt": "2024-05-23T08:56:21.618Z",
     * "updatedAt": "2024-05-23T08:56:21.618Z",
     * "barcode": "9164035109868",
     * "qrCode": "https://assets.dummyjson.com/public/qr-code.png"
     * },
     * "images": [
     * "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/1.png"
     * ],
     * "thumbnail":
     * "https://cdn.dummyjson.com/products/images/beauty/Essence%20Mascara%20Lash%20Princess/thumbnail.png"
     * }
     * 
     */

}