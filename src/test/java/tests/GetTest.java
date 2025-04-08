package tests;

import helpers.BaseRequests;
import helpers.RandomGenerators;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.GetEntity;



import static io.restassured.RestAssured.given;

public class GetTest extends BaseTest{


    @Test(description = "Testing get request")
    @Severity(SeverityLevel.CRITICAL)
    public void GetEntity_test(){
        GetEntity createEntity = RandomGenerators.generateRandomEntity();
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
        softAssertation.assertEquals(getEntity.getTitle(), createEntity.getTitle());
        softAssertation.assertEquals(getEntity.getId(), UserId);
        softAssertation.assertAll();
    }

}
