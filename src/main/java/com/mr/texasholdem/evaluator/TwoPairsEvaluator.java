package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Pair;
import com.mr.texasholdem.hand.TwoPairs;

public class TwoPairsEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    PairEvaluator pairEvaluator = new PairEvaluator();
    List<Pair> pairs = pairEvaluator.findPairs(cards, asList(cards), new ArrayList<>());
    int size = pairs.size();
    if (size < 2) {
      return null;
    }
    Collections.sort(pairs);
    Pair pair1 = pairs.get(size - 1);
    Pair pair2 = pairs.get(size - 2);
    Card[] kickers = findKickers(cards, pair1.getCard1(), pair1.getCard2(), pair2.getCard1(), pair2.getCard2());
    return new TwoPairs(pair1, pair2, kickers);
  }

  @Override
  public int priority() {
    return Hand.TWO_PAIRS_VALUE;
  }
}
