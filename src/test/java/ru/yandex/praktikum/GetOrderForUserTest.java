package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import ru.yandex.praktikum.client.order.OrderClient;
import ru.yandex.praktikum.service.IngredientGenerator;

import static org.hamcrest.Matchers.equalTo;
public class GetOrderForUserTest extends UserCreateAndDeleteTest{

    private final IngredientGenerator ingredientGenerator = new IngredientGenerator();
    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Получение заказов для авторизованного пользователя")
    public void getOrdersWithLogin() {
        orderClient.createOrderWithLogin(ingredientGenerator.getCorrectIngredients(), accessToken);
        Response response = orderClient.getOrderForUserWithLogin(accessToken);

        response.then().statusCode(HttpStatus.SC_OK).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Получение заказов для неавторизованного пользователя")
    public void getOrdersWithoutLogin() {
        Response response = orderClient.getOrderForUserWithoutLogin();

        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED).and().body("success", equalTo(false));
    }
}
