package com.mr.texasholdem;

import java.util.Objects;

public class Hand implements Comparable<Hand> {
  private final int value;

  public Hand(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Hand hand = (Hand) o;
    return value == hand.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public int compareTo(Hand o) {
    return value - o.value;
  }
}
