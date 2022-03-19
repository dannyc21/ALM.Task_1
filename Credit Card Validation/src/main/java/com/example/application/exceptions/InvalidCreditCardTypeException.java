package com.example.application.exceptions;

public class InvalidCreditCardTypeException extends RuntimeException {
  public InvalidCreditCardTypeException(String errorMessage)
  {
    super(errorMessage);
  }
}
