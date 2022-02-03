public class CreditCardValidator {
  private String creditCardNumber;
  private String creditCardType;

  public CreditCardValidator(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public boolean validateNumbers() {
    for (int i=0; i<creditCardNumber.length(); i++) {
      if (Character.isWhitespace(creditCardNumber.charAt(i)))
        continue;

      if (!Character.isDigit(creditCardNumber.charAt(i))) {
        return false;
      }
    }

    String[] cardDigitsArray = creditCardNumber.split(" ");
    if (cardDigitsArray.length == 4) {
      this.creditCardType = getCreditCardType(Integer.parseInt(cardDigitsArray[0]));
      return this.creditCardType != null;
    } else {
      if (creditCardNumber.length() == 16) {
        this.creditCardType = getCreditCardType(Integer.parseInt(creditCardNumber.substring(0, 4)));
        return this.creditCardType != null;
      }
    }
    return false;
  }

  private String getCreditCardType(int firstFourNumbers) {
    if (firstFourNumbers/1000 == 4)
      return "VISA";
    else if (firstFourNumbers/1000 == 5) {
      switch(firstFourNumbers/100) {
        case 51:
        case 52:
        case 53:
        case 54:
        case 55:
          return "MASTERCARD";
      }
    } else if (firstFourNumbers == 6011 || firstFourNumbers/100 == 65)
      return "DISCOVER";
    else if (firstFourNumbers/100 == 35)
      return "JCB";
    else if (firstFourNumbers/100 == 34 || firstFourNumbers/100 == 37)
      return "AMERICAN EXPRESS";

    return null;
  }

  public void validateCreditCard() {
    boolean isValidCard = validateNumbers();

    if (isValidCard) {
      System.out.printf("Card is valid. Payment System is \"%s\"\n", this.creditCardType);
    } else {

      System.out.println("Errors:");
      if (this.creditCardNumber.length() != 16)
        throw new RuntimeException("Length should be 16 symbols");

      for (int i = 0; i < this.creditCardNumber.length(); i++) {
        if (!Character.isDigit(this.creditCardNumber.charAt(i))) {
          throw new RuntimeException("Number should contain only digits");
        }
      }

      if (this.creditCardType == null)
        throw new RuntimeException("Payment System can't be determine");
    }
  }

  public String getCreditCardNumber() {
    return this.creditCardNumber;
  }

  @Override
  public String toString() {
    return String.valueOf(this.creditCardNumber);
  }
}
