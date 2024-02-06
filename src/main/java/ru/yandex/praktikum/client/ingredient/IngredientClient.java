package ru.yandex.praktikum.client.ingredient;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import ru.yandex.praktikum.client.RestClient;
import ru.yandex.praktikum.model.ingredient.Ingredient;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.constants.Endpoints.*;

public class IngredientClient extends RestClient {
    public Response getAllIngredient() {
        return getDefaultRequestSpecification()
                .get(ENDPOINT_FOR_GET_INGREDIENT);
    }

    public Ingredient getDefaultIngredient() {
        Ingredient ingredient =
                given()
                        .contentType(ContentType.JSON)
                        .baseUri(BASE_URI)
                        .basePath(BASE_PATH)
                        .get(ENDPOINT_FOR_GET_INGREDIENT)
                        .body()
                        .as(Ingredient.class);
        return ingredient;
    }
}