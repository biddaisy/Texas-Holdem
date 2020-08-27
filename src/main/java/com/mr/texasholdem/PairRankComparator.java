package com.mr.texasholdem;

import java.util.Comparator;

public class PairRankComparator implements Comparator<Pair> {
  @Override
  public int compare(Pair pair1, Pair pair2) {
    return pair1.getRank().compareTo(pair2.getRank());
  }
}
