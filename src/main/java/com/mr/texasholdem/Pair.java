package com.mr.texasholdem;

public class Pair extends Hand{

  private Card card1;

  private Card card2;

  private Rank rank;

  public Pair(Card card1, Card card2) {
    super(2);
    if (!card1.equalsByRank(card2)){
      throw new IllegalArgumentException("card ranks must be identical");
    }
    this.card1 = card1;
    this.card2 = card2;
    this.rank = card1.getRank();
  }

  public Card getCard1() {
    return card1;
  }

  public void setCard1(Card card1) {
    this.card1 = card1;
  }

  public Card getCard2() {
    return card2;
  }

  public void setCard2(Card card2) {
    this.card2 = card2;
  }

  public Rank getRank() {
    return rank;
  }
}
