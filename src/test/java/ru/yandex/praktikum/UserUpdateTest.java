package ru.yandex.praktikum;

import io.restassured.response.Response;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.user.UserClient;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class UserUpdateTest extends UserCreateAndDeleteTest {
    private final String email;
    private final String password;
    private final String name;
    private static final String OLD = "old";
    static Faker faker = new Faker();
    private final UserClient userClient = new UserClient();

    public UserUpdateTest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Parameterized.Parameters()
    public static Object[][] getParameters() {
        return new Object[][]{
                {OLD, OLD, OLD},
                {uniqueEmail(), RandomStringUtils.randomAlphanumeric(10), faker.name().fullName()},
                {OLD, RandomStringUtils.randomAlphanumeric(10), faker.name().fullName()},
                {OLD, RandomStringUtils.randomAlphanumeric(10), OLD},
                {uniqueEmail(), RandomStringUtils.randomAlphanumeric(10), OLD},
                {uniqueEmail(), OLD, OLD},
                {uniqueEmail(), OLD, faker.name().fullName()},
                {OLD, OLD, faker.name().fullName()},
                {OLD, RandomStringUtils.randomAlphanumeric(10), OLD},
                {OLD, RandomStringUtils.randomAlphanumeric(10), faker.name().fullName()}
        };
    }

    @Test
    @DisplayName("Обновление данных пользователя")
    public void updateUserWithLogin() {
        Map<String, String> dataForUpdate = createHashMapForUpdate(email, password, name);
        System.out.println(" Обновляемые данные: " + dataForUpdate);
        Response response = userClient.updateUserWithLogin(dataForUpdate, accessToken);
        response.then().statusCode(HttpStatus.SC_OK).and().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Обновление данных без авторизации пользователя")
    public void updateUserWithoutLogin() {
        Map<String, String> dataForUpdate = createHashMapForUpdate(email, password, name);
        System.out.println(" Обновляемые данные: " + dataForUpdate);
        Response response = userClient.updateUserWithoutLogin(dataForUpdate);
        response.then().statusCode(HttpStatus.SC_UNAUTHORIZED).and().assertThat().body("success", equalTo(false));
    }

    private Map<String, String> createHashMapForUpdate(String email, String password, String name) {
        Map<String, String> updateData = new HashMap<>();
        if (email.equals(OLD)) {
            updateData.put("email", user.getEmail());
        } else {
            updateData.put("email", email);
        }
        if (password.equals(OLD)) {
            updateData.put("password", user.getPassword());
        } else {
            updateData.put("password", password);
        }
        if (name.equals(OLD)) {
            updateData.put("name", user.getName());
        } else {
            updateData.put("name", name);
        }
        return updateData;
    }

    private static String uniqueEmail() {
        return String.format("%s@yandex.ru", RandomStringUtils.randomAlphabetic(5));
    }
}
