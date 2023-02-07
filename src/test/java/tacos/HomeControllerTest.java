package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import tacos.controllers.HomeController;

@WebMvcTest(HomeController.class) // Тест для HomeController.
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc; // Внедрить MockMvc.

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))                      // Выполнить запрос GET /.
                .andExpect(status().isOk())                      // Ожидается код ответа HTTP 200.
                .andExpect(view().name("home"))   // Ожидается имя представления home.
                .andExpect(content().string(                     // Ожидается наличие строки "Welcome to...".
                        containsString("Welcome to...")));
    }

}