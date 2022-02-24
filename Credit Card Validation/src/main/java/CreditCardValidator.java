import java.util.ArrayList;
import java.util.List;

public class CreditCardValidator {
  private final int CREDIT_CARD_LENGTH = 16;

  public String validateNumbers(String creditCardNumber) {
    boolean letterFound = containsNotOnlyDigits(creditCardNumber);

    if (!letterFound) {
      String[] cardDigitsArray = creditCardNumber.split(" ");
      if (cardDigitsArray.length == 4) {
        return paymentSystemResolver(cardDigitsArray[0]);
      } else if (creditCardNumber.length() == 16) {
        return paymentSystemResolver(creditCardNumber.substring(0, 4));
      } else {
        return "";
      }
    }
    return "";
  }

  private boolean containsNotOnlyDigits(String creditCardNumber) {
    for (int i = 0; i < creditCardNumber.length(); i++) {
      if (Character.isWhitespace(creditCardNumber.charAt(i)))
        continue;

      if (!Character.isDigit(creditCardNumber.charAt(i))) {
        return true;
      }
    }

    return false;
  }

  private String paymentSystemResolver(String prefix) {
    String systemName = "";

    for (CardType cardType : CardType.values()) {
      for (String cardPrefix : cardType.getPrefixs()) {
        if (prefix.startsWith(cardPrefix)) {
          systemName = cardType.getPaymentSystemName();
          break;
        }
      }
    }

    if (systemName.length() == 0) {
      return "";
    } else {
      return systemName;
    }
  }

  public void validateCreditCard(String creditCardNumber) {
    String validatedNumbers = validateNumbers(creditCardNumber);

    if (validatedNumbers.length() > 0) {
      System.out.printf("Card is valid. Payment System is \"%s\"\n", validatedNumbers);
    } else {
      List<String> errorList = new ArrayList<>();
      errorList.add("Payment System can't be determine");

      if (creditCardNumber.length() != this.CREDIT_CARD_LENGTH) {
        if (creditCardNumber.split(" ").length != 4) {
          errorList.add("Length should be 16 symbols");
        }
      }

      for (int i = 0; i < creditCardNumber.length(); i++) {
        if (Character.isWhitespace(creditCardNumber.charAt(i)))
          continue;

        if (!Character.isDigit(creditCardNumber.charAt(i))) {
          errorList.add("Number should contain only digits");
          break;
        }
      }

      System.out.println("Errors:");
      for (String error : errorList) {
        System.out.println(error);
      }
    }
  }
}
