package tacos.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;


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

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    /** Метод addIngredientsToModel извлекает все ингредиенты из базы данных, вызывая метод findAll() */
    /** внедренного экземпляра IngredientRepository. Затем он фильтрует их по типам ингредиентов и */
    /** добавляет в модель. */

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));

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

    // @Valid - требует выполнить проверку отправленного объекта Taco после его привязки
    // к отправленной форме, но до начала выполнения тела метода processTaco().
    // Если обнаружатся какие-либо ошибки, то сведенья о них будут зафиксированы в объекте Errors
    // который передается в processTaco().
    // Первые несколько строк в processTaco() проверяют наличие ошибок, вызывая метод hasErrors()
    // объекта Errors. Если ошибки есть, то метод processTaco() щавершает работу без обработки Taco
    // и возвращает имя представления "design", что бы повторно отобразить форму.

    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:orders/current";
    }

    // redirect - сообщает что это представление с перенаправлением, то есть после завершения processTaco()
    // браузер пользователя должен открыть другую страницу, отправив запрос GET с путем orders/current.
}
