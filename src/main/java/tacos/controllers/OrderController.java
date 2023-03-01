package tacos.controllers;

// Контроллер представляющий форму заказа Тако.

// RequestMapping - Аннотация на уровне класса сообщает, что любые методы обработки запросов в этом контроллере
// будут обрабатывать запросы с путями, начинающимися с /orders.
// В сочетании с ней аннотация @GetMapping на уровне метода orderForm() указывает, что этот метод будет
// обрабатывать HTTP-запросы GET с путем /orders/current.

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    /* Когда приложение вызовет метод processOrder() для обработки отправленного заказа, ему будет  */
    /* передан объект TacoOrder со значениями свойств, полученными из полей отправленной формы. */
    /* В настоящее время метод processOrder() просто регистрирует объект TacoOrder в журнале. */

    /* Прежде чем завершиться, метод processOrder() вызывает метод setComplete() объекта SessionStatus, */
    /* переданного в параметре. Первоначально объект TacoOrder был создан и перемещен в сеанс,  */
    /* когда пользователь создавал свой первый Тако. Вызывая setComplete(), мы гарантируем, что сеанс */
    /* очищен и готов к приему нового заказа, когда пользователь создас Тако. */
    /*  */

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus){

        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
