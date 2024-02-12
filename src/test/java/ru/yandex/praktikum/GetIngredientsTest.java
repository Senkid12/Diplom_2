package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.ingredient.IngredientClient;

import static org.hamcrest.Matchers.equalTo;

public class GetIngredientsTest {
    private final IngredientClient ingredientClient = new IngredientClient();

    @Test
    @DisplayName("Получение ингредиентов")
    public void getAllIngredients() {
        Response response = ingredientClient.getAllIngredient();
        response.then().statusCode(HttpStatus.SC_OK).and().body("success", equalTo(true));
    }
}

