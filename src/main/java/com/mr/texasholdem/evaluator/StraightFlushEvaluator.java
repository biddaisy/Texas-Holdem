package com.mr.texasholdem.evaluator;

import java.util.List;
import java.util.Map;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.Suit;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Straight;
import com.mr.texasholdem.hand.StraightFlush;

public class StraightFlushEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    Map<Suit, List<Card>> cardsBySuit = new FlushEvaluator().getRankSortedCardsBySuit(cards);
    for (List<Card> suitedCards : cardsBySuit.values()) {
      if (suitedCards.size() >= 5) {
        Straight straight = (Straight) new StraightEvaluator().evaluate(suitedCards.toArray(new Card[] {}));
        return straight != null && straight.isFlush() ? new StraightFlush(straight.getCards()) : null;
      }
    }
    return null;
  }

  @Override
  public int priority() {
    return Hand.STRAIGHT_FLUSH_VALUE;
  }
}
