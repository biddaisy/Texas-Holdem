package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Pair;

public class PairEvaluator extends AbstractHandEvaluator {

  public Hand evaluate(Card[] cards) {
    List<Pair> pairs = evaluatePairs(asList(cards), new ArrayList<>());
    return !pairs.isEmpty() ? Collections.max(pairs) : null;
  }

  @Override
  public int priority() {
    return Hand.PAIR_VALUE;
  }

  protected List<Pair> evaluatePairs(List<Card> cards, List<Pair> pairs) {
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
