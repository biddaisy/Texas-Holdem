package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.FullHouse;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Pair;
import com.mr.texasholdem.hand.ThreeOfAKind;

public class FullHouseEvaluator extends AbstractHandEvaluator {
  @Override
  public Hand evaluate(Card[] cards) {
    ThreeOfAKind threeOfAKind = new ThreeOfAKindEvaluator().findThreeOfAKind(asList(cards));
    if (threeOfAKind == null)
      return null;
    List<Card> remainingCards = asList(cards);
    remainingCards.remove(threeOfAKind.getCard1());
    remainingCards.remove(threeOfAKind.getCard2());
    remainingCards.remove(threeOfAKind.getCard3());
    Pair pair = new PairEvaluator().findPair(remainingCards);
    if (pair == null)
      return null;
    return new FullHouse(threeOfAKind, pair);
  }

  @Override
  public int priority() {
    return Hand.FULL_HOUSE_VALUE;
  }
}
