public class Mexico {
  public static void main(String[] args) {
    double valueBuyIn = Double.valueOf(args[0]);
    double baseBet = Double.valueOf(args[1]);
    playMexico(valueBuyIn, baseBet);
  }
  
  //method to simulate the roll of one dice 
  public static int diceRoll() {
    //int dice = a dice roll between 1-6
    int dice = (int) (Math.random()*6 +1);
    return dice;
  }
  
  //method to get the score of two dice rolls
  public static int getScore(int diceOne, int diceTwo) {
    //the first dice roll is diceOne and the second is diceTwo
    //if the first dice roll is equal of greater than the second dice roll
    if ((diceOne==diceTwo) || (diceOne>diceTwo)) {
      int score = diceOne*10+diceTwo;
      return score;
    }
    //if the second dice roll is greater
    else {
      int score = diceTwo*10+diceOne;
      return score;
    }
  }
  
  //method that plays one round with the input of the player's name
  public static int playOneRound(String playerName) {
    int diceOne = diceRoll();
    int diceTwo = diceRoll();
    //using getScore to find the totalScore
    int totalScore = getScore(diceOne, diceTwo);
    System.out.println(playerName + " rolled: "+ diceOne +" " + diceTwo);
    if (totalScore == 21) {
      System.out.println(playerName + "'s score is: Mexico!" );
    }
    else {
      System.out.println(playerName + "'s score is: " + totalScore);
    }
    return totalScore;
  }
  
  //method that gets winner using the scores from playOneRound
  public static String getWinner(int player1Score, int player2Score) {
    //if statements to determine the winner of the round or whether it's a tie 
    if (((player1Score%11==0) && (player2Score!=21) && (player2Score!=player1Score)) || ((player1Score==21) && (player2Score!=player1Score))) {
      return "Guilia";
    }
    if ((player1Score>player2Score) && (player2Score!=21) && (player2Score%11!=0)) {
      return "Guilia";
    }
    if (((player2Score%11==0) && (player1Score!=21) && (player2Score!=player1Score)) || ((player2Score==21) && (player2Score!=player1Score))) {
      return "David";
    }
    if ((player2Score>player1Score) && (player1Score!=21) && (player1Score%11!=0)) {
      return "David";
    }
    else {
      return "tie";
    }
  }
  
  //method to confirm that there are sufficient funds to play game
  public static boolean canPlay(double valueBuyIn, double baseBet) {
    if ((valueBuyIn>= baseBet) && (valueBuyIn%baseBet== 0) && (valueBuyIn>0)) {
      return true;
    }
    else {
      return false;
    }
  }
  
  //method that puts all the methods together in order to simulate a full game 
  public static void playMexico(double valueBuyIn, double baseBet) {
    //determining if game can be played using if statements
    if (canPlay(valueBuyIn, baseBet)==false) {
      System.out.println("Insufficient funds. The game cannot be played.");
    }
    
    else {
      //variable for round number
      int roundNumber = 1;
      //storing the buy in value in a variable for each player 
      double player1Money = valueBuyIn;
      double player2Money = valueBuyIn;
      //loop to play the game 
      while ((player1Money>0) && (player2Money>0)) {
        System.out.println("Round " + roundNumber);
        //storing the scores of each player from one round in variables
        int player1Score = playOneRound("Guilia");
        int player2Score = playOneRound("David");
        //storing the winner in a variable
        String winner = getWinner(player1Score, player2Score);
        //using if statements to determine who won the round
        if (winner.equals("tie")) {
          System.out.println("It's a tie! Roll again");
        }
        else if (winner.equals("Guilia")) {
          System.out.println("Guilia wins this round");
          player2Money = player2Money- baseBet;
        }
        else if (winner.equals("David")) {
          System.out.println("David wins this round");
          player1Money = player1Money-baseBet;
        }
        roundNumber++;
      }
      
      //if statements to end the game 
      if (player1Money ==0) {
        System.out.println("David won the game!");
      }
      else if (player2Money ==0) {
        System.out.println("Guilia won the game!");
      }    
    }
  }
}
    