package com.mr.texasholdem.hand;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.Rank;

public class TwoPairs extends Hand {

  private final Pair pair1;

  private final Pair pair2;

  public TwoPairs(Pair pair1, Pair pair2, Card[] kickers) {
    super(TWO_PAIRS_VALUE, getMaxRank(pair1, pair2), getMinRank(pair1, pair2), kickers);
    this.pair1 = pair1;
    this.pair2 = pair2;
  }

  private static Rank getMaxRank(Pair pair1, Pair pair2) {
    return pair1.getRank().compareTo(pair2.getRank()) > 0 ? pair1.getRank() : pair2.getRank();
  }

  private static Rank getMinRank(Pair pair1, Pair pair2) {
    return pair1.getRank().compareTo(pair2.getRank()) > 0 ? pair2.getRank() : pair1.getRank();
  }

  public Pair getPair1() {
    return pair1;
  }

  public Pair getPair2() {
    return pair2;
  }

}
