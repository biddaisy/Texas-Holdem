package com.mr.texasholdem.evaluator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mr.texasholdem.card.Card;

public abstract class AbstractHandEvaluator implements HandEvaluator {

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AbstractHandEvaluator abstractHandEvaluator = (AbstractHandEvaluator) o;
    return priority() == abstractHandEvaluator.priority();
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority());
  }

  @Override
  public String toString() {
    return "evaluator of priority " + priority();
  }

  @Override
  public int compareTo(HandEvaluator o) {
    return priority() - o.priority();
  }

  protected static List<Card> asList(Card[] cards) {
    return Arrays.stream(cards).collect(Collectors.toList());
  }

}
