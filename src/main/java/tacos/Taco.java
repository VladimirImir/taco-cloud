package tacos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Имя должно быть не менее 5 символов.")
    private String name;

    @NotNull
    @Size(min = 1, message = "Вы должны выбрать как минимум 1 ингредиент.")
    private List<Ingredient> ingredients;
}
