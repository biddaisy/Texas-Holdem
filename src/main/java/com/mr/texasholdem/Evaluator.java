package com.mr.texasholdem;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.HighCard;
import com.mr.texasholdem.hand.Pair;
import com.mr.texasholdem.hand.TwoPairs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Evaluator {

  private Hand hand;

  public Hand evaluate(List<Card> cards) {
    boolean found = evaluateTwoPairs(new ArrayList<>(cards));
    if (found)
      return hand;
    found = evaluatePair(new ArrayList<>(cards));
    if (found)
      return hand;
    evaluateHighCard(cards);
    return hand;
  }

  private void evaluateHighCard(List<Card> cards) {
    hand = new HighCard(Collections.max(cards, new CardRankComparator()));
  }

  private boolean evaluateTwoPairs(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    int size = pairs.size();
    if (size < 2) {
      return false;
    }
    Collections.sort(pairs);
    hand = new TwoPairs(pairs.get(size - 1), pairs.get(size - 2));
    return true;
  }

  private boolean evaluatePair(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    Pair pair = !pairs.isEmpty() ? Collections.max(pairs) : null;
    return (hand = pair) != null;
  }

  private List<Pair> evaluatePairs(List<Card> cards, List<Pair> pairs) {
    int size = cards.size();
    Card card1 = cards.get(0);
    for (int a = 1; a < size; a++) {
      Card card2 = cards.get(a);
      if (card1.equalsByRank(card2)) {
        pairs.add(new Pair(card1, card2));
        cards.remove(card2);
        break;
      }
    }
    cards.remove(card1);
    if (cards.size() > 1) {
      return evaluatePairs(cards, pairs);
    }
    return pairs;
  }

}
