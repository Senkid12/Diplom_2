package ru.yandex.praktikum.client.order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.client.RestClient;

import java.util.Map;

import static ru.yandex.praktikum.constants.Endpoints.ENDPOINT_FOR_GET_ORDERS_FOR_USER;

public class OrderClient extends RestClient {

    @Step("Создание заказа с авторизацией")
    public Response createOrderWithLogin(Map<String, String[]> ingredients, String accessToken) {
        return getDefaultRequestSpecification()
                .header("Authorization", accessToken)
                .body(ingredients)
//                .post(ENDPOINT_FOR_CREATE_ORDER);
                .post(ENDPOINT_FOR_GET_ORDERS_FOR_USER);
    }

    @Step("Создание заказа без авторизацией")
    public Response createOrderWithoutLogin(Map<String, String[]> ingredients) {
        return getDefaultRequestSpecification()
                .body(ingredients)
                .post(ENDPOINT_FOR_GET_ORDERS_FOR_USER);

    }

    @Step("Получение заказа конкретного пользователя")
    public Response getOrderForUserWithLogin(String accessToken) {
        return getDefaultRequestSpecification()
                .header("Authorization", accessToken)
                .get(ENDPOINT_FOR_GET_ORDERS_FOR_USER);
    }

    @Step("Получение заказа конкретного пользователя без авторизации")
    public Response getOrderForUserWithoutLogin() {
        return getDefaultRequestSpecification()
                .get(ENDPOINT_FOR_GET_ORDERS_FOR_USER);
    }
}
