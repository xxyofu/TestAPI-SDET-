package tests;

import helpers.BaseRequests;
import helpers.RandomGenerators;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.AdditionGet;
import pojo.GetEntity;


import static io.restassured.RestAssured.given;

public class UpdateTest extends BaseTest{

    @Test(description = "Testing patch request")
    @Severity(SeverityLevel.CRITICAL)
    public void UpdateEntity_test(){
        GetEntity createEntity = RandomGenerators.generateRandomEntity();
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
        softAssertation.assertAll();
    }

}
