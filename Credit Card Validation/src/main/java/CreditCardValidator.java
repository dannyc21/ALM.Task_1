public class CreditCardValidator {
  private String creditCardNumber;
  private String creditCardType;

  public CreditCardValidator(String creditCardNumber) {
    boolean isValidCard = validateNumbers(creditCardNumber);

    if (isValidCard) {
      this.creditCardNumber = creditCardNumber;
      System.out.printf("Card is valid. Payment System is \"%s\"\n", this.creditCardType);
    } else {
      validationErrors(creditCardNumber);
    }
  }

  public boolean validateNumbers(String creditCardNumber) {
    String[] cardDigitsArray = creditCardNumber.split(" ");
    if (cardDigitsArray.length == 4) {
      this.creditCardType = getCreditCardType(Integer.parseInt(cardDigitsArray[0]));
      return this.creditCardType != null;
    } else {
      if (creditCardNumber.length() == 16) {
        for (int i=0; i<creditCardNumber.length(); i++) {
          if (!Character.isDigit(creditCardNumber.charAt(i))) {
            return false;
          }
        }

        this.creditCardType = getCreditCardType(Integer.parseInt(creditCardNumber.substring(0, 4)));
        return this.creditCardType != null;
      }
    }
    return false;
  }

  private String getCreditCardType(int numberSet) {
    if (numberSet/1000 == 4)
      return "VISA";
    else if (numberSet/1000 == 5) {
      switch(numberSet/100) {
        case 51:
        case 52:
        case 53:
        case 54:
        case 55:
          return "MASTERCARD";
      }
    } else if (numberSet == 6011 || numberSet/100 == 65)
      return "DISCOVER";
    else if (numberSet/100 == 35)
      return "JCB";
    else if (numberSet/100 == 34 || numberSet/100 == 37)
      return "AMERICAN EXPRESS";

    return null;
  }

  public void validationErrors(String creditCardNumber) {
    System.out.println("Errors:");
    if (creditCardNumber.length() != 16)
      System.out.println(" -> Length should be 16 symbols");

    for (int i=0; i<creditCardNumber.length(); i++) {
      if (!Character.isDigit(creditCardNumber.charAt(i))) {
        System.out.println(" -> Number should contain only digits");
        break;
      }
    }

    if (this.creditCardType == null)
      System.out.println(" -> Payment System can't be determine");
  }

  public String getCreditCardNumber() {
    return this.creditCardNumber;
  }

  @Override
  public String toString() {
    return String.valueOf(this.creditCardNumber);
  }
}
