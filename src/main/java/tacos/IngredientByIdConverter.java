package tacos;

// Преобразование строк в объекты Ingredient.

// Конвертер - это любой класс, который реализует интерфейс Converter с методом convert(), получающим
// значение одного типа и преобразующим его в значение другого типа.
// Что бы преобразовать String в Ingredient, мы будем использовать IngredientByIdConverter.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.data.IngredientRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }

}
