package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.AdditionGet;
import pojo.CreateEntity;
import pojo.GetEntity;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UpdateTest {
    private RequestSpecification requestSpecification;
    private int UserId;
    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }
    @Test(description = "Testing patch request")
    @Severity(SeverityLevel.CRITICAL)
    public void UpdateEntity_test(){
        CreateEntity createEntity = CreateEntity.builder().title("Test Get").build();
        UserId = BaseRequests.CreateEntity(createEntity, requestSpecification);
        GetEntity getEntity = BaseRequests.GetEntity(UserId, requestSpecification);
        getEntity.setAddition(AdditionGet.builder().additional_number(256).build());
        given()
                .spec(requestSpecification)
                .body(getEntity)
                .when()
                .patch("/api/patch/"+UserId)
                .then()
                .statusCode(204);
        getEntity = BaseRequests.GetEntity(UserId, requestSpecification);
        SoftAssert softAssertation = new SoftAssert();
        softAssertation.assertEquals(getEntity.getAddition().getAdditional_number(), 256);
    }
    @AfterMethod
    public void clear_garbage(){
        BaseRequests.DeleteEntity(UserId, requestSpecification);
    }
}
