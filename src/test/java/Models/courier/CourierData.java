package Models.courier;

import com.github.javafaker.Faker;


public class CourierData {
    private String login;
    private String  password;
    private String firstName;
    public CourierData(String login, String  password, String firstName){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CourierData(){
        Faker faker = new Faker();
        login = faker.name().firstName();
        password = faker.name().firstName();
        firstName = faker.name().firstName();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
