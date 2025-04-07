package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.GetEntity;


import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllTest extends BaseTest{


    @Test(description = "Testing get request for all entities")
    @Severity(SeverityLevel.CRITICAL)
    public void GetAllEntity_test(){
        GetEntity createEntity = GetEntity.builder().title("Test Get").build();
        UserId = BaseRequests.CreateEntity(createEntity, requestSpecification);
        List<GetEntity> getEntities = given()
                .spec(requestSpecification)
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("entity", GetEntity.class);
        SoftAssert softAssertation = new SoftAssert();
        boolean flag = false;
        for (int i = 0; i<getEntities.size(); i++){
            if (getEntities.get(i).getId()==UserId) {
                softAssertation.assertEquals(getEntities.get(i).getId(), UserId);
                flag = true;
            }
        }
        if (!flag)
            softAssertation.assertEquals(UserId, -1);
    }

}
