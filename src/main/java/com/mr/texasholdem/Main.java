package com.mr.texasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

        String inputString = null;
        try {
            inputString = reader.readLine();
        }
        catch (IOException e) {
            System.out.println("input error: ");
            e.printStackTrace(System.out);
        }
        String[] tokens = inputString.split("\\s+");
        if (tokens == null || tokens.length < 2){
            System.out.println("wrong input data");
            return;
        }
        CommunityCards communityCards = new CommunityCards(tokens[0]);
        List<SevenCard> sevenCards = new ArrayList<>();
        for (int a = 1; a < tokens.length; a++){
            SevenCard sevenCard = new SevenCard(communityCards, tokens[a]);
            sevenCards.add(sevenCard);
        }
        Collections.sort(sevenCards);
        System.out.print(communityCards.toString());
        System.out.print(sevenCards.stream().reduce("", (s, sc)->s.concat(" " + sc.getHoleCardCodes()), String::concat));
    }
}
