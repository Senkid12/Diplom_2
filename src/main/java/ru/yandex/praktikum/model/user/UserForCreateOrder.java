package ru.yandex.praktikum.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForCreateOrder {
    private String name;
    private String email;
    private String createAt;
    private String updateAt;
}
