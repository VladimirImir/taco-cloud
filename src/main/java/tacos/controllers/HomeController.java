package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller.
public class HomeController {

    // Вариант # 1;

    // @WebMvcTest(HomeController.class) - в HomeControllerTest нужно удалить ссылку
    // если будем использовать WebConfig .

    @GetMapping("/") // Обрабатывает запросы с корневым путем "/".
    public String home(){
        return "home"; // Возвращает имя представления.
    }
}
