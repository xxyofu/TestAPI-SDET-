package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.CreateEntity;
import pojo.ListEntities;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetAllTest {
    private RequestSpecification requestSpecification;
    private int UserId;
    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }
    @Test(description = "Testing get request for all entities")
    @Severity(SeverityLevel.CRITICAL)
    public void GetAllEntity_test(){
        CreateEntity createEntity = CreateEntity.builder().title("Test Get").build();
        UserId = BaseRequests.CreateEntity(createEntity, requestSpecification);
        ListEntities getEntities = given()
                .spec(requestSpecification)
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract()
                .as(ListEntities.class, ObjectMapperType.GSON);
        SoftAssert softAssertation = new SoftAssert();
        boolean flag = false;
        for (int i = 0; i<getEntities.getEntity().length; i++){
            if (getEntities.getEntity()[i].getId()==UserId) {
                softAssertation.assertEquals(getEntities.getEntity()[i].getId(), UserId);
                flag = true;
            }
        }
        if (flag==false)
            softAssertation.assertEquals(UserId, -1);
    }
    @AfterMethod
    public void clear_garbage(){
        BaseRequests.DeleteEntity(UserId, requestSpecification);
    }
}
