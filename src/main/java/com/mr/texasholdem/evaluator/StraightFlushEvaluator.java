package com.mr.texasholdem.evaluator;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Straight;
import com.mr.texasholdem.hand.StraightFlush;

public class StraightFlushEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    Straight straight = (Straight) new StraightEvaluator().evaluate(cards);
    return straight != null && straight.isFlush() ? new StraightFlush(straight.getCards()) : null;
  }

  @Override
  public int priority() {
    return Hand.STRAIGHT_FLUSH_VALUE;
  }
}
