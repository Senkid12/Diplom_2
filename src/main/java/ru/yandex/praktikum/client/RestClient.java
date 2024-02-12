package ru.yandex.praktikum.client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.constants.Endpoints.BASE_PATH;
import static ru.yandex.praktikum.constants.Endpoints.BASE_URI;

public abstract class RestClient {
    protected RequestSpecification getDefaultRequestSpecification() {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH);
    }
}
