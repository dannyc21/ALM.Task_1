package com.example.application.creditCard;

import java.util.ArrayList;

public class CreditCard {
  private static final int CREDIT_CARD_LENGTH = 16;

  public String validateNumbers(String creditCardNumber)
  {
    boolean letterFound = doesContainNotOnlyDigits(creditCardNumber);

    if(!letterFound)
    {
      if(creditCardNumber.length() == CREDIT_CARD_LENGTH)
      {
        return CardType.resolvePaymentSystem(creditCardNumber);
      }
    }

    return "";
  }

  public ArrayList<String> validateCreditCard(String creditCardNumber)
  {
    ArrayList<String> errorList = new ArrayList<>();
    errorList.add("Payment System can't be determine");

    if(creditCardNumber.length() != CREDIT_CARD_LENGTH)
    {
      errorList.add("Length should be 16 symbols");
    }

    if(doesContainNotOnlyDigits(creditCardNumber))
    {
      errorList.add("Should contain only digits");
    }

    return errorList;
  }

  private boolean doesContainNotOnlyDigits(String creditCardNumber)
  {
    for(int i=0; i< creditCardNumber.length(); i++)
    {
      if(Character.isWhitespace(creditCardNumber.charAt(i)))
      {
        continue;
      }

      if(!Character.isDigit(creditCardNumber.charAt(i)))
      {
        return true;
      }
    }

    return false;
  }
}
