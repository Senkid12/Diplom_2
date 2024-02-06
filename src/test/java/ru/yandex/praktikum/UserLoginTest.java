package ru.yandex.praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.user.UserClient;

import static org.hamcrest.Matchers.equalTo;

public class UserLoginTest extends UserCreateAndDeleteTest {
    private final UserClient userClient = new UserClient();

    @Test
    @DisplayName("Авторизация пользователя")
    public void loginUser() {
        System.out.printf(" Авторизация пользователя%n email: %s%n password: %s%n name: %s%n", user.getEmail(), user.getPassword(), user.getName());
        userClient.login(user)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Авторизация с некорректным полем email")
    public void loginUserWithIncorrectEmail() {
        user.setEmail(String.format("%s@yandex.ru", RandomStringUtils.randomAlphabetic(5)));
        System.out.printf(" Авторизация пользователя%n email: %s%n password: %s%n name: %s%n", user.getEmail(), user.getPassword(), user.getName());
        userClient.login(user)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false));
    }

    @Test
    @DisplayName("Авторизация с некорректным полем password")
    public void loginUserWithIncorrectPassword() {
        user.setPassword(RandomStringUtils.randomAlphanumeric(10));
        System.out.printf(" Авторизация пользователя%n email: %s%n password: %s%n name: %s%n", user.getEmail(), user.getPassword(), user.getName());
        userClient.login(user)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false));
    }
}