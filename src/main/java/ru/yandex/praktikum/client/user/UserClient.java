package ru.yandex.praktikum.client.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.client.RestClient;
import ru.yandex.praktikum.model.user.User;
import ru.yandex.praktikum.model.user.UserWithoutEmail;
import ru.yandex.praktikum.model.user.UserWithoutName;
import ru.yandex.praktikum.model.user.UserWithoutPassword;

import java.util.Map;

import static ru.yandex.praktikum.constants.Endpoints.*;

public class UserClient extends RestClient {
    @Step("Создание пользователя")
    public Response create(User user) {
        return getDefaultRequestSpecification()
                .body(user)
                .when()
                .post(ENDPOINT_FOR_CREATE_USER);
    }

    @Step("Создание пользователя без поля email")
    public Response createWithoutEmail(UserWithoutEmail userWithoutEmail) {
        return getDefaultRequestSpecification()
                .body(userWithoutEmail)
                .when()
                .post(ENDPOINT_FOR_CREATE_USER);
    }

    @Step("Создание пользователя без поля email")
    public Response createWithoutPassword(UserWithoutPassword userWithoutPassword) {
        return getDefaultRequestSpecification()
                .body(userWithoutPassword)
                .when()
                .post(ENDPOINT_FOR_CREATE_USER);
    }

    @Step("Создание пользователя без поля name")
    public Response createWithoutName(UserWithoutName userWithoutPassword) {
        return getDefaultRequestSpecification()
                .body(userWithoutPassword)
                .when()
                .post(ENDPOINT_FOR_CREATE_USER);
    }

    @Step("Удаление пользователя")
    public void deleteUser(User user, String accessToken) {
        getDefaultRequestSpecification()
                .header("Authorization", accessToken)
                .body(user)
                .delete(ENDPOINT_FOR_DELETE_USER);
    }

    @Step("Авторизация пользователя")
    public Response login(User user) {
        return getDefaultRequestSpecification()
                .body(user)
                .when()
                .post(ENDPOINT_FOR_LOGIN_USER);
    }

    @Step("Обновление данных пользователя")
    public Response updateUserWithLogin(Map<String, String> updateData, String accessToken) {
        return getDefaultRequestSpecification()
                .header("Authorization", accessToken)
                .body(updateData)
                .patch(ENDPOINT_FOR_DELETE_USER);
    }

    @Step("Обновление данных без авторизации пользователя")
    public Response updateUserWithoutLogin(Map<String, String> updateData) {
        return getDefaultRequestSpecification()
                .body(updateData)
                .patch(ENDPOINT_FOR_DELETE_USER);
    }
}
