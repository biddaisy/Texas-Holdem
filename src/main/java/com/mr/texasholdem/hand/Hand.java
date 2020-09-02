package com.mr.texasholdem.hand;

import java.util.Arrays;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.card.Rank;

public class Hand implements Comparable<Hand> {
  public static final int HIGH_CARD_VALUE = 1;

  public static final int PAIR_VALUE = 2;

  public static final int TWO_PAIRS_VALUE = 3;

  public static final int THREE_OF_A_KIND_VALUE = 4;

  public static final int STRAIGHT_VALUE = 5;

  public static final int FLUSH_VALUE = 6;

  public static final int FULL_HOUSE_VALUE = 7;

  public static final int FOUR_OF_A_KIND_VALUE = 8;

  public static final int STRAIGHT_FLUSH_VALUE = 9;

  public static final int ROYAL_FLUSH_VALUE = 10;

  private final int value;

  private final Rank rank;

  private final HandPriority handPriority;

  public Hand(int value, Rank rank) {
    this.value = value;
    this.rank = rank;
    handPriority = new HandPriority(value, rank);
  }

  protected static Card[] sortHandByRank(Card[] cards) {
    if (cards == null || cards.length != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards: " + Arrays.toString(cards));
    }
    Arrays.sort(cards, new CardRankComparator());
    return cards;
  }

  public int getValue() {
    return value;
  }

  public Rank getRank() {
    return rank;
  }

  public HandPriority getHandPriority() {
    return handPriority;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Hand hand = (Hand) o;
    return getHandPriority().equals(hand.getHandPriority());
  }

  @Override
  public int hashCode() {
    return getHandPriority().hashCode();
  }

  @Override
  public int compareTo(Hand o) {
    return getHandPriority().compareTo(o.getHandPriority());
  }

}
