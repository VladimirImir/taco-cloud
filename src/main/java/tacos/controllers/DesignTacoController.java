package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import tacos.Ingredient;
import tacos.Taco;
import tacos.TacoOrder;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j                         // Slf4j - эта аннотицая реализована в Lombok.
                               // Slf4j - Автоматически генерирует статическое свойство Logger,
                               // определяемого библиотекой Slf4j(Simple Logging Facade for Java)
                               // - простой интерфейс журналирования для Java.

@Controller                    // Controller - Эта аннотация идентифицирует этот класс как контролеер
                               // и отмечает его как доступный для механизма сканирования компонентов,
                               // что бы фреймворк Spring мог обнаружить его и автоматически создать
                               // экземпляр DesignTacoController в виде bean-компонента в контексте приложения.

@RequestMapping("/design") // RequestMapping - Опредиляет тип запросов, который обрабатывает этот контроллер.
                             // В данном случае она сообщает что DesignTacoController будет обрабатывать
                             // запросы, пути в который начинаются с /design.

@SessionAttributes("tacoOrder") // SessionAttributes - эта аннотация указывает что объект TacoOrder
                                // объявленный в классе чуть ниже, должен поддерживаться на уровне сеанса.
                                // Это важно, потому что создание тако так же является первым шагов в создании
                                // заказа, и созданный нами заказ необходимо будет перенести в сеанс,
                                // охватывающий несколько запросов.

// Анностация @RequestMapping на уровне класса дополнена аннотацией @GetMapping на уровне метода showDesignForm()
// Аннотация @GetMapping в сочетании с @RequestMapping на уровне класса определяет метод, в данном случае
// showDesignForm(), который должен вызываться для обработки HTTP-запроса GET путем /design.

// @GetMapping - это всего лишь одна из целого семейства аннотаций сопоставления запросов.

// Все аннотации из семейства, доступные в Spring MVC:

// @RequestMapping - Обобщенная обработка запросов.
// @GetMapping - Обработка HTTP-запросов GET.
// @PostMapping - Обработка HTTP-запросов POST.
// @PutMapping - Обработка HTTP-запросов PUT.
// @DeleteMapping - Обработка HTTP-запросов DELETE.
// @PatchMapping - Обработка HTTP-запросов PATCH.

// Model - это объект, в котором данные пересылаются между контроллером и любым представлением, ответственным
// за преобразование этих данных в разметку HTML.

public class DesignTacoController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));

        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
