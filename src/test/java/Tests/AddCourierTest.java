package Tests;

import Models.courier.CourierData;
import Services.CourierService;
import com.github.javafaker.Faker;
import io.qameta.allure.Issue;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class AddCourierTest {
    CourierService courierService = new CourierService();

    public String genString() {
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";}

    @Test
    public  void createNewCourier(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        assertNotNull(courierService.getCourierId(courierData));

        courierService.deleteCourier(courierData);
    }
    @Test
    @Issue("Ошибка в сообщении ответа")
    public  void createCourierWithSameData(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.createCourier(courierData)
                .then()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
        courierService.deleteCourier(courierData);
    }
    @Test
    public  void createCourierWithoutData(){
        CourierData courierData = new CourierData(null, null, null );
        courierService.createCourier(courierData)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public  void createCourierWithoutPassword(){
        CourierData courierData = new CourierData(genString(), null, genString() );
        courierService.createCourier(courierData)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    public  void createCourierWithoutLogin(){
        CourierData courierData = new CourierData(null,genString(), genString());
        courierService.createCourier(courierData)
                .then().assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
    @Test
    public  void createNewCourierReturnStatus(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData)
                .then().assertThat().statusCode(201);
        courierService.deleteCourier(courierData);
    }
    @Test
    public  void createNewCourierReturnValue(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData)
                .then().assertThat()
                .body("ok", equalTo(true));
        courierService.deleteCourier(courierData);
    }
    @Test
    @Issue("Ошибка в сообщении ответа")
    public  void createCourierWithSameExistingLogin(){
        CourierData courierData = new CourierData();
        courierService.createCourier(courierData);
        courierService.createCourier(new CourierData(courierData.getLogin(), genString(), genString()))
                .then()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется"));
        courierService.deleteCourier(courierData);
    }

}
