package com.mr.texasholdem;

import com.mr.texasholdem.hand.Hand;
import com.mr.texasholdem.hand.HandPriority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

  public static void main(String[] args) {
    System.out.println("Please enter 5 community and then hole card pairs");
    while (true) {
      String[] tokens = getTokens();
      if (tokens.length == 0)
        return;
      try {
        CommunityCards communityCards = new CommunityCards(tokens[0]);
        List<SevenCard> sevenCards = new ArrayList<>();
        for (int a = 1; a < tokens.length; a++) {
          SevenCard sevenCard = new SevenCard(communityCards, tokens[a]);
          sevenCards.add(sevenCard);
        }
        Collections.sort(sevenCards);
        printResult(sevenCards);
      }
      catch (WrongInputParameterException e) {
        System.out.println("Wrong input parameters: " + e.getMessage());
      }

    }

  }

  private static void printResult(List<SevenCard> sevenCards) {
    SortedMap<HandPriority, List<String>> holeCardsByPriority = new TreeMap<>();
    for (SevenCard sevenCard : sevenCards){
      holeCardsByPriority.putIfAbsent(sevenCard.getHand().getHandPriority(), new ArrayList<>());
      holeCardsByPriority.get(sevenCard.getHand().getHandPriority()).add(sevenCard.getHoleCardCodes());
    }

    for (List<String> holeCards : holeCardsByPriority.values()){
      System.out.print(holeCards.stream().sorted().reduce((s1,s2)->s1 + "=" + s2).orElse("") + " ");
    }
    System.out.println();
  }

  private static String[] getTokens() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
        System.out.println("Please enter at least one hole card pair");
      }
      else {
        return tokens;
      }
    }

  }
}
