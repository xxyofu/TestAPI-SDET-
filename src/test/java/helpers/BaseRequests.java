package helpers;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import pojo.CreateEntity;
import pojo.GetEntity;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BaseRequests {
    @Step("Making Request Specification")
    public static RequestSpecification initRequestSpecification() throws IOException{
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setContentType(ContentType.JSON)
                .setBaseUri(PropertyProvider.getIntance().getProperty("api_url"))
                .setAccept(ContentType.JSON);
        return requestSpecBuilder.build();
    }
    @Step("Create Entity by using POJO")
    public static int CreateEntity(CreateEntity createEntity, RequestSpecification requestSpecification){
        return Integer
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

    @Step("Deleting Entity by ID: {id}")
    public static void DeleteEntity(int id, RequestSpecification requestSpecification){
        given()
                .spec(requestSpecification)
                .delete("/api/delete/"+id)
                .then()
                .statusCode(204);
    }
    @Step("Get entity by ID: {id}")
    public static GetEntity GetEntity(int id, RequestSpecification requestSpecification){
        return given()
                .spec(requestSpecification)
                .when()
                .get("/api/get/"+id)
                .then()
                .statusCode(200)
                .extract()
                .as(GetEntity.class, ObjectMapperType.GSON);
    }
}
