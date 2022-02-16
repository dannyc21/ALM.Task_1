import java.util.ArrayList;
import java.util.List;

enum CardType {
  VISA("VISA", "4"),
  JCB("JCB", "35"),
  DISCOVER("DISCOVER", "6011", "65"),
  MASTERCARD("MASTERCARD", "51", "52", "53", "54", "55"),
  AMERICAN_EXPRESS("AMERICAN EXPRESS", "34", "37");
  
  private final String paymentName;
  private final List<String> prefix;
  
  CardType(String paymentName, String ...prefix) {
    this.paymentName = paymentName;
    this.prefix = new ArrayList<String>(List.of(prefix));
  }
  
  public List<String> getPrefix() {
    return this.prefix;
  }
  public String getPaymentName() {
    return this.paymentName;
  }
}

public class CreditCardValidator {
  private final int CREDIT_CARD_LENGTH = 16;
  private final String creditCardNumber;

  public CreditCardValidator(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public String validateNumbers() {
    for (int i = 0; i < creditCardNumber.length(); i++) {
      if (Character.isWhitespace(creditCardNumber.charAt(i)))
        continue;

      if (!Character.isDigit(creditCardNumber.charAt(i))) {
        return null;
      }
    }

    String[] cardDigitsArray = creditCardNumber.split(" ");
    if (cardDigitsArray.length == 4) {
      return PaymentSystemResolver(cardDigitsArray[0]);
    } else if (creditCardNumber.length() == 16) {
      return PaymentSystemResolver(creditCardNumber.substring(0, 4));
    } else {
      return null;
    }
  }

  private String PaymentSystemResolver(String prefix) {
    String systemName;
    String prefixFirstTwo = prefix.substring(0, 2);

    if (CardType.VISA.getPrefix().contains(prefix.substring(0,1)))
      systemName = CardType.VISA.getPaymentName();
    else if (CardType.MASTERCARD.getPrefix().contains(prefixFirstTwo))
      systemName = CardType.MASTERCARD.getPaymentName();
    else if (CardType.JCB.getPrefix().contains(prefixFirstTwo))
      systemName = CardType.JCB.getPaymentName();
    else if (CardType.DISCOVER.getPrefix().contains(prefix) || CardType.DISCOVER.getPrefix().contains(prefixFirstTwo))
      systemName = CardType.DISCOVER.getPaymentName();
    else if (CardType.AMERICAN_EXPRESS.getPrefix().contains(prefixFirstTwo))
      systemName = CardType.AMERICAN_EXPRESS.getPaymentName();
    else
      systemName = null;

    return systemName;
  }

  public void validateCreditCard() {
    String validatedNumbers = validateNumbers();

    if (validatedNumbers != null) {
      System.out.printf("Card is valid. Payment System is \"%s\"\n", validatedNumbers);
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

      throw new InvalidCreditCardType("Payment System can't be determine");
    }
  }
}
