package com.mr.texasholdem.hand;

public class FullHouse extends Hand {
  private final ThreeOfAKind threeOfAKind;

  private final Pair pair;

  public FullHouse(ThreeOfAKind threeOfAKind, Pair pair) {
    super(FULL_HOUSE_VALUE, threeOfAKind.getRank());
    this.threeOfAKind = threeOfAKind;
    this.pair = pair;
  }

  public ThreeOfAKind getThreeOfAKind() {
    return threeOfAKind;
  }

  public Pair getPair() {
    return pair;
  }
}
