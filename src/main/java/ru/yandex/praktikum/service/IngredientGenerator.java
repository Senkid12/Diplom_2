package ru.yandex.praktikum.service;

import ru.yandex.praktikum.client.ingredient.IngredientClient;
import ru.yandex.praktikum.model.ingredient.Data;
import ru.yandex.praktikum.model.ingredient.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientGenerator {
    private final IngredientClient ingredientClient = new IngredientClient();
    Ingredient ingredient = ingredientClient.getDefaultIngredient();
    private Map<String, String[]> ingredientMap = new HashMap<>();
    List<Data> ingredients = ingredient.getData();

    public Map<String, String[]> getCorrectIngredients() {
        String[] hashIngredient = new String[ingredients.size()];
        for (int i = 0; i < hashIngredient.length; i++) {
            hashIngredient[i] = ingredients.get(i).get_id();
        }
        ingredientMap.put("ingredients", hashIngredient);
        return ingredientMap;
    }

    public Map<String, String[]> getEmptyIngredients() {
        return ingredientMap;
    }

    public Map<String, String[]> getIncorrectIngredients() {
        ingredientMap.put("ingredients", new String[]{"invalid hash_1"});
        return ingredientMap;
    }
}
