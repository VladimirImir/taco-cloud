package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// implements WebMvcConfigurer.

@SpringBootApplication
public class TacoCloudApplication  {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    // Вариант # 3;

    /*@Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
    }*/
}
