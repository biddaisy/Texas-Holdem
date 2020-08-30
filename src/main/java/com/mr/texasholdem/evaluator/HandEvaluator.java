package com.mr.texasholdem.evaluator;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;

public interface HandEvaluator extends Comparable<HandEvaluator>{

  Hand evaluate(Card[] cards);

  int priority();

}
