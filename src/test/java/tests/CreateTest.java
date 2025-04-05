package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.CreateEntity;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CreateTest {
    int UserId;
    private RequestSpecification requestSpecification;
    @BeforeClass
    public void setup() throws IOException{
        requestSpecification = BaseRequests.initRequestSpecification();
    }
    @Test(description = "Testing post request")
    @Severity(SeverityLevel.CRITICAL)
    public void CreateEntity_test(){
        CreateEntity createEntity = CreateEntity.builder().build();

        UserId = Integer
                .parseInt(given()
                        .spec(requestSpecification)
                        .body(createEntity)
                        .when()
                        .post("/api/create")
                        .then().statusCode(200)
                        .extract()
                        .response()
                        .asString());
    }
    @AfterMethod
    public void clear_garbage(){
        BaseRequests.DeleteEntity(UserId, requestSpecification);
    }
}
