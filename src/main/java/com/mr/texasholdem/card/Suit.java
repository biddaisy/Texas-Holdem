package com.mr.texasholdem.card;

import com.mr.texasholdem.WrongInputParameterException;

public enum Suit {
  DIAMONDS('d'),
  CLUBS('c'),
  HEARTS('h'),
  SPADES('s');

  private final char code;

  Suit(char code) {
    this.code = code;
  }

  public static Suit valueOf(char suitCode) throws WrongInputParameterException {
    switch (suitCode) {
      case 'd':
        return DIAMONDS;
      case 'c':
        return CLUBS;
      case 'h':
        return HEARTS;
      case 's':
        return SPADES;
      default:
        throw new WrongInputParameterException("wrong suit code '" + suitCode + "'");
    }
  }

  @Override
  public String toString() {
    return String.valueOf(code);
  }
}
