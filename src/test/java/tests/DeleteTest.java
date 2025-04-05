package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pojo.CreateEntity;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeleteTest {
    private RequestSpecification requestSpecification;
    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }

    @Test(description = "Testing deleting request")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteEntity_test(){
        CreateEntity createEntity = CreateEntity.builder().build();
        int id = BaseRequests.CreateEntity(createEntity, requestSpecification);
        given()
                .spec(requestSpecification)
                .when()
                .delete("/api/delete/"+id)
                .then()
                .statusCode(204);
    }
}
