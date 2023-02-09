package Tests;

import Models.courier.CourierData;
import Models.courier.CourierLoginData;
import Services.CourierService;
import com.github.javafaker.Faker;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;

public class LogInCourierTest {
    CourierService courierService = new CourierService();

    public String genString() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";}
    @Test
    public  void successLogIn(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.courierLogIn(courierData)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue());
        courierService.deleteCourier(courierData);
    }
    @Test
    @Issue("Ошибка 504 при попытке входа без логина")
    public  void failedLogInWithoutData(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.courierLogIn(new CourierLoginData(null,null))
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
        courierService.deleteCourier(courierData);
    }
    @Test
    @Issue("Ошибка 504 при попытке входа без логина")
    public  void failedLogInWithoutLogin(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.courierLogIn(new CourierLoginData(null,courierData.getPassword()))
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
        courierService.deleteCourier(courierData);
    }
    @Test
    public  void failedLogInWithoutPassword(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.courierLogIn(new CourierLoginData(courierData.getLogin(),null))
                .then()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
        courierService.deleteCourier(courierData);
    }
    @Test
    public  void failedLogInWithWrongLogin(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.courierLogIn(new CourierLoginData(genString(),courierData.getPassword()))
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
        courierService.deleteCourier(courierData);
    }
    @Test
    public  void failedLogInWithWrongPassword(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.courierLogIn(new CourierLoginData(courierData.getLogin(),genString()))
                .then()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
        courierService.deleteCourier(courierData);
    }
}
