package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.FourOfAKind;
import com.mr.texasholdem.hand.Hand;

public class FourOfAKindEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    return findFourOfAKind(asList(cards));
  }

  @Override
  public int priority() {
    return Hand.FOUR_OF_A_KIND_VALUE;
  }

  protected FourOfAKind findFourOfAKind(List<Card> cards) {
    List<FourOfAKind> fourOfAKinds = findFourOfAKinds(cards.toArray(new Card[0]), cards, new ArrayList<>());
    return !fourOfAKinds.isEmpty() ? Collections.max(fourOfAKinds) : null;
  }

  private List<FourOfAKind> findFourOfAKinds(Card[] sevenCards, List<Card> cards, List<FourOfAKind> fourOfAKinds) {
    int size = cards.size();
    Card card1 = cards.get(0);
    Card card2 = null;
    Card card3 = null;
    for (int a = 1; a < size; a++) {
      Card card = cards.get(a);
      if (card1.equalsByRank(card)) {
        if (card2 == null) {
          card2 = card;
        }
        else
          if (card3 == null) {
            card3 = card;
          }
          else {
            cards.remove(card2);
            cards.remove(card3);
            cards.remove(card);
            Card[] kickers = findKickers(sevenCards, card1, card2, card3, card);
            fourOfAKinds.add(new FourOfAKind(card1, card2, card3, card, kickers));
            break;
          }
      }
    }
    cards.remove(card1);
    if (cards.size() > 3) {
      return findFourOfAKinds(sevenCards, cards, fourOfAKinds);
    }
    return fourOfAKinds;
  }

}
