public class CreditCardValidator {
  private final int CREDIT_CARD_LENGTH = 16;

  public String validateNumbers(String creditCardNumber) {
    boolean letterFound = hasLetter(creditCardNumber);

    if (!letterFound) {
      String[] cardDigitsArray = creditCardNumber.split(" ");
      if (cardDigitsArray.length == 4) {
        return PaymentSystemResolver(cardDigitsArray[0]);
      } else if (creditCardNumber.length() == 16) {
        return PaymentSystemResolver(creditCardNumber.substring(0, 4));
      } else {
        return "";
      }
    }
    return "";
  }

  private boolean hasLetter(String creditCardNumber) {
    for (int i = 0; i < creditCardNumber.length(); i++) {
      if (Character.isWhitespace(creditCardNumber.charAt(i)))
        continue;

      if (!Character.isDigit(creditCardNumber.charAt(i))) {
        return true;
      }
    }

    return false;
  }

  private String PaymentSystemResolver(String prefix) {
    String systemName = "";
    String prefixFirstTwo = prefix.substring(0, 2);

    for (CardType cardType : CardType.values()) {
      if (cardType.getPrefixs().contains(prefix.substring(0, 1)) || cardType.getPrefixs().contains(prefixFirstTwo)) {
        systemName = cardType.getPaymentSystemName();
      }
    }

    return systemName;
  }

  public void validateCreditCard(String creditCardNumber) {
    String validatedNumbers = validateNumbers(creditCardNumber);

    if (validatedNumbers.length() > 0) {
      System.out.printf("Card is valid. Payment System is \"%s\"\n", validatedNumbers);
    } else {

      System.out.println("Errors:");
      if (creditCardNumber.length() != this.CREDIT_CARD_LENGTH)
        if (creditCardNumber.split(" ").length != 4)
          throw new RuntimeException("Length should be 16 symbols");

      for (int i = 0; i < creditCardNumber.length(); i++) {
        if (Character.isWhitespace(creditCardNumber.charAt(i)))
          continue;

        if (!Character.isDigit(creditCardNumber.charAt(i))) {
          throw new RuntimeException("Number should contain only digits");
        }
      }

      throw new InvalidCreditCardType("Payment System can't be determine");
    }
  }
}
