import java.util.Scanner;

public class Main {
  private static final Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {

    System.out.println("Hello. Enter card number for validation:");
    String cardNumbers = userInput.nextLine();

    CreditCardValidator creditCard = new CreditCardValidator();
    try {
      creditCard.validateCreditCard(cardNumbers);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
