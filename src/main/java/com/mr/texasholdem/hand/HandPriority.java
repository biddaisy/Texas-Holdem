package com.mr.texasholdem.hand;

import java.util.Arrays;
import java.util.Objects;

import com.mr.texasholdem.card.Rank;

public class HandPriority implements Comparable<HandPriority> {

  private final int value;

  private final Rank rank;

  private final Rank rank2;

  private final Rank[] kickerRanks;

  protected HandPriority(int value, Rank rank, Rank rank2, Rank[] kickerRanks) {
    this.value = value;
    this.rank = rank;
    this.rank2 = rank2;
    this.kickerRanks = kickerRanks;
    Arrays.sort(kickerRanks);
  }

  protected int getValue() {
    return value;
  }

  protected Rank getRank() {
    return rank;
  }

  protected Rank getRank2() {
    return rank2;
  }

  protected Rank[] getKickerRanks() {
    return kickerRanks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    HandPriority that = (HandPriority) o;
    return value == that.value && rank == that.rank && rank2 == that.rank2 && Arrays.equals(kickerRanks, that.kickerRanks);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(value, rank, rank2);
    result = 31 * result + Arrays.hashCode(kickerRanks);
    return result;
  }

  @Override
  public int compareTo(HandPriority o) {
    int res = value - o.value;
    if (res == 0) {
      res = rank.compareTo(o.rank);
    }
    if (res == 0) {
      res = rank2.compareTo(o.rank2);
    }
    for (int a = kickerRanks.length - 1; res == 0 && a >= 0; a--) {
      res = kickerRanks[a].compareTo(o.kickerRanks[a]);
    }
    return res;
  }
}
