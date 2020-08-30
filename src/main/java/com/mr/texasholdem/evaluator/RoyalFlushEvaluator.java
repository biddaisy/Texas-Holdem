package com.mr.texasholdem.evaluator;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.RoyalFlush;
import com.mr.texasholdem.hand.StraightFlush;

public class RoyalFlushEvaluator  extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    StraightFlush straight = (StraightFlush) new StraightFlushEvaluator().evaluate(cards);
    return straight != null && straight.isRoyal() ? new RoyalFlush(straight.getCards()) : null;
  }

  @Override
  public int priority() {
    return Hand.ROYAL_FLUSH_VALUE;
  }
}
