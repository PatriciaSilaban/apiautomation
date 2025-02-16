// package restassured;

// import io.restassured.RestAssured;
// import io.restassured.path.json.JsonPath;
// import io.restassured.response.Response;
// import io.restassured.specification.RequestSpecification;

// public class RestAssuredImpl {
//     public static void main(String[] args) {

//         // getAllProducts();
//         getSingleProduct();
//         // searchProduct();
//         // addProduct();
//         // updateProduct();
//         // deleteProduct();
//         // auth();

//     }

//     public static String auth() {

//         String token;
//         String json = "{\n" + //
//                 "  \"username\": \"emilys\",\n" + //
//                 "  \"password\": \"emilyspass\",\n" + //
//                 "  \"expiresInMins\": 30\n" + //
//                 "}";

//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured.given();

//         Response response = requestSpecification
//                 .log()
//                 .all()
//                 .body(json)
//                 .contentType("application/json")
//                 .pathParam("path", "auth")
//                 .pathParam("section", "login")
//                 .when()
//                 .post("{path}/{section}");

//         System.out.println("login" + response.asPrettyString());

//         JsonPath jsonPath = response.jsonPath();

//         System.out.println("token" + jsonPath.get("accessToken"));

//         token = jsonPath.get("accessToken");

//         return token;

//     }

//     public static void getAllProducts() {
//         // Tahapan awal, define baseURI nya, yaitu https://dummyjson.com/products
//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured
//                 .given();

//         Response response = requestSpecification.log().all().get("products");

//         Response response2 = RestAssured
//                 .given()
//                 .log()
//                 .all()
//                 .when()
//                 .get("products");

//         System.out.println("Hasilnya adalah" + response.asPrettyString());
//     }

//     // public static void getSingleProduct() {

//     // RestAssured.baseURI = "https://dummyjson.com";
//     // RequestSpecification requestSpecification = RestAssured.given();

//     // Response response = requestSpecification
//     // .log()
//     // .all()
//     // // .pathParam("idProduct", 1) --> comment
//     // .when()
//     // // .get("products/{idProduct}"); --> comment
//     // .get("products/1");

//     // // bisa menggunakan .get("products/1"); aja atau bisa menggunakan 2 syntax
//     // yang
//     // // diberi komen
//     // // yaitu line 40 dan line 42

//     // System.out.println("single Product" + response.asPrettyString());

//     // }

//     // ini contoh untuk auth get single product (mendapatkan token untuk get single
//     // product)

//     public static void getSingleProduct() {

//         String token;

//         token = auth();

//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured.given();

//         Response response = requestSpecification
//                 .log()
//                 .all()
//                 .pathParam("idProduct", 1)
//                 .pathParam("path", "products")
//                 .header("Authorization", "Bearer" + token)
//                 .when()
//                 .get("{path}/{idProduct}");
//         System.out.println("single Product" + response.asPrettyString());

//     }

//     public static void searchProduct() {
//         /*
//          * 'https//dummyjson.com/products/search?q=phone'
//          */
//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured
//                 .given();

//         Response response = requestSpecification
//                 .log()
//                 .all()
//                 .pathParam("path", "products")
//                 .pathParam("method", "search")
//                 .queryParam("q", "iPhone 5s")
//                 .when()
//                 .get("{path}/{method}");

//         System.out.println("Ini adalah hasil search" + response.asPrettyString());

//     }

//     public static void addProduct() {

//         String json = "{\n" +
//                 "   \"title\": \"Apple MacBook Pro 16\",\n" +
//                 "   \"price\": 1849.99,\n" +
//                 "   \"brand\": \"Apple\"\n" +
//                 "}";

//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured
//                 .given();

//         Response response = requestSpecification
//                 .log()
//                 .all()
//                 .pathParam("path", "products")
//                 .pathParam("method", "add")
//                 .body(json)
//                 .contentType("application/json")
//                 .when()
//                 .post("{path}/{method}");

//         System.out.println("add product" + response.asPrettyString());
//     }

//     public static void updateProduct() {
//         String json = "{\n" +
//                 "   \"title\": \"Patricia Silaban\",\n" +
//                 "   \"price\": 1849.99,\n" +
//                 "   \"brand\": \"Apple\"\n" +
//                 "}";

//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured
//                 .given();

//         Response response = requestSpecification
//                 .log()
//                 .all()
//                 .pathParam("path", "products")
//                 .pathParam("idProduct", "1")
//                 .body(json)
//                 .contentType("application/json")
//                 .when()
//                 .put("{path}/{idProduct}");

//         System.out.println("update product" + response.asPrettyString());

//     }

//     public static void deleteProduct() {
//         RestAssured.baseURI = "https://dummyjson.com";
//         RequestSpecification requestSpecification = RestAssured
//                 .given();

//         Response response = requestSpecification
//                 .log()
//                 .all()
//                 .pathParam("path", "products")
//                 .pathParam("idProduct", "1")
//                 .contentType("application/json")
//                 .when()
//                 .delete("{path}/{idProduct}");

//         System.out.println("delete product" + response.asPrettyString());
//     }
// }