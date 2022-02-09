public class InvalidCreditCardType extends RuntimeException {
  public InvalidCreditCardType(String errorMessage) {
    super(errorMessage);
  }
}
