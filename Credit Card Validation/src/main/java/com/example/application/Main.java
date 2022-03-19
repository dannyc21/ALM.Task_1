package com.example.application;

import com.example.application.creditCard.CreditCard;

import java.util.Scanner;

public class Main {
  private static final Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {
    CreditCard creditCard = new CreditCard();

    System.out.println("Hello. Enter card number for validation:");
    String cardNumbers = userInput.nextLine();

    validateCard(creditCard, cardNumbers);
  }

  private static void validateCard(CreditCard creditCard, String cardNumbers) {
    if (creditCard.validateNumbers(cardNumbers).length() != 0) {
      System.out.println("Credit card is valid .... " + creditCard.validateNumbers(cardNumbers));
    } else {
      creditCard.validateCreditCard(cardNumbers).forEach(System.out::println);
    }
  }
}
