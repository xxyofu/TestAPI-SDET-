package tests;

import helpers.BaseRequests;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    int UserId;
    RequestSpecification requestSpecification;
    @BeforeClass
    public void setup() throws IOException {
        requestSpecification = BaseRequests.initRequestSpecification();
    }
    @AfterMethod
    public void clear_garbage(){
        BaseRequests.DeleteEntity(UserId, requestSpecification);
    }
}
