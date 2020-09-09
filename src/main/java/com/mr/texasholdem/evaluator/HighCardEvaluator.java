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
    Card highCard = Collections.max(Arrays.asList(cards), new CardRankComparator());
    Card[] kickers = findKickers(cards, highCard);
    return new HighCard(highCard, kickers);
  }

  @Override
  public int priority() {
    return Hand.HIGH_CARD_VALUE;
  }
}
