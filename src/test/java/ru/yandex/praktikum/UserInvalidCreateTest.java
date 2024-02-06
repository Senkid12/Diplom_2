package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.user.UserClient;
import ru.yandex.praktikum.model.user.UserWithoutEmail;
import ru.yandex.praktikum.model.user.UserWithoutName;
import ru.yandex.praktikum.model.user.UserWithoutPassword;
import ru.yandex.praktikum.service.UserGenerator;

import static org.hamcrest.Matchers.equalTo;

public class UserInvalidCreateTest {
    private final UserClient userClient = new UserClient();
    private final UserGenerator userGenerator = new UserGenerator();
    @Test
    @DisplayName("Создание пользователя без поля email")
    public void createWithoutEmailUser() {
        UserWithoutEmail userWithoutEmail = userGenerator.getUserWithoutEmail();
        Response response = userClient.createWithoutEmail(userWithoutEmail);
        System.out.printf(" Создание пользователя%n password: %s%n name: %s%n", userWithoutEmail.getPassword(), userWithoutEmail.getName());
        response.then().statusCode(HttpStatus.SC_FORBIDDEN).and().assertThat().body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создание пользователя без поля password")
    public void createWithoutPasswordUser() {
        UserWithoutPassword userWithoutPassword = userGenerator.getUserWithoutPassword();
        Response response = userClient.createWithoutPassword(userWithoutPassword);
        System.out.printf(" Создание пользователя%n email: %s%n name: %s%n", userWithoutPassword.getEmail(), userWithoutPassword.getName());
        response.then().statusCode(HttpStatus.SC_FORBIDDEN).and().assertThat().body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создание пользователя без поля name")
    public void createWithoutNameUser() {
        UserWithoutName userWithoutName = userGenerator.getUserWithoutName();
        Response response = userClient.createWithoutName(userWithoutName);
        System.out.printf(" Создание пользователя%n email: %s%n password: %s", userWithoutName.getEmail(), userWithoutName.getPassword());
        response.then().statusCode(HttpStatus.SC_FORBIDDEN).and().assertThat().body("success", equalTo(false));
    }
}
