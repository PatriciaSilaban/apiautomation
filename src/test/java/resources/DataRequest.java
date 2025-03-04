package resources;

import java.util.HashMap;
import java.util.Map;

public class DataRequest {
    // Berisi data test
    /*
     * Mapping berisi 2 komponen
     * 1. Key
     * 2. Value
     */

    public static Object json;

    public Map<String, String> addItemCollection() {
        Map<String, String> dataCollection = new HashMap<>();

        dataCollection.put("addItem", "{\r\n" + //
                "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                "   \"data\": {\r\n" + //
                "      \"year\": 2019,\r\n" + //
                "      \"price\": 20000,\r\n" + //
                "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                "      \"Hard disk size\": \"1 TB\"\r\n" + //
                "   }\r\n" + //
                "}");

        dataCollection.put("addItem2", "{\n" + //
                " \"name\": \"Apple MacBook Pro 15\",\n" + //
                " \"data\": {\n" + //
                " \"year\": 2019,\n" + //
                " \"price\": 20000,\n" + //
                " \"CPU model\": \"Intel Core i9\",\n" + //
                " \"Hard disk size\": \"1 TB\"\n" + //
                " }\n" + //
                "}");
        return dataCollection;
    }
}
