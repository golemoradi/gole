import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class TicTacToe {
  public static void main(String[] args) {
    play();
  }
                                          
  //a method that populates the array with blank spaces
  //takes int n for dimensions 
  public static char[][] createBoard(int n) {
    char[][] board = new char[n][n];
    for (int i=0; i<n; i++) {
      for (int j=0; j<n; j++) {
        board[i][j] = ' ';
      }
    }
    return board;
  }
  
  //method that displays a 2D array on a board
  public static void displayBoard(char[][] a) {
    //size represents the size of the boarders of the board
    int size = a.length*2+1;
    for (int i=0; i<size; i++) {
      if (i%2==0) {
        for (int j=0; j<size; j++) {
          if (j%2==0) {
            System.out.print('+');
          }
          else {
            System.out.print('-');
          }
        }
      }
      
      else {
        for (int j=0; j<size; j++) {
          if (j%2==0) {
            System.out.print('|');
          }
          else {
            //adding the array to the board
            System.out.print(a[(i-1)/2][(j-1)/2]);
          }
        }
      }
      System.out.println();
    }
  }
  
  //a method that allows you to write on the board (change values of the array elements)
  public static void writeOnBoard(char[][] board, char c, int row, int column) {
    if ((row<0 || row>board.length-1) || (column<0 || column>board.length-1)) {
      throw new IllegalArgumentException("Cell input cannot be outside of the board");
    }
    else if (board[row][column]!=' ') {
      throw new IllegalArgumentException("Cell is not empty");
    }
    else {
      board[row][column] = c;
    }
  }
  
  //method that gets the user's move using scanner
  public static void getUserMove(char[][] board) {
    Scanner read = new Scanner(System.in);
    int row = read.nextInt();
    int column = read.nextInt();
    //making sure the input is valid
    while  (((row<0 || row>board.length-1) || (column<0 || column>board.length-1)) || (board[row][column]!=' ')) {
      System.out.println("Please enter a new move");
      row = read.nextInt();
      column = read.nextInt();
    }
    writeOnBoard(board,'x', row, column);
  }
  
  //method that checks for obvious moves for AI
  public static boolean checkForObviousMove(char[][] board) {
    //using if statements to check
    //first checking if there are any obvious wins for AI by calling helper methods
    if (canWinRows(board, 'o') == true) {
      return true;
    }
    else if (canWinColumns(board,'o') == true) {
      return true;
    }
    else if (canWinDiagonals(board,'o') == true) {
      return true;
    }
    //next checking if there are any possible wins for User that AI can block by calling helper methods
    else if (canWinRows(board,'x') == true) {
      return true;
    }
    else if (canWinColumns(board,'x') == true) {
      return true;
    }
    else if (canWinDiagonals(board,'x') == true) {
      return true;
    }
    return false;
  }
  
  //method that gets the computer's move
  public static void getAIMove(char[][] board) {
    Random gen = new Random();
    //if no obvious move, then random move is generated
    if (checkForObviousMove(board) == false) {
      int randomMoveX = gen.nextInt(board.length);
      int randomMoveY = gen.nextInt(board.length);
      //making sure the random move is valid
      while (board[randomMoveX][randomMoveY]!=' ') {
        randomMoveX = gen.nextInt(board.length);
        randomMoveY = gen.nextInt(board.length);
      }
      writeOnBoard(board,'o',randomMoveX,randomMoveY);
    }
  }
  
  //a method that checks for the winner 
  public static char checkForWinner(char[][] board) {
    //checking if there was a row win for AI or User
    for (int i=0; i<board.length; i++) {
      // rowAI or rowUser represents the amount of o or x respectively in the row 
      int rowAI = 0;
      int rowUser=0;
      for (int j=0; j<board.length; j++) {
        //if there is an x or o in the row the row(AI or User) count will increase
        if (board[i][j] == 'o') {
          rowAI++;
        }
        if (board[i][j] == 'x') {
          rowUser++;
        }
        //wins
        if (rowAI == board.length) {
          return 'o';
        }
        if (rowUser == board.length)  {
          return 'x';
        }
      }
    }
  
      
    //checking if there was a column win for AI or User
    for (int i=0; i<board.length; i++) {
      // columnAI or columnUser represents the amount of o or x respectively in the column
      int columnAI = 0;
      int columnUser = 0;
      for (int j=0; j<board.length; j++) {
      //if there is an o or x in the column the column(AI or User) count will increase 
        if (board[j][i] == 'o') {
          columnAI++;
        }
        if (board[j][i] == 'x') {
          columnUser++;
        }
        //wins
        if (columnAI == board.length) {
          return 'o';
        }
        if (columnUser == board.length) {
          return 'x';
        }
      }
    }
      
    // checking if there was a diagnol win for AI or user
    // first checking diagonally to the left: \
    int leftDiagonalAI = 0;
    int leftDiagonalUser = 0;
      for (int i=0; i<board.length; i++) {
        //checking how many x or o there are diagonally for AI and user 
        if (board[i][i] == 'o') {
          leftDiagonalAI++;
        }
        if (board[i][i] == 'x') {
          leftDiagonalUser++;
        }
        //wins
        if (leftDiagonalAI == board.length) {
          return 'o';
        }
        if (leftDiagonalUser == board.length) {
          return 'x';
        }
      }
    // Second, checking diagonally to the right: /
    // rightDiagonal is number of x or o diagonally (to the right), assumed to be 0 for User and AI
    int rightDiagonalAI = 0;
    int rightDiagonalUser = 0;
      for (int i=0; i<board.length; i++) {
        //checking for x or o diagonally right /
        if (board[i][board.length-(1+i)] == 'o') {
          rightDiagonalAI++;
        }
        if (board[i][board.length-(1+i)] == 'x') {
          rightDiagonalUser++;
        }
        //wins
        if (rightDiagonalAI==board.length) {
          return 'o';
        }
        if (rightDiagonalUser==board.length) {
          return 'x';
        }
      }
      
      return ' ';
  }
  
  //method that simulates the tic tac toe game between AI and User
  public static void play() {
    Random gen = new Random();
    Scanner read = new Scanner(System.in);
    //starting off the game
    System.out.println("Please enter your name:");
    String name = read.nextLine();
    System.out.println("Welcome, " + name + "! Are you ready to play?");
    System.out.println("Please choose the dimension of your board:");
    //checking if dimension is valid
    boolean isInteger = read.hasNextInt();
    int boardDimension;
    String check = " ";
    if (isInteger==true) {
      boardDimension = read.nextInt();
    }
    else {
      while (isInteger==false) {
        isInteger = read.hasNextInt();
        check = read.nextLine();
        if (isInteger==true) {
          break;
        }
        System.out.println("Please input an integer to choose the dimension of your board:");
      }
      boardDimension = Integer.parseInt(check);
    }
    
    //coin toss
    int tossResult = gen.nextInt(2);
    //if tossResult=0, AI starts and if tossResult=1, User starts
    int AIPlayOrder = 0;
    int UserPlayOrder = 0;
    if (tossResult == 0) {
      AIPlayOrder = 0;
      UserPlayOrder = 1;
      System.out.println("The result of the coin toss is: 0 \nThe AI has the first move");
    }
    else if (tossResult == 1) {
      AIPlayOrder = 1;
      UserPlayOrder = 0;
      System.out.println("The result of the coin toss is: 1\n" + name + " has the first move");
    }
    
    
    ///loop to alternate player's turns
    char[][] board = createBoard(boardDimension);
    for (int i=0; i<boardDimension*boardDimension; i++) {
      if (i==AIPlayOrder) {
        System.out.println("The AI has made its move:");
        getAIMove(board);
        displayBoard(board);
        checkForWinner(board);
        if (checkForWinner(board) == 'o') {
          System.out.println("GAME OVER!\nYou lost");
          break;
        }
        AIPlayOrder = AIPlayOrder + 2;
      }
      if (i==UserPlayOrder) {
        System.out.println("Please enter your move:");
        getUserMove(board);
        displayBoard(board);
        checkForWinner(board);
        if (checkForWinner(board) == 'x') {
          System.out.println("Congratulations! You won");
          break;
        }
        UserPlayOrder = UserPlayOrder + 2;
      }
    }
    if (checkForWinner(board) ==' ') {
      System.out.println("Its a tie!");
    }
  }
  
  //HELPER METHODS:
  //helper method that checks if there is a potential win in the rows for AI or player
  public static boolean canWinRows(char[][] board, char c) {
      //checking rows
      //row is number of x or o in the row; assumed to be 0 
      int row;
      for (int i=0; i<board.length; i++) {
        row = 0;
        for (int j=0; j<board.length; j++) {
          //if there is an o in the row the row count will increase
          if (board[i][j] == c) {
            row++;
          }
          //potential obvious move 
          if (row == board.length-1) {
            for (int k=0; k<board.length; k++) {
              //checking what c is 
              //if c===o, then checking for AI win
              if (c=='o') {
                if (board[i][k] == ' ') {
                  writeOnBoard(board,'o', i,k);
                  //System.out.println("there is an obvious move");
                  return true;
                }
              }
              // if c=='x', then checking for potential user win to block
              else if (c=='x') {
                if (board[i][k] == ' ') {
                  writeOnBoard(board,'o', i,k);
                  //System.out.println("there is an obvious move");
                  return true;
                }
              }
            }
          }
        }
      }
     return false;
    }
    
   //helper method that checks if there is a potential win in the columns for AI or player
   public static boolean canWinColumns(char[][] board, char c) {
      //checking columns
      // column is number of x or o in a column, assumed to be 0
      int column;
      for (int i=0; i<board.length; i++) {
        column = 0;
        for (int j=0; j<board.length; j++) {
          //if there is an o in the column the column count will increase 
          if (board[j][i] == c) {
            column++;
          }
          //potential obvious move
          if (column == board.length-1) {
            for (int k=0; k<board.length; k++) {
              //checking what c is 
              //if c===o, then checking for AI win
              if (c=='o') {
                if (board[k][i] == ' ') {
                  writeOnBoard(board,'o',k,i);
                  //System.out.println("there is an obvious move");
                  return true;
                }
              }
              // if c=='x', then checking for potential user win to block
              else if (c=='x') {
                if (board[k][i] == ' ') {
                  writeOnBoard(board,'o', k,i);
                  //System.out.println("there is an obvious move");
                  return true;
                }
              }
            }
          }
        }
      }
      return false;
    }
    
    //helper method that checks if there is a potential win in the rows for AI or player
    public static boolean canWinDiagonals(char[][] board, char c) {
      // first, checking diagonally to the left: \
      // leftDiagonal is number of x or o diagonally (to the left), assumed to be 0
      int leftDiagonal = 0;
      for (int i=0; i<board.length; i++) {
        //checking how many x or o there are diagonally
        if (board[i][i] == c) {
          leftDiagonal++;
        }
        //checking potential obvious moves
        if (leftDiagonal == board.length-1) {
          for (int j=0; j<board.length; j++) {
            //checking what c is 
            //if c===o, then checking for AI win
            if (c=='o') {
              if (board[j][j] == ' ') {
                writeOnBoard(board,'o',j,j);
                //System.out.println("there is an obvious move");
                return true;
              }
            }
            // if c=='x', then checking for potential user win to block
            else if (c=='x') {
              if (board[j][j] == ' ') {
                writeOnBoard(board,'o',j,j);
                //System.out.println("there is an obvious move");
                return true;
              }
            }
          }
        }
      }
      
      // Second, checking diagonally to the right: /
      // rightDiagonal is number of x or o diagonally (to the right), assumed to be 0
      int rightDiagonal = 0;
      for (int i=0; i<board.length; i++) {
        //checking for x or o diagonally right /
        if (board[i][board.length-(1+i)] == c) {
          rightDiagonal++;
        }
        //checking potential obvious moves
        if (rightDiagonal==board.length-1) {
          for (int j=0; j<board.length; j++) {
            //checking what c is 
            //if c===o, then checking for AI win
            if (c=='o') {
              if (board[j][board.length-(1+j)] == ' ') {
                writeOnBoard(board,'o',j,(board.length-(1+j)));
                return true;
              }
            }
            // if c=='x', then checking for potential user win to block
            else if (c=='x') {
              if (board[j][board.length-(1+j)] == ' ') {
                writeOnBoard(board,'o',j,(board.length-(1+j)));
                return true;
              }
            }
          }
        }
      }
      return false;
    }
}
    