package com.mr.texasholdem.evaluator;

import static com.mr.texasholdem.card.Card.findAce;
import static com.mr.texasholdem.hand.Straight.isValidStraight;

import java.util.*;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.Straight;

public class StraightEvaluator extends AbstractHandEvaluator {

  @Override
  public Hand evaluate(Card[] cards) {
    SortedSet<Card> uniqueSortedCards = getSortedCards(asList(cards));
    if (uniqueSortedCards.size() < 5)
      return null;
    Card[] uniqueSortedCardsArray = uniqueSortedCards.toArray(new Card[] {});
    Card ace = findAce(uniqueSortedCardsArray);
    Straight straight = findStraight(uniqueSortedCardsArray);
    if (straight == null && ace != null) {
      uniqueSortedCardsArray = transfigurate(uniqueSortedCards, ace);
      straight = findStraight(uniqueSortedCardsArray);
    }
    return straight;
  }

  @Override
  public int priority() {
    return Hand.STRAIGHT_VALUE;
  }

  private SortedSet<Card> getSortedCards(List<Card> cards) {
    SortedSet<Card> sortedCards = new TreeSet<>(new CardRankComparator());
    sortedCards.addAll(cards);
    return sortedCards;
  }

  private Card[] transfigurate(SortedSet<Card> sortedCards, Card ace) {
    Card one = ace.clone().transfiguration();
    sortedCards.remove(ace);
    sortedCards.add(one);
    return sortedCards.toArray(new Card[] {});
  }

  private Straight findStraight(Card[] sortedCards) {
    List<Straight> straits = new ArrayList<>();
    int shiftSize = sortedCards.length - 4;
    for (int a = 0; a < shiftSize; a++) {
      Card[] fiveCards = Arrays.copyOfRange(sortedCards, a, a + 5);
      if (isValidStraight(fiveCards)) {
        straits.add(new Straight(fiveCards));
      }
    }
    return straits.isEmpty() ? null : Collections.max(straits);
  }

}
