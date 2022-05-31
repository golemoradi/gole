package assignment4;

import java.util.ArrayList;
import java.util.LinkedList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	private ArrayList<Song> songs;
	private MyHashTable<Integer, ArrayList<Song>> years;
	private MyHashTable<String,Song> titles;
	private MyHashTable<String,ArrayList<Song>> artists;
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE
        this.songs = songs;
        
        int numBuckets = songs.size();
        this.years = new MyHashTable<>(numBuckets);
        this.titles = new MyHashTable<>(numBuckets);
        this.artists = new MyHashTable<>(numBuckets);
        
        //populating the hashtables
        for (Song a_song: songs) {
        	years.put(a_song.getYear(), searchByYear(a_song.getYear()));
        	artists.put(a_song.getArtist(), searchByArtist(a_song.getArtist()));
        	titles.put(a_song.getTitle(), a_song);
        }
        //ADD YOUR CODE ABOVE HERE
    }
 
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
        //updating the hashtables
        titles.put(s.getTitle(), s);
        
        int s_year = s.getYear();
        for (HashPair<Integer, ArrayList<Song>> diff_years: this.years) {
        	if (diff_years.getKey()==s_year) {
        		diff_years.getValue().add(s);
        		break;
        	}
        }
        
        for (HashPair<String, ArrayList<Song>> diff_artists: this.artists) {
        	if (diff_artists.getKey().equals(s.getArtist())) {
        		diff_artists.getValue().add(s);
        		break;
        	}
        }
        
        songs.add(s);
        
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
        return titles.get(title);
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
    	//iterating through all the songs to find the ones that match the artist arg
    	ArrayList<Song> more_songs = new ArrayList<>();
    	for (Song a_song: this.songs) {
    		if (a_song.getArtist().equals(artist)) {
    			more_songs.add(a_song);
    		}
    	}
    	return more_songs;
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE
    	//iterating through the songs to find the song year that matches year
    	ArrayList<Song> more_songs = new ArrayList<>();
    	for (Song a_song: this.songs) {
    		if (a_song.getYear()==year) {
    			more_songs.add(a_song);
    		}
    	}
		return more_songs;
        //ADD CODE ABOVE HERE
        
    }
}
