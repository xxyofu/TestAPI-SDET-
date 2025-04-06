package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojo.GetEntity;


import static io.restassured.RestAssured.given;

public class DeleteTest extends BaseTest{


    @Test(description = "Testing deleting request")
    @Severity(SeverityLevel.CRITICAL)
    public void DeleteEntity_test(){
        GetEntity createEntity = GetEntity.builder().build();
        int id = BaseRequests.CreateEntity(createEntity, requestSpecification);
        given()
                .spec(requestSpecification)
                .when()
                .delete("/api/delete/"+id)
                .then()
                .statusCode(204);
    }
    @Override
    public void clear_garbage(){}

}
