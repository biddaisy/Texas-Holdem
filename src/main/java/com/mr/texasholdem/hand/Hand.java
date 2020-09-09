package com.mr.texasholdem.hand;

import java.util.Arrays;
import java.util.Collections;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.card.Rank;

public abstract class Hand implements Comparable<Hand> {
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

  private final HandPriority handPriority;

  private final Card[] kickers;

  protected Hand(int value, Rank rank, Card[] kickers) {
    this(value, rank, rank, kickers);
  }

  protected Hand(int value, Rank rank, Rank rank2, Card[] kickers) {
    this.kickers = kickers;
    verifyKickers();
    this.handPriority = new HandPriority(value, rank, rank2, findMaxKickerRank() );
  }

  private Rank findMaxKickerRank() {
    if (kickers.length == 0) {
      return null;
    }
    else {
      return Collections.max(Arrays.asList(kickers), new CardRankComparator()).getRank();
    }
  }

  protected static Card[] sortHandByRank(Card[] cards) {
    if (cards == null || cards.length != 5) {
      throw new IllegalArgumentException("Hand must have 5 cards: " + Arrays.toString(cards));
    }
    Arrays.sort(cards, new CardRankComparator());
    return cards;
  }

  public Rank getRank() {
    return handPriority.getRank();
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

  protected void verifyKickers(){
    int kickersMax;
    if (this instanceof HighCard){
      kickersMax = 4;
    } else if (this instanceof Pair && !(this instanceof ThreeOfAKind)){
      kickersMax = 3;
    } else if (this instanceof TwoPairs){
      kickersMax = 1;
    } else if (this instanceof ThreeOfAKind && !(this instanceof FourOfAKind)){
      kickersMax = 2;
    } else if (this instanceof FourOfAKind){
      kickersMax = 1;
    }
    else {
      return;
    }
    if (kickers.length > kickersMax){
      throw new IllegalArgumentException("kicker cards cannot be more than " + kickersMax + ": " + Arrays.toString(kickers));
    }
  }
}
