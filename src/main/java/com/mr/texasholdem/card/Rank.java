package com.mr.texasholdem.card;

public enum Rank {

  ONE('1'),
  TWO('2'),
  THREE('3'),
  FOUR('4'),
  FIVE('5'),
  SIX('6'),
  SEVEN('7'),
  EIGHT('8'),
  NINE('9'),
  TEN('T'),
  JACK('J'),
  QUEEN('Q'),
  KING('K'),
  ACE('A');

  private final char code;

  Rank(char code) {
    this.code = code;
  }

  public static Rank valueOf(char rankCode) {
    switch (rankCode) {
      case '1':
        return ONE;
      case '2':
        return TWO;
      case '3':
        return THREE;
      case '4':
        return FOUR;
      case '5':
        return FIVE;
      case '6':
        return SIX;
      case '7':
        return SEVEN;
      case '8':
        return EIGHT;
      case '9':
        return NINE;
      case 'T':
        return TEN;
      case 'J':
        return JACK;
      case 'Q':
        return QUEEN;
      case 'K':
        return KING;
      case 'A':
        return ACE;
      default:
        throw new IllegalArgumentException("wrong rank code '" + "'");
    }
  }

  @Override
  public String toString() {
    return this == ONE? ACE.toString() : String.valueOf(code);
  }
}
