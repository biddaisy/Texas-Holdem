package com.mr.texasholdem;

import java.util.Objects;

public class Hand implements Comparable<Hand> {
  private final int value;

  private final Rank rank;

  public Hand(int value, Rank rank) {
    this.value = value;
    this.rank = rank;
  }

  public int getValue() {
    return value;
  }

  public Rank getRank() { return rank;}
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Hand hand = (Hand) o;
    return value == hand.value && rank == hand.rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, rank);
  }

  @Override
  public int compareTo(Hand o) {
    int res = value - o.value;
    return res == 0 ? rank.compareTo(o.rank) : res;
  }
}
