package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.ThreeOfAKind;

public class ThreeOfAKindEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    return findThreeOfAKind(asList(cards));
  }

  @Override
  public int priority() {
    return Hand.THREE_OF_A_KIND_VALUE;
  }

  protected ThreeOfAKind findThreeOfAKind(List<Card> cards) {
    List<ThreeOfAKind> threeOfAKinds = evaluateThreeOfAKinds(cards, new ArrayList<>());
    return !threeOfAKinds.isEmpty() ? Collections.max(threeOfAKinds) : null;
  }

  private List<ThreeOfAKind> evaluateThreeOfAKinds(List<Card> cards, List<ThreeOfAKind> threeOfAKinds) {
    int size = cards.size();
    Card card1 = cards.get(0);
    Card card2 = null;
    for (int a = 1; a < size; a++) {
      Card card = cards.get(a);
      if (card1.equalsByRank(card)) {
        if (card2 == null) {
          card2 = card;
        }
        else {
          cards.remove(card2);
          cards.remove(card);
          threeOfAKinds.add(new ThreeOfAKind(card1, card2, card));
          break;
        }
      }
    }
    cards.remove(card1);
    if (cards.size() > 2) {
      return evaluateThreeOfAKinds(cards, threeOfAKinds);
    }
    return threeOfAKinds;
  }

}
