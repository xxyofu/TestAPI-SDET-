package tests;

import helpers.BaseRequests;
import helpers.JsonGetters;
import helpers.RandomGenerators;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.GetEntity;

import static io.restassured.RestAssured.given;

public class CreateTest extends BaseTest{

    @Test(description = "Testing post request")
    @Severity(SeverityLevel.CRITICAL)
    public void CreateEntity_test(){
        GetEntity createEntity = RandomGenerators.generateRandomEntity();
        String request = JsonGetters.getSerilizedJsonString(createEntity);

        UserId = Integer
                .parseInt(given()
                        .spec(requestSpecification)
                        .body(request)
                        .when()
                        .post("/api/create")
                        .then().statusCode(200)
                        .extract()
                        .response()
                        .asString());
        GetEntity getEntity = BaseRequests.GetEntity(UserId, requestSpecification);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(getEntity.getTitle(), createEntity.getTitle());
        softAssert.assertAll();
    }

}
