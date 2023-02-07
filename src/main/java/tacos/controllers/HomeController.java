package tacos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller.
public class HomeController {

    @GetMapping("/") // Обрабатывает запросы с корневым путем "/".
    public String home(){
        return "home"; // Возвращает имя представления.
    }
}
