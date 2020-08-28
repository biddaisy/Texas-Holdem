package com.mr.texasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    while (true) {
      String[] tokens = getTokens();
      if (tokens.length == 0)
        return;
      CommunityCards communityCards = new CommunityCards(tokens[0]);
      List<SevenCard> sevenCards = new ArrayList<>();
      for (int a = 1; a < tokens.length; a++) {
        SevenCard sevenCard = new SevenCard(communityCards, tokens[a]);
        sevenCards.add(sevenCard);
      }
      Collections.sort(sevenCards);
      printResult(communityCards, sevenCards);
    }
  }

  private static void printResult(CommunityCards communityCards, List<SevenCard> sevenCards) {
    System.out.print(communityCards + " ");
    SevenCard prevSevenCard = sevenCards.get(0);
    System.out.print(prevSevenCard.getHoleCardCodes());
    for (int a = 1; a < sevenCards.size(); a++){
      SevenCard sevenCard = sevenCards.get(a);
      if (prevSevenCard.equals(sevenCard)){
        System.out.print("=");
      } else {
        System.out.print(" ");
      }
      System.out.print(sevenCard.getHoleCardCodes());
      prevSevenCard = sevenCard;
    }
    System.out.println();
  }

  private static String[] getTokens() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String inputString = null;
    String[] tokens;
    while (true) {
      try {
        inputString = reader.readLine();
      }
      catch (IOException e) {
        System.out.println("input error: ");
        e.printStackTrace(System.out);
      }
      if (inputString == null) {
        return new String[] {};
      }
      tokens = inputString.split("\\s+");
      if (tokens.length < 2) {
        System.out.println("wrong input data");
      }
      else {
        return tokens;
      }
    }

  }
}
