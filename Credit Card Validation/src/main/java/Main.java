import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

enum Season { MASTERCARD }

public class Main {
  private static final Scanner userInput = new Scanner(System.in);
  private static final int[] MASTERCARD = {34, 37};

  public static void main(String[] args) {
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
