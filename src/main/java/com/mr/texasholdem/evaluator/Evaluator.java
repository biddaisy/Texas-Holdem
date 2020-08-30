package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;

public class Evaluator {

  private final List<HandEvaluator> handEvaluators = new ArrayList<>();

  public Evaluator() {
    handEvaluators.add(new HighCardEvaluator());
    handEvaluators.add(new PairEvaluator());
    handEvaluators.add(new TwoPairsEvaluator());
    handEvaluators.add(new ThreeOfAKindEvaluator());
    handEvaluators.add(new StraightEvaluator());
    handEvaluators.add(new FlushEvaluator());
    handEvaluators.add(new FullHouseEvaluator());
  }

  public Hand evaluate(Card[] cards) {
    int size = handEvaluators.size();
    for (int a = size - 1; a >= 0; a--) {
      Hand hand = handEvaluators.get(a).evaluate(cards);
      if (hand != null)
        return hand;
    }
    return null;
  }

}