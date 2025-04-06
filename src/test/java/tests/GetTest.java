package tests;

import helpers.BaseRequests;
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
        GetEntity createEntity = GetEntity.builder().title("Test Get").build();
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
        softAssertation.assertEquals(getEntity.getId(), UserId);
    }

}
