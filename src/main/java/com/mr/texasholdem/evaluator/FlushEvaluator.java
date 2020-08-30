package com.mr.texasholdem.evaluator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.Suit;
import com.mr.texasholdem.hand.Flush;
import com.mr.texasholdem.hand.Hand;

public class FlushEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    Map<Suit, List<Card>> cardsBySuit = new EnumMap<>(Suit.class);
    cardsBySuit.put(Suit.CLUBS, new ArrayList<>());
    cardsBySuit.put(Suit.DIAMONDS, new ArrayList<>());
    cardsBySuit.put(Suit.HEARTS, new ArrayList<>());
    cardsBySuit.put(Suit.SPADES, new ArrayList<>());
    for (Card card : cards) {
      cardsBySuit.get(card.getSuit()).add(card);
    }
    for (List<Card> suitedCards : cardsBySuit.values()) {
      if (suitedCards.size() == 5) {
        return new Flush(suitedCards.toArray(new Card[] {}));
      }
    }
    return null;
  }

  @Override
  public int priority() {
    return Hand.FLUSH_VALUE;
  }

}
