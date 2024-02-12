package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import io.qameta.allure.junit4.DisplayName;
import ru.yandex.praktikum.client.user.UserClient;
import ru.yandex.praktikum.model.user.User;
import ru.yandex.praktikum.service.UserGenerator;

public class UserCreateAndDeleteTest {
    protected String accessToken;
    protected User user;
    protected final UserGenerator userGenerator = new UserGenerator();
    protected final UserClient userClient = new UserClient();
    protected Response responseForCreateUser;

    @Before
    public void setUp() {
        user = userGenerator.getUser();
        responseForCreateUser = userClient.create(user);
        accessToken = responseForCreateUser.body().path("accessToken");
    }

    @After
    @DisplayName("Удаление user")
    public void deleteUser() {
        if (user != null) {
            System.out.println("Пользователь " + user.getName() + " Был удален");
            userClient.deleteUser(user, accessToken);
        }
    }
}
