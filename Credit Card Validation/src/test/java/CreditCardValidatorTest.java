import com.example.application.creditCard.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CreditCardValidatorTest {

  @Test
  @DisplayName("Should Not Create Credit Card When Contain Letters")
  public void shouldThrowRuntimeExceptionWhenCreditCardContainLetter() {
    CreditCard creditCard = new CreditCard();
    Assertions.assertThrows(RuntimeException.class, (Executable) creditCard.validateCreditCard("4245255"));
  }
}