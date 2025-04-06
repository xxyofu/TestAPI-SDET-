package tests;

import helpers.BaseRequests;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.mapper.ObjectMapperType;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pojo.GetEntity;
import pojo.ListEntities;


import static io.restassured.RestAssured.given;

public class GetAllTest extends BaseTest{


    @Test(description = "Testing get request for all entities")
    @Severity(SeverityLevel.CRITICAL)
    public void GetAllEntity_test(){
        GetEntity createEntity = GetEntity.builder().title("Test Get").build();
        UserId = BaseRequests.CreateEntity(createEntity, requestSpecification);
        ListEntities getEntities = given()
                .spec(requestSpecification)
                .when()
                .get("/api/getAll")
                .then()
                .statusCode(200)
                .extract()
                .as(ListEntities.class, ObjectMapperType.GSON);
        SoftAssert softAssertation = new SoftAssert();
        boolean flag = false;
        for (int i = 0; i<getEntities.getEntity().length; i++){
            if (getEntities.getEntity()[i].getId()==UserId) {
                softAssertation.assertEquals(getEntities.getEntity()[i].getId(), UserId);
                flag = true;
            }
        }
        if (flag==false)
            softAssertation.assertEquals(UserId, -1);
    }

}
