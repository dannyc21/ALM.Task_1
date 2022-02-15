import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum CardType {
  VISA("VISA", "4"),
  JCB("JCB", "35"),
  DISCOVER("DISCOVER", "6011", "65"),
  MASTERCARD("MASTERCARD", "51", "52", "53", "54", "55"),
  AMERICAN_EXPRESS("AMERICAN EXPRESS", "34", "37");
  
  private String paymentName;
  private String[] prefix;
  
  private CardType(String paymentName, String ...prefix) {
    this.paymentName = paymentName;
    this.firstDigits = firstDigits;
  }
  
  public String[] getFirstDigits() {
    return this.firstDigits;
  }
}

public class CreditCardValidator {
  private static final CREDIT_CARD_LENGTH = 16;
  private final String creditCardNumber;
  private String creditCardType;

  public CreditCardValidator(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public boolean validateNumbers() {
    for (int i = 0; i < creditCardNumber.length(); i++) {
      if (Character.isWhitespace(creditCardNumber.charAt(i)))
        continue;

      if (!Character.isDigit(creditCardNumber.charAt(i))) {
        return false;
      }
    }

    String[] cardDigitsArray = creditCardNumber.split(" ");
    if (cardDigitsArray.length == 4) {
      this.creditCardType = getCreditCardType(cardDigitsArray[0]);
      return this.creditCardType != null;
    } else if (creditCardNumber.length() == 16) {
      this.creditCardType = getCreditCardType(creditCardNumber.substring(0, 4));
      return this.creditCardType != null;
    } else {
      return false;
    }
  }

//   TODO: Finish the edits
  private String getCreditCardType(String firstFourNumbers) {
    if (firstFourNumbers.startsWith(CardType.VISA.getFirstDigits()[0]))
      return "VISA";
    else if (Array.asList(CardType.MASTERCARD).contains(firstFourNumbers.substring(0,2))) {
      return "MASTERCARD";
    } else if (Array.asList(CardType.MASTERCARD).contains(firstFourNumbers))
      return "DISCOVER";
    else if (firstFourNumbers / 100 == 35)
      return "JCB";
    else if (firstFourNumbers / 100 == 34 || firstFourNumbers / 100 == 37)
      return "AMERICAN EXPRESS";

    return null;
  }

  public void validateCreditCard() {
    boolean isValidCard = validateNumbers();

    if (isValidCard) {
      System.out.printf("Card is valid. Payment System is \"%s\"\n", this.creditCardType);
    } else {

      System.out.println("Errors:");
      if (this.creditCardNumber.length() != this.CREDIT_CARD_LENGTH)
        if (this.creditCardNumber.split(" ").length != 4)
          throw new RuntimeException("Length should be 16 symbols");

      for (int i = 0; i < this.creditCardNumber.length(); i++) {
        if (Character.isWhitespace(this.creditCardNumber.charAt(i)))
          continue;

        if (!Character.isDigit(this.creditCardNumber.charAt(i))) {
          throw new RuntimeException("Number should contain only digits");
        }
      }

      if (this.creditCardType == null)
        throw new InvalidCreditCardType("Payment System can't be determine");
    }
  }
}
