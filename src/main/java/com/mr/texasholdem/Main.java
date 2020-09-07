package com.mr.texasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import com.mr.texasholdem.card.Card;
import com.mr.texasholdem.hand.HandPriority;

public class Main {

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String[] tokens = getTokens(reader);
      if (tokens.length == 0)
        return;
      runGame(tokens);
    }
  }

  private static void runGame(String[] tokens) {
    try {
      CommunityCards communityCards = new CommunityCards(tokens[0]);
      List<Card> holeCards = new ArrayList<>();
      List<SevenCard> sevenCards = new ArrayList<>();
      for (int a = 1; a < tokens.length; a++) {
        SevenCard sevenCard = new SevenCard(communityCards, tokens[a]);
        validateHoleCards(holeCards, sevenCard);
        sevenCards.add(sevenCard);
      }
      printResult(sevenCards);
    }
    catch (WrongInputParameterException e) {
      System.out.println("Wrong input parameters: " + e.getMessage());
    }
  }

  private static void validateHoleCards(final List<Card> holeCards, SevenCard sevenCard) throws WrongInputParameterException {
    Card holeCard1 = sevenCard.getHoleCard1();
    Card holeCard2 = sevenCard.getHoleCard2();
    Card.validateCard(holeCards, holeCard1);
    Card.validateCard(holeCards, holeCard2);
    holeCards.add(holeCard1);
    holeCards.add(holeCard2);
  }

  private static void printResult(List<SevenCard> sevenCards) {
    SortedMap<HandPriority, List<String>> holeCardsByPriority = new TreeMap<>();
    for (SevenCard sevenCard : sevenCards) {
      holeCardsByPriority.putIfAbsent(sevenCard.getHand().getHandPriority(), new ArrayList<>());
      holeCardsByPriority.get(sevenCard.getHand().getHandPriority()).add(sevenCard.getHoleCardCodes());
    }

    boolean firstTime = true;
    for (List<String> holeCards : holeCardsByPriority.values()) {
      if (!firstTime) {
        System.out.print(' ');
      } else {
        firstTime = false;
      }
      System.out.print(holeCards.stream().sorted().reduce((s1, s2) -> s1 + "=" + s2).orElse(""));
    }
    System.out.println();
  }

  private static String[] getTokens(BufferedReader reader) {
    String inputString;
    String[] tokens;
    while (true) {
      try {
        inputString = reader.readLine();
      }
      catch (IOException e) {
        e.printStackTrace(System.out);
        continue;
      }
      if (inputString == null) {
        return new String[] {};
      }
      tokens = inputString.split("\\s+");
      if (tokens.length < 2) {
        System.out.println("Please enter community cards and at least one hole card pair");
      }
      else {
        return tokens;
      }
    }
  }
}
