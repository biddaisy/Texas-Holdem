package com.mr.texasholdem.evaluator;

import java.util.Arrays;
import java.util.Collections;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.HighCard;

public class HighCardEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    return new HighCard(Collections.max(Arrays.asList(cards), new CardRankComparator()));
  }

  @Override
  public int priority() {
    return Hand.HIGH_CARD_VALUE;
  }
}
