import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);

    System.out.println("Hello. Enter card number for validation:");
    String cardNumbers = userInput.nextLine();

    CreditCardValidator creditCard = new CreditCardValidator(cardNumbers);
    try {
      creditCard.validateCreditCard();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
