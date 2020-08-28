package com.mr.texasholdem;

import java.util.Objects;

public class Card {
  private final Rank rank;

  private final Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Card(String cardCode){
    if (cardCode == null || cardCode.length() != 2){
      throw new IllegalArgumentException("card code length must be 2");
    }
    this.rank = Rank.valueOf(cardCode.charAt(0));
    this.suit = Suit.valueOf(cardCode.charAt(1));
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  public boolean equalsByRank(Card card) {
    return this.getRank().compareTo(card.getRank()) == 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Card card = (Card) o;
    return rank == card.rank && suit == card.suit;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank, suit);
  }

  @Override public String toString() {
    return rank.toString() + suit.toString();
  }

  public static Card valueOf(String cardCode) {
    if (cardCode == null || cardCode.length() != 2) {
      throw new IllegalArgumentException("wrong card code '" + cardCode + "'");
    }
    return new Card(Rank.valueOf(cardCode.charAt(0)), Suit.valueOf(cardCode.charAt(1)));
  }
}
