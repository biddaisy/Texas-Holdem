# Texas-Holdem

An application implements an algorithm for comparing the strength of Texas Hold'em Hands. A value of
a Texas Hold'em Hand is the best possible value out of all possible subsets of 5 cards from the 7
cards which are formed by 5 board cards and 2 hand cards.

1) Download the last version of Oracle JDK 1.8 from before the licence change (version 1.8_202) and install in e.g. /usr/local/jdk1.8.0_202/
2) Clone the repository
3) Go to the project folder, e.g. Texas-Holdem 
4) Run <java bin>/javac -sourcepath ./src/main/java -d ./bin ./src/main/java/com/mr/texasholdem/Main.java
5) Run <java bin>/java -cp ./bin com.mr.texasholdem.Main
6) You will be prompted to input data<br>
For example:<br>
Input:<br>
 4cKs4h8s7s Ad4s Ac4d As9s KhKd 5d6d<br>
Output:<br>
 Ac4d=Ad4s 5d6d As9s KhKd