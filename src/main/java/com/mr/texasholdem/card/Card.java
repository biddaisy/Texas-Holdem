package com.mr.texasholdem.card;

import com.mr.texasholdem.WrongInputParameterException;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Card {
  private Rank rank;

  private final Suit suit;

  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  public Card(String cardCode) throws WrongInputParameterException {
    if (cardCode == null || cardCode.length() != 2) {
      throw new WrongInputParameterException("Card code length must be 2: " + cardCode);
    }
    this.rank = Rank.valueOf(cardCode.charAt(0));
    this.suit = Suit.valueOf(cardCode.charAt(1));
  }

  public Rank getRank() {
    return rank;
  }

  private int getRankValue() {
    return rank.ordinal();
  }

  public boolean isFollowedBy(Card card) {
    return (card.getRankValue() - getRankValue()) == 1;
  }

  public Suit getSuit() {
    return suit;
  }

  public Card transfiguration() {
    if (rank == Rank.ACE) {
      rank = Rank.ONE;
    }
    else
      if (rank == Rank.ONE) {
        rank = Rank.ACE;
      }
      else {
        throw new UnsupportedOperationException();
      }
    return this;
  }

  public boolean equalsByRank(Card card) {
    return this.getRank() == card.getRank();
  }

  public boolean equalsBySuit(Card card) {
    return this.getSuit() == card.getSuit();
  }

  public Card copy() {
    return new Card(rank, suit);
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

  @Override
  public String toString() {
    return rank.toString() + suit.toString();
  }

  public static Card valueOf(String cardCode) throws WrongInputParameterException {
    if (cardCode == null || cardCode.length() != 2) {
      throw new IllegalArgumentException("wrong Card code '" + cardCode + "'");
    }
    return new Card(Rank.valueOf(cardCode.charAt(0)), Suit.valueOf(cardCode.charAt(1)));
  }

  public static Card findAce(Card[] cards) {
    return findCardByRank(cards, Rank.ACE);
  }

  public static Card findCardByRank(Card[] cards, Rank rank) {
    return Stream.of(cards).filter(c -> c.getRank() == rank).findFirst().orElseGet(() -> null);
  }

  public static boolean hasCard(Card[] cards, Card card) {
    return Arrays.asList(cards).contains(card);
  }

  public static void validateCard(Card[] cards, Card card) throws WrongInputParameterException {
    if (Card.hasCard(cards, card)){
      throw new WrongInputParameterException("Card " + card + " entered at least 2 times");
    }
  }
}
