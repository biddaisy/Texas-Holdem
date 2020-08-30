package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Pair;
import com.mr.texasholdem.hand.TwoPairs;

public class TwoPairsEvaluator extends AbstractHandEvaluator {

  public Hand evaluate(Card[] cards) {
    PairEvaluator pairEvaluator = new PairEvaluator();
    List<Pair> pairs = pairEvaluator.evaluatePairs(asList(cards), new ArrayList<>());
    int size = pairs.size();
    if (size < 2) {
      return null;
    }
    Collections.sort(pairs);
    return new TwoPairs(pairs.get(size - 1), pairs.get(size - 2));
  }

  @Override
  public int priority() {
    return Hand.TWO_PAIRS_VALUE;
  }
}
