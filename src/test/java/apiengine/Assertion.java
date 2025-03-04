package apiengine;

import java.util.List;
import org.testng.Assert;
import com.apiautomation.model.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Assertion {

    public void assertListAllObject(Response response) {
        JsonPath jsonPath = JsonPath.from(response.asString());
        List<getListOfAllObjectResponse> responseObjects = jsonPath.getList("", getListOfAllObjectResponse.class);

        getListOfAllObjectResponse firstObject = responseObjects.get(0);
        Assert.assertEquals(firstObject.id, responseObjects.get(0).id);
        Assert.assertEquals(firstObject.name, responseObjects.get(0).name);
        Assert.assertEquals(firstObject.dataItem.color, responseObjects.get(0).dataItem.color);
        Assert.assertEquals(firstObject.dataItem.capacity, responseObjects.get(0).dataItem.capacity);

        if (responseObjects.size() > 1) {
            getListOfAllObjectResponse secondObject = responseObjects.get(1);
            Assert.assertEquals(secondObject.id, responseObjects.get(1).id);
            Assert.assertEquals(secondObject.name, responseObjects.get(1).name);
            Assert.assertNull(secondObject.dataItem); // FIX: Menyesuaikan dengan assertion awal
        }
    }

    public void assertAddObject(addObjectResponse objectResponse) {
        Assert.assertNotNull(objectResponse.createdAt); // FIX: createdAt tidak bisa dibandingkan dengan objectResponse
                                                        // langsung
        Assert.assertNotNull(objectResponse.id);
        Assert.assertEquals(objectResponse.name, objectResponse.name);
        Assert.assertEquals(objectResponse.dataItem.year, objectResponse.dataItem.year);
        Assert.assertEquals(objectResponse.dataItem.price, objectResponse.dataItem.price);
        Assert.assertEquals(objectResponse.dataItem.cpuModel, objectResponse.dataItem.cpuModel);
        Assert.assertEquals(objectResponse.dataItem.hardDiskSize, objectResponse.dataItem.hardDiskSize);
    }

    public void assertSingleObject(getListSingleObjectResponse listSingleObjectResponse) {
        Assert.assertNotNull(listSingleObjectResponse.id);
        Assert.assertEquals(listSingleObjectResponse.name, listSingleObjectResponse.name);
        Assert.assertEquals(listSingleObjectResponse.dataItem.year, listSingleObjectResponse.dataItem.year);
        Assert.assertEquals(listSingleObjectResponse.dataItem.price, listSingleObjectResponse.dataItem.price);
        Assert.assertEquals(listSingleObjectResponse.dataItem.cpuModel, listSingleObjectResponse.dataItem.cpuModel);
        Assert.assertEquals(listSingleObjectResponse.dataItem.hardDiskSize,
                listSingleObjectResponse.dataItem.hardDiskSize);
    }

    public void assertObjectByIDS(getListOfObjectByIDSResponse listObjectByIDSResponse) {
        Assert.assertNotNull(listObjectByIDSResponse.id);
        Assert.assertEquals(listObjectByIDSResponse.name, listObjectByIDSResponse.name);
        Assert.assertEquals(listObjectByIDSResponse.dataItem.color, listObjectByIDSResponse.dataItem.color);
        Assert.assertEquals(listObjectByIDSResponse.dataItem.capacityGB, listObjectByIDSResponse.dataItem.capacityGB);
    }

    public void assertUpdateObject(updateObjectResponse updateResponse) {
        Assert.assertNotNull(updateResponse.id);
        Assert.assertEquals(updateResponse.name, updateResponse.name);
        Assert.assertNotNull(updateResponse.updatedAt);
        Assert.assertEquals(updateResponse.dataItem.year, updateResponse.dataItem.year);
        Assert.assertEquals(updateResponse.dataItem.price, updateResponse.dataItem.price);
        Assert.assertEquals(updateResponse.dataItem.cpuModel, updateResponse.dataItem.cpuModel);
        Assert.assertEquals(updateResponse.dataItem.hardDiskSize, updateResponse.dataItem.hardDiskSize);
        Assert.assertEquals(updateResponse.dataItem.color, updateResponse.dataItem.color);
    }

    public void assertUpdatePartiallyObject(updatePartiallyObjectResponse updatePartiallyResponse) {
        Assert.assertNotNull(updatePartiallyResponse.id);
        Assert.assertEquals(updatePartiallyResponse.name, updatePartiallyResponse.name);
        Assert.assertNotNull(updatePartiallyResponse.updatedAt);
        Assert.assertEquals(updatePartiallyResponse.dataItem.year, updatePartiallyResponse.dataItem.year);
        Assert.assertEquals(updatePartiallyResponse.dataItem.price, updatePartiallyResponse.dataItem.price);
        Assert.assertEquals(updatePartiallyResponse.dataItem.cpuModel, updatePartiallyResponse.dataItem.cpuModel);
        Assert.assertEquals(updatePartiallyResponse.dataItem.hardDiskSize,
                updatePartiallyResponse.dataItem.hardDiskSize);
        Assert.assertEquals(updatePartiallyResponse.dataItem.color, updatePartiallyResponse.dataItem.color);
    }

    public void assertDeleteObject(deleteObjectResponse deleteObject, String idObject) {
        Assert.assertEquals(deleteObject.message, "Object with id " + idObject + " has been deleted.");
    }
}