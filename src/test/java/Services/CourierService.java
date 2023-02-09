package Services;

import Models.courier.CourierData;
import Models.courier.CourierLoginData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierService {
    @Step("Get response from POST request to /api/v1/courier/login")
    public Integer getCourierId(CourierData courierData){
        return given()
                .header("Content-type", "application/json")
                .body(courierData)
                .post("/api/v1/courier/login")
                .then()
                .extract().path("id");
    }
    @Step("Send POST request to /api/v1/courier/login")
    public Response courierLogIn(CourierData courierData){
        return  given()
                .header("Content-type", "application/json")
                .body(courierData)
                .post("/api/v1/courier/login");
    }
    public Response courierLogIn(CourierLoginData courierLoginData){
        return  given()
                .header("Content-type", "application/json")
                .body(courierLoginData)
                .post("/api/v1/courier/login");
    }
    @Step("Send Delete request to /api/v1/courier")
    public void deleteCourier(CourierData courierData){
        given()
                .header("Content-type", "application/json")
                .and()
                .body(getCourierId(courierData))
                .when()
                .delete("/api/v1/courier");
    }
    @Step("Send POST request to /api/v1/courier")
    public Response createCourier(CourierData courierData){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierData)
                .when()
                .post("/api/v1/courier");
    }
}
