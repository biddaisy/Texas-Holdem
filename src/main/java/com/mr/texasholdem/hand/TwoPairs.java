package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Rank;

public class TwoPairs extends Hand {

  private final Pair pair1;

  private final Pair pair2;

  public TwoPairs(Pair pair1, Pair pair2) {
    super(3, getRank(pair1, pair2));
    this.pair1 = pair1;
    this.pair2 = pair2;
  }

  public Pair getPair1() {
    return pair1;
  }

  public Pair getPair2() {
    return pair2;
  }

  private static Rank getRank(Pair pair1, Pair pair2) {
    return pair1.getRank().compareTo(pair2.getRank()) > 0 ? pair1.getRank() : pair2.getRank();
  }

}
