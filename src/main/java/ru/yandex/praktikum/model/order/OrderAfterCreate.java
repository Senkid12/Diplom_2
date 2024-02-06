package ru.yandex.praktikum.model.order;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.yandex.praktikum.model.ingredient.Data;
import ru.yandex.praktikum.model.user.UserForCreateOrder;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAfterCreate {
    private Data[] ingredients;
    private String _id;
    private UserForCreateOrder owner;
    private String status;
    private String name;
    private String createAt;
    private String updateAt;
    private int number;
    private int price;

}
