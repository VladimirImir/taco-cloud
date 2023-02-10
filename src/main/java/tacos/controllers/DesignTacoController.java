package tacos.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
                new Ingredient("FLTO", "Мучная Тортилья", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Кукурузная Тортилья", Ingredient.Type.WRAP),
                new Ingredient("GRBF", "Говяжий фарш", Ingredient.Type.PROTEIN),
                new Ingredient("CARN", "Карнитас", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Нарезанные помидоры", Ingredient.Type.VEGGIES),
                new Ingredient("LETC", "Салат", Ingredient.Type.VEGGIES),
                new Ingredient("CHED", "Чеддер", Ingredient.Type.CHEESE),
                new Ingredient("JACK", "Монтерей джек", Ingredient.Type.CHEESE),
                new Ingredient("SLSA", "Сальса", Ingredient.Type.SAUCE),
                new Ingredient("SRCR", "Сметана", Ingredient.Type.SAUCE)
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

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping // PostMapping - аннотация которую мы применили к методу processTaco() сообщает
                    // анотации @RequestMapping на уровне класса что processTaco() будет обрабатывать
                    // запросы POST с путем /design.

    // @ModelAttribute - аннотация перед параметром TacoOrder указывает, что он должен использовать
    // объект TacoOrder, который был помещен в модель методом order() с аннотацией @ModelAttribute.

    public String processTaco(Taco taco, @ModelAttribute TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:orders/current";
    }

    // redirect - сообщает что это представление с перенаправлением, то есть после завершения processTaco()
    // браузер пользователя должен открыть другую страницу, отправив запрос GET с путем orders/current.
}
