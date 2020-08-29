package com.mr.texasholdem;

import static com.mr.texasholdem.card.Card.findAce;
import static com.mr.texasholdem.hand.Straight.isValidStraight;

import java.util.*;
import java.util.stream.Collectors;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.card.CardRankComparator;
import com.mr.texasholdem.card.Suit;
import com.mr.texasholdem.hand.*;

public class Evaluator {

  private Hand hand;

  public Hand evaluate(Card[] cards) {

    boolean found = evaluateFlush(cards);
    if (found)
      return hand;
    found = evaluateStraight(getSortedCards(asList(cards)));
    if (found)
      return hand;
    found = evaluateThreeOfAKinds(asList(cards));
    if (found)
      return hand;
    found = evaluateTwoPairs(asList(cards));
    if (found)
      return hand;
    found = evaluatePair(asList(cards));
    if (found)
      return hand;
    evaluateHighCard(asList(cards));
    return hand;
  }

  private List<Card> asList(Card[] cards){
    return Arrays.stream(cards).collect(Collectors.toList());
  }
  private SortedSet<Card> getSortedCards(List<Card> cards) {
    SortedSet<Card> sortedCards = new TreeSet<>(new CardRankComparator());
    sortedCards.addAll(cards);
    return sortedCards;
  }

  private void evaluateHighCard(List<Card> cards) {
    hand = new HighCard(Collections.max(cards, new CardRankComparator()));
  }

  private boolean evaluateTwoPairs(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    int size = pairs.size();
    if (size < 2) {
      return false;
    }
    Collections.sort(pairs);
    hand = new TwoPairs(pairs.get(size - 1), pairs.get(size - 2));
    return true;
  }

  private boolean evaluatePair(List<Card> cards) {
    List<Pair> pairs = evaluatePairs(cards, new ArrayList<>());
    Pair pair = !pairs.isEmpty() ? Collections.max(pairs) : null;
    return (hand = pair) != null;
  }

  private List<Pair> evaluatePairs(List<Card> cards, List<Pair> pairs) {
    int size = cards.size();
    Card card1 = cards.get(0);
    for (int a = 1; a < size; a++) {
      Card card2 = cards.get(a);
      if (card1.equalsByRank(card2)) {
        pairs.add(new Pair(card1, card2));
        cards.remove(card2);
        break;
      }
    }
    cards.remove(card1);
    if (cards.size() > 1) {
      return evaluatePairs(cards, pairs);
    }
    return pairs;
  }

  private boolean evaluateThreeOfAKinds(List<Card> cards) {
    List<ThreeOfAKind> threeOfAKinds = evaluateThreeOfAKinds(cards, new ArrayList<>());
    Pair pair = !threeOfAKinds.isEmpty() ? Collections.max(threeOfAKinds) : null;
    return (hand = pair) != null;
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
    if (cards.size() > 1) {
      return evaluateThreeOfAKinds(cards, threeOfAKinds);
    }
    return threeOfAKinds;
  }

  private boolean evaluateStraight(SortedSet<Card> sortedCards) {
    if (sortedCards.size() < 5)
      return false;
    Card[] cards = sortedCards.toArray(new Card[] {});
    Card ace = findAce(cards);
    hand = findStraight(cards);
    if (hand == null && ace != null) {
      cards = transfigurate(sortedCards, ace);
      hand = findStraight(cards);
    }
    return hand != null;
  }

  private Card[] transfigurate(SortedSet<Card> sortedCards, Card ace) {
    Card one = ace.clone().transfiguration();
    sortedCards.remove(ace);
    sortedCards.add(one);
    return sortedCards.toArray(new Card[] {});
  }

  private Straight findStraight(Card[] cards) {
    List<Straight> straits = new ArrayList<>();
    int shiftSize = cards.length - 4;
    for (int a = 0; a < shiftSize; a++) {
      Card[] fiveCards = Arrays.copyOfRange(cards, a, a + 5);
      if (isValidStraight(fiveCards)) {
        straits.add(new Straight(fiveCards));
      }
    }
    return straits.isEmpty() ? null : Collections.max(straits);
  }

  private boolean evaluateFlush(Card[] cards) {
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
        hand = new Flush(suitedCards.toArray(new Card[]{}));
        return true;
      }
    }
    return false;
  }
}
