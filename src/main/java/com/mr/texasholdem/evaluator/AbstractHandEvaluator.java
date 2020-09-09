package com.mr.texasholdem.evaluator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;

public abstract class AbstractHandEvaluator implements HandEvaluator {

  protected static List<Card> asList(Card[] cards) {
    return Arrays.stream(cards).collect(Collectors.toList());
  }

  protected static Card[] findKickers(Card[] cards, Card... handCards) {
    if (handCards.length > 5) {
      throw new IllegalArgumentException("wrong amount of hand cards : " + Arrays.toString(handCards));
    }
    if (handCards.length == 5) {
      return new Card[0];
    }
    if (cards.length < 5){
      return new Card[0];
    }
    List<Card> kickers = asList(cards);
    kickers.removeAll(asList(handCards));
    kickers.sort(new CardRankComparator());
    int size = kickers.size();
    return kickers.subList(size - 5 + handCards.length, size).toArray(new Card[] {});
  }

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

}
