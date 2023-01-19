package Tests;

import Models.Order.OrderCreateData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class AddOrderTest {
    private List<String> color;
    public AddOrderTest(List<String> color){
        this.color = color;
    }
    public Response deleteOrder(int track){
       return given()
                .header("Content-type", "application/json")
                .and()
                .body(track)
                .when()
                .put("/api/v1/orders/cancel");
    }
    @Parameterized.Parameters
    public static Object[][] getDataForOrder() {
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("GREY","BLACK")},
                {List.of()}
        };
}
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";}
    @Test
    public void createOrder(){
        OrderCreateData orderCreateData = new OrderCreateData();
        orderCreateData.setColor(color);
        int track = given()
                .header("Content-type", "application/json")
                .body(orderCreateData)
                .post("/api/v1/orders")
                .then()
                .assertThat()
                .statusCode(201)
                .extract().path("track");
        assertNotNull(track);
        deleteOrder(track);
    }
}
