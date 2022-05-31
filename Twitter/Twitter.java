import java.io.*; 
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Twitter {
  private ArrayList<Tweet> tweets; 
  
  //main method to test
  public static void main(String[] args) {
    Twitter example = new Twitter();
    Tweet.loadStopWords("stopWords.txt");
    example.loadDB("tweets.txt");
    System.out.println(example.rangeTweets(example.getTweet(4), example.getTweet(2)));
    System.out.println(example.trendingTopic());
    System.out.println(example.printDB());
    System.out.println("The number of tweets is: " + example.getSizeTwitter()); 
  }
  
  //Name: Gole Moradi
  //StudentID: 260726494
  
  //constructor
  public Twitter() {
    this.tweets = new ArrayList<Tweet>();
  }
  
  //method that reads a file of tweets and adds them to the array list if theyre valid
  public void loadDB(String fileName) {
    try {
      FileReader fr = new FileReader(fileName);
      BufferedReader br = new BufferedReader(fr);
      String line = br.readLine();
      ArrayList<String> allTweets = new ArrayList<String>();
      //loop to go through all the tweets
      while (line!=null) {
        allTweets.add(line);
        line = br.readLine();
      }
      br.close();
      fr.close();
      
      int indexTweetsList = 0; 
      int size = allTweets.size();
      for (int i=0; i<size; i++) {
        //separating the tweet into user account, date, time, message
        String individualTweet = allTweets.get(i);
        String[] tweetComponents = individualTweet.split("\t");
        String userAccount = tweetComponents[0];
        String date = tweetComponents[1];
        String time = tweetComponents[2];
        String message = tweetComponents[3];
        Tweet theTweet = new Tweet(userAccount, date, time, message);
       
        //adding the tweet to the arraylist if valid
        if (theTweet.checkMessage() == true) {
          this.tweets.add(indexTweetsList,theTweet);
          indexTweetsList++;
        }
      }
      
      sortTwitter();
    } catch (IOException e) {
    } catch (NullPointerException e) {
      System.out.println("Error checking the stopWords database: The file of stop words has not been loaded yet");
    } catch (IndexOutOfBoundsException e) {
    }
    
  }
  
  //method that sorts tweets based on date/time of publication in increasing order
  public void sortTwitter() { 
    for (int i=0; i <this.tweets.size(); i++) {
      for (int j = this.tweets.size()-1; j>i; j--) {
        if (this.tweets.get(j).isBefore(this.tweets.get(i)) == true) {
          Tweet test = this.tweets.get(i);
          this.tweets.set(i,this.tweets.get(j));
          this.tweets.set(j,test);
        }
      }
    }
  }
  
  //get number of tweets
  public int getSizeTwitter() {
    int size = this.tweets.size();
    return size;
  }
  
  //get tweet at certain index
  public Tweet getTweet(int index) {
    Tweet tweet = this.tweets.get(index);
    return tweet;
  }
  
  //method that returns string of all the tweets
  public String printDB() {
    String allTweets ="";
    for (int i=0; i<this.tweets.size(); i++) {
      String s = this.tweets.get(i).toString();
      allTweets = allTweets + "\n" + s;
    }
    return allTweets;
  }
  
  //method rangeTweets
  public ArrayList<Tweet> rangeTweets(Tweet tweet1, Tweet tweet2) {
    int first = this.tweets.indexOf(tweet1);
    int second = this.tweets.indexOf(tweet2);
    
    if (second<first) {
      first = second;
      second = this.tweets.indexOf(tweet1);
    }
    
    ArrayList<Tweet> rangeOfTweets = new ArrayList<Tweet>();
    for (int i=first; i<=second; i++) {
      rangeOfTweets.add(this.tweets.get(i));
    }
    
    return rangeOfTweets;
  }
  
  //saveDB is a method that writes on a file all the tweets
  public void saveDB(String fileName) {
    try {
      FileWriter fw = new FileWriter(fileName);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(printDB());
      bw.close();
      fw.close();
    } catch (IOException e) {
    }
  }
  
  //method that returns the "trending" word (most frequent in tweets)
  public String trendingTopic() {
    HashMap<String,Integer> wordCount = new HashMap<String, Integer>();
    ArrayList<Tweet> listTweets = this.tweets;
  
    for (int i=0; i<this.tweets.size(); i++) {
      String tweet1 =  listTweets.get(i).getMessage();
      tweet1 = tweet1.replaceAll("\\;","");
      tweet1 = tweet1.replaceAll("\\:","");
      tweet1 = tweet1.replaceAll("\\.","");
      tweet1 = tweet1.replaceAll("\\,","");
      
      String[] words = tweet1.split(" ");
      for (int j=0; j<words.length; j++) {
        for (String s: Tweet.stopWords) {
          if (words[j].equalsIgnoreCase(s)) {
            words[j] = "";
          }
        }
      }
      
 
      for (int j=0; j<words.length; j++) {
        for (int k=j+1; k<words.length; k++) {
          if (words[j].equals(words[k])) {
            words[k] = "";
          }
        }
      }
      
      String tweet2 = "";
      for (int j=0; j<words.length; j++) {
        if (!words[j].equals("")) {
          tweet2 = tweet2 + words[j] + " ";
        }
      }
          
      
      //System.out.println(tweet2);
      
      String[] words2 = tweet2.split(" ");
      
      for (int j=0; j<words2.length; j++) {
        if (wordCount.containsKey(words2[j])) {
          int value = wordCount.get(words2[j]);
          value++;
          wordCount.put(words2[j], value);
        }
        else {
          wordCount.put(words2[j], 1);
        }
      }
      
    } 
    
    //finding out which key has the highest value
    String mostPopWord = "";
    int highestValue = 0;
      
    for (String s: wordCount.keySet()) {
      int value = wordCount.get(s);
      if (value>highestValue) {
        mostPopWord = s;
        highestValue = value;
      }
    }
    return mostPopWord;
  }
}










  
  