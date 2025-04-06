package tests;

import helpers.JsonGetters;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import pojo.GetEntity;

import static io.restassured.RestAssured.given;

public class CreateTest extends BaseTest{


    @Test(description = "Testing post request")
    @Severity(SeverityLevel.CRITICAL)
    public void CreateEntity_test(){
        GetEntity createEntity = GetEntity.builder().build();
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
    }

}
