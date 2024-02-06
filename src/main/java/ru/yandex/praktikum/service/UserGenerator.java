package ru.yandex.praktikum.service;

import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import ru.yandex.praktikum.model.user.User;
import ru.yandex.praktikum.model.user.UserWithoutEmail;
import ru.yandex.praktikum.model.user.UserWithoutName;
import ru.yandex.praktikum.model.user.UserWithoutPassword;

public class UserGenerator {
    Faker faker = new Faker();

    public User getUser() {
        return User.builder()
                .email(String.format("%s@yandex.ru", faker.name().username()))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .name(faker.name().fullName())
                .build();
    }

    public UserWithoutEmail getUserWithoutEmail() {
        return UserWithoutEmail.builder()
                .password(RandomStringUtils.randomAlphanumeric(10))
                .name(faker.name().fullName())
                .build();
    }

    public UserWithoutPassword getUserWithoutPassword() {
        return UserWithoutPassword.builder()
                .email(String.format("%s@yandex.ru", faker.name().username()))
                .name(faker.name().fullName())
                .build();
    }

    public UserWithoutName getUserWithoutName() {
        return UserWithoutName.builder()
                .email(String.format("%s@yandex.ru", faker.name().username()))
                .password(RandomStringUtils.randomAlphanumeric(10))
                .build();
    }
}