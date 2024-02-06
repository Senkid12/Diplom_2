package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.order.OrderClient;
import ru.yandex.praktikum.model.ingredient.Data;
import ru.yandex.praktikum.model.order.OrderAfterCreate;
import ru.yandex.praktikum.service.IngredientGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class CreateOrdersTest extends UserCreateAndDeleteTest {
    private final OrderClient orderClient = new OrderClient();
    private final IngredientGenerator ingredientGenerator = new IngredientGenerator();

    @Test
    @DisplayName("Создание заказа авторизированного пользователя")
    public void createCorrectOrderForLoginUser() {
        Map<String, String[]> ingredientMap = ingredientGenerator.getCorrectIngredients();
        Response response = orderClient.createOrderWithLogin(ingredientMap, accessToken);

        OrderAfterCreate orderAfterCreate = response
                .body()
                .jsonPath()
                .getObject("order", OrderAfterCreate.class);
        List<String> expected = new ArrayList<>(Arrays.asList(ingredientMap.get("ingredients")));
        List<String> actual = new ArrayList<>();
        for (Data data : orderAfterCreate.getIngredients()) {
            actual.add(data.get_id());
        }
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Создание заказа с авторизацией без ингредиентов")
    public void createOrderWithLoginWithoutIngredient() {
        Map<String, String[]> ingredientMap = ingredientGenerator.getEmptyIngredients();
        Response response = orderClient.createOrderWithLogin(ingredientMap, accessToken);

        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).and().body("message", equalTo("Ingredient ids must be provided")).and().body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создание заказа с авторизацией с некорректным ингредиентом")
    public void createOrderWithLoginIncorrectIngredient() {
        Map<String, String[]> ingredientMap = ingredientGenerator.getIncorrectIngredients();
        Response response = orderClient.createOrderWithLogin(ingredientMap, accessToken);

        response.then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void createOrderWithoutLogin() {
        Map<String, String[]> ingredientMap = ingredientGenerator.getCorrectIngredients();
        Response response = orderClient.createOrderWithoutLogin(ingredientMap);

        response.then().statusCode(HttpStatus.SC_OK).and().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа без авторизации и без ингредиентов")
    public void createOrderWithoutLoginWithoutIngredient() {
        Map<String, String[]> ingredientMap = ingredientGenerator.getEmptyIngredients();
        Response response = orderClient.createOrderWithoutLogin(ingredientMap);

        response.then().statusCode(HttpStatus.SC_BAD_REQUEST).and().body("success", equalTo(false));
    }

    @Test
    @DisplayName("Создание заказа без авторизации с некорректным ингредиентом")
    public void createOrderWithoutLoginIncorrectIngredient() {
        Map<String, String[]> ingredientMap = ingredientGenerator.getIncorrectIngredients();
        Response response = orderClient.createOrderWithoutLogin(ingredientMap);

        response.then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
