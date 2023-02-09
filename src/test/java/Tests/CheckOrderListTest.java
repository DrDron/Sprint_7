package Tests;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.notNullValue;

public class CheckOrderListTest {
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";}
    @Test
    public void getOrderList(){
        given()
                .header("Content-type", "application/json")
                .get("/api/v1/orders")
                .then()
                .assertThat()
                .body(anything("orders"))
                .statusCode(200);
    }

}
