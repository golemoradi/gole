import java.io.*; 
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Tweet {
  private String userAccount;
  private String date;
  private String time;
  private String message;
  public static HashSet<String> stopWords;
  
  //Name: Gole Moradi
  //StudentID: 260726494

  //constructor 
  public Tweet(String userAccount, String date, String time, String message) {
    this.userAccount = userAccount;
    this.date = date;
    this.time = time;
    this.message = message;
  }
  
  //get methods
  public String getDate() {
    return this.date;
  }
  
  public String getTime() {
    return this.time;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String getUserAccount() {
    return this.userAccount;
  }
  
  //to string method
  public String toString() {
    String completeTweet = getUserAccount() + "\t" + getDate() + "\t" + getTime() + "\t" + getMessage();
    return completeTweet;
  }
  
  //method that checks if this tweet was posted earlier than a given tweet 
  public boolean isBefore(Tweet tweet1) {
    String date = tweet1.getDate();
    String[] dateComponents = date.split("-");
    String[] thisDateComponents = this.date.split("-");
    String time = tweet1.getTime();
    String[] timeComponents = time.split(":");
    String[] thisTimeComponents = this.time.split(":");
    
    //comparing the dates to see if its potentially earlier
    boolean earlier = false;
    int year = Integer.parseInt(dateComponents[0]);
    int thisYear = Integer.parseInt(thisDateComponents[0]);
    int month = Integer.parseInt(dateComponents[1]);
    int thisMonth = Integer.parseInt(thisDateComponents[1]);
    int day = Integer.parseInt(dateComponents[2]);
    int thisDay = Integer.parseInt(thisDateComponents[2]);
         
    if (thisYear==year) {
      if (thisMonth==month) {
        if (thisDay==day) {
          earlier = true;
        }
        else if (thisDay<day) {
          earlier = true;
          return true;
        }
        else if (thisDay>day) {
          earlier = false;
          return false;
        }
      } 
      else if (thisMonth<month) {
        earlier = true;
        return true;
      } 
      else if (thisMonth>month) {
        earlier = false;
        return false;
      }
    } 
    else if (thisYear<year) {
      earlier = true;
      return true;
    } 
    else if (thisYear>year) {
      earlier = false;
      return false;
    }
    
    //comparing the times
    if (earlier==true) {
      int hour = Integer.parseInt(timeComponents[0]);
      int thisHour = Integer.parseInt(thisTimeComponents[0]);
      int minute = Integer.parseInt(timeComponents[1]);
      int thisMinute = Integer.parseInt(thisTimeComponents[1]);
      int second = Integer.parseInt(timeComponents[2]);
      int thisSecond = Integer.parseInt(thisTimeComponents[2]);
      
      if (thisHour==hour) {
        if (thisMinute==minute) {
          if (thisSecond==second) {
            earlier = true;
            return true;
          }
          else if (thisSecond<second) {
            earlier = true;
            return true;
          }
          else if (thisSecond>second) {
            earlier = false;
            return false;
          }
        } 
        else if (thisMinute<minute) {
          earlier = true;
          return true;
        } 
        else if (thisMinute>minute) {
          earlier = false;
          return false;
        }
      } 
      else if (thisHour<hour) {
        earlier = true;
        return true;
      } 
      else if (thisHour>hour) {
        earlier = false;
        return false;
      }
    }
    
    return true;
    
  }
  
  
  //method to read file of stop words and initialize stopWords
  public static void loadStopWords(String stopWordFile) {
    try {
      FileReader fr = new FileReader(stopWordFile);
      BufferedReader br = new BufferedReader(fr);
      stopWords = new HashSet<String>();
      String line = br.readLine();
      while (line!=null) {
        stopWords.add(line);
        line = br.readLine();
      }
      br.close();
      fr.close();
    } catch (IOException e) {
      System.out.println("something went wrong");
    }
  }
  
  public static void main(String[] args) {
  }
  
  //method that checks if the message has a valid amount of words (excluding stop words)                                        
  public boolean checkMessage() {
    if (stopWords.equals(null)) {
      throw new NullPointerException("HashSet has not been initialized");
    }
    
    //removing punctuation
    String message = this.message;
    message =  message.replaceAll("\\,","");
    message =  message.replaceAll("\\.","");
    message =  message.replaceAll("\\;","");
    message =  message.replaceAll("\\:","");
    
    //splitting the tweet message into an array of strings
    String[] messageWords = message.split(" ");
    int validWords = 0;
    for (int i=0; i<messageWords.length; i++) {
      validWords++;
    }

   //checking for stop words
    for (int i=0; i<messageWords.length; i++) {
      for (String s: stopWords) {
        if (messageWords[i].equalsIgnoreCase(s)) {
          validWords--;
        }
      }
    }
    
    if (validWords<16 && validWords>0) {
      return true;
    }
    else {
      return false;
    }
  }
}
  

