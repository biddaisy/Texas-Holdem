package com.mr.texasholdem.evaluator;

import java.util.SortedSet;
import java.util.TreeSet;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;

public class Evaluator {

  private final SortedSet<HandEvaluator> handEvaluators = new TreeSet<>((o1, o2) -> o2.priority() - o1.priority());

  public Evaluator() {
    handEvaluators.add(new HighCardEvaluator());
    handEvaluators.add(new PairEvaluator());
    handEvaluators.add(new TwoPairsEvaluator());
    handEvaluators.add(new ThreeOfAKindEvaluator());
    handEvaluators.add(new StraightEvaluator());
    handEvaluators.add(new FlushEvaluator());
    handEvaluators.add(new FullHouseEvaluator());
    handEvaluators.add(new FourOfAKindEvaluator());
    handEvaluators.add(new StraightFlushEvaluator());
    handEvaluators.add(new RoyalFlushEvaluator());
  }

  public Hand evaluate(Card[] cards) {
    for (HandEvaluator handEvaluator : handEvaluators) {
      Hand hand = handEvaluator.evaluate(cards);
      if (hand != null)
        return hand;
    }
    return null;
  }

}
