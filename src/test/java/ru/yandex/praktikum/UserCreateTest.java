package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.user.UserClient;

import static org.hamcrest.Matchers.equalTo;

public class UserCreateTest extends UserCreateAndDeleteTest {
    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUser() {
        System.out.printf(" Создание пользователя%n email: %s%n password: %s%n name: %s%n", user.getEmail(), user.getPassword(), user.getName());
        responseForCreateUser.then().statusCode(HttpStatus.SC_OK).and().assertThat().body("success", equalTo(true));
        System.out.println();
    }

    @Test
    @DisplayName("Создание существующего пользователя")
    public void createAnExistingUser() {
        System.out.printf(" Создание пользователя%n email: %s%n password: %s%n name: %s%n", user.getEmail(), user.getPassword(), user.getName());
        responseForCreateUser.then().statusCode(HttpStatus.SC_OK).and().assertThat().body("success", equalTo(true));

        Response response1 = userClient.create(user);
        System.out.printf(" Создание пользователя%n email: %s%n password: %s%n name: %s%n", user.getEmail(), user.getPassword(), user.getName());
        response1.then().statusCode(HttpStatus.SC_FORBIDDEN).and().assertThat().body("success", equalTo(false));
    }
}
