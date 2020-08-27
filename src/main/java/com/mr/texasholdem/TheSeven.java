package com.mr.texasholdem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TheSeven implements Comparable<TheSeven> {

  private final Card[] holeCards;

  private final CommunityCards communityCards;

  private Hand hand;

  public TheSeven(CommunityCards communityCards, Card[] holeCards) {
    this.communityCards = communityCards;
    this.holeCards = holeCards;
    evaluate();
  }

  private List<Card> getAllCardsAsList() {
    return Stream.concat(Arrays.stream(holeCards), Arrays.stream(communityCards.getCards())).collect(Collectors.toList());
  }

  private void evaluate() {
    List<Card> cards = getAllCardsAsList();
    boolean found = evaluatePair(new ArrayList<>(cards));
    if (!found) {
      evaluateHighCard(cards);
    }
  }

  private void evaluateHighCard(List<Card> cards) {
    hand = new HighCard(Collections.max(cards, new CardRankComparator()));
  }

  private boolean evaluatePair(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    Pair pair = !pairs.isEmpty() ? Collections.max(pairs, new PairRankComparator()) : null;
    return (hand = pair) != null;
  }

  private List<Pair> evaluatePairs(List<Card> cards, List<Pair> pairs) {
    int size = cards.size();
    Card card1 = cards.get(0);
    for (int a = 1; a < size; a++) {
      Card card2 = cards.get(a);
      if (card1.equalsByRank(card2)) {
        pairs.add(new Pair(card1, card2));
        cards.remove(card1);
        cards.remove(card2);
        if (cards.size() > 1) {
          return evaluatePairs(cards, pairs);
        }
      }
    }
    return pairs;
  }

  public int getHandValue() {
    return hand == null ? 0 : hand.getValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    TheSeven theSeven = (TheSeven) o;
    return hand.equals(theSeven.hand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hand);
  }

  @Override
  public int compareTo(TheSeven o) {
    return getHandValue() - o.getHandValue();
  }
}
