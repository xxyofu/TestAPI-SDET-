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
import pojo.GetEntity;


import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetTest {
    private RequestSpecification requestSpecification;
    private int UserId;
    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }
    @Test(description = "Testing get request")
    @Severity(SeverityLevel.CRITICAL)
    public void GetEntity_test(){
        CreateEntity createEntity = CreateEntity.builder().title("Test Get").build();
        UserId = BaseRequests.CreateEntity(createEntity, requestSpecification);
        GetEntity getEntity = given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/"+UserId)
                .then()
                .statusCode(200)
                .extract()
                .as(GetEntity.class, ObjectMapperType.GSON);
        SoftAssert softAssertation = new SoftAssert();
        softAssertation.assertEquals(getEntity.getTitle(), "Test Get");
    }
    @AfterMethod
    public void clear_garbage(){
        BaseRequests.DeleteEntity(UserId, requestSpecification);
    }
}
