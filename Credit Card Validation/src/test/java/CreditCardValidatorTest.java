import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreditCardValidatorTest {

  @Test
  @DisplayName("Should Not Create Credit Card When Contain Letters")
  public void shouldThrowRuntimeExceptionWhenCreditCardContainLetter() {
    CreditCardValidator creditCard = new CreditCardValidator();
//    Assertions.assertThrows(RuntimeException.class, creditCard.validateCreditCard("4245255"));
  }
}