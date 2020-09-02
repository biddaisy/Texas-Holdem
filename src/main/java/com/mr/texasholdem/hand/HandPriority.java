package com.mr.texasholdem.hand;

import java.util.Objects;

import com.mr.texasholdem.card.Rank;

public class HandPriority implements Comparable<HandPriority> {

  private final int value;

  private final Rank rank;

  public HandPriority(int value, Rank rank) {
    this.value = value;
    this.rank = rank;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    HandPriority that = (HandPriority) o;
    return value == that.value && rank == that.rank;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, rank);
  }

  @Override
  public int compareTo(HandPriority o) {
    int res = value - o.value;
    return res == 0 ? rank.compareTo(o.rank) : res;
  }
}
