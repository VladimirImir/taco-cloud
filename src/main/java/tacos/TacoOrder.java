package tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {

    @NotBlank(message = "Укажите имя доставки.")
    private String deliveryName;

    @NotBlank(message = "Укажите улицу.")
    private String deliveryStreet;

    @NotBlank(message = "Укажите город.")
    private String deliveryCity;

    @NotBlank(message = "Укажите состояние.")
    private String deliveryState;

    @NotBlank(message = "Требуется почтовый индекс.")
    private String deliveryZip;

    // @CreditCardNumber - Эта аннотация требует, что бы значение свойства было действительным номером
    // кредитной карты , прошедшим проверку алгоритмом Луна.

    @CreditCardNumber(message = "Недействительный номер кредитной карты.")
    private String Number;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Должен быть формат MM/YY")
    private String Expiration; // Окончаниие-срок действия.

    // @Digits - Гарантирует что значение содержит ровно три цифры.

    @Digits(integer=3, fraction=0, message="Неверный CVV")
    private String CVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }
}
