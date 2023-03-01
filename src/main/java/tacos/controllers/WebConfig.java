package tacos.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// WebConfig - реализует интерфейс WebMvcConfigurer.

// Вариант # 2;

// @WebMvcTest(HomeController.class) - в HomeControllerTest нужно удалить ссылку и закоментить метод home()
// который если будем использовать WebConfig .

@Configuration
public class WebConfig implements WebMvcConfigurer {
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }*/
}
