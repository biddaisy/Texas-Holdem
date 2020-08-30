package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.FullHouse;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Pair;
import com.mr.texasholdem.hand.FourOfAKind;

public class FullHouseEvaluator extends AbstractHandEvaluator {
  @Override
  public Hand evaluate(Card[] cards) {
    FourOfAKind FourOfAKind = new FourOfAKindEvaluator().findFourOfAKind(asList(cards));
    if (FourOfAKind == null)
      return null;
    List<Card> remainingCards = asList(cards);
    remainingCards.remove(FourOfAKind.getCard1());
    remainingCards.remove(FourOfAKind.getCard2());
    remainingCards.remove(FourOfAKind.getCard3());
    Pair pair = new PairEvaluator().findPair(remainingCards);
    if (pair == null)
      return null;
    return new FullHouse(FourOfAKind, pair);
  }

  @Override
  public int priority() {
    return Hand.FULL_HOUSE_VALUE;
  }
}
