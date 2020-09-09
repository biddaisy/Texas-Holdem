package com.mr.texasholdem.hand;

import java.util.Objects;

import com.mr.texasholdem.card.Rank;

public class HandPriority implements Comparable<HandPriority> {

  private final int value;

  private final Rank rank;

  private final Rank rank2;

  private final Rank kickerRank;

  protected HandPriority(int value, Rank rank, Rank rank2, Rank kickerRank) {
    this.value = value;
    this.rank = rank;
    this.rank2 = rank2;
    this.kickerRank = kickerRank;
  }

  public int getValue() {
    return value;
  }

  public Rank getRank() {
    return rank;
  }

  public Rank getRank2() {
    return rank2;
  }

  public Rank getKickerRank() {
    return kickerRank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    HandPriority that = (HandPriority) o;
    return value == that.value && rank == that.rank && rank2 == that.rank2 && kickerRank == that.kickerRank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, rank, rank2, kickerRank);
  }

  @Override
  public int compareTo(HandPriority o) {
    int res = value - o.value;
    if (res == 0 ) {
      res = rank.compareTo(o.rank);
    }
    if (res == 0 ) {
      res = rank2.compareTo(o.rank2);
    }
    if (res == 0 && kickerRank != null && o.kickerRank != null){
      res = kickerRank.compareTo(o.kickerRank);
    }
    return res;
  }
}
