package com.example.application.creditCard;

import java.util.List;

public enum CardType {
  VISA("VISA", "4"),
  JCB("JCB", "35"),
  DISCOVER("DISCOVER", "6011", "65"),
  MASTERCARD("MASTERCARD", "51", "52", "53", "54", "55"),
  AMERICAN_EXPRESS("AMERICAN EXPRESS", "34", "37");

  private final String paymentSystemName;
  private final List<String> prefixes;

  CardType(String paymentSystemName, String ...prefix)
  {
    this.paymentSystemName = paymentSystemName;
    this.prefixes = List.of(prefix);
  }

  public List<String> getPrefixes()
  {
    return this.prefixes;
  }

  public String getPaymentSystemName()
  {
    return paymentSystemName;
  }

  public static String resolvePaymentSystem(String prefix)
  {
    for(CardType cardType : CardType.values())
    {
      for(String cardPrefix : cardType.getPrefixes())
      {
        if(prefix.startsWith(cardPrefix))
        {
          return cardType.getPaymentSystemName();
        }
      }
    }

    return "";
  }

}
