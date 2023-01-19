package Models.courier;

import com.github.javafaker.Faker;

public class CourierLoginData {
    private String login;
    private String  password;
    public CourierLoginData(String login, String  password){
        this.login = login;
        this.password = password;
    }
    public CourierLoginData(){
        Faker faker = new Faker();
        login = faker.name().firstName();
        password = faker.name().firstName();
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
