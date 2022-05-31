package assignment4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

//gole moradi
//260726494

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.numBuckets = initialCapacity;
        this.numEntries = 0;
        
        //initializing the arraylist
        buckets = new ArrayList<>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
			LinkedList<HashPair<K,V>> tmp = new LinkedList<>();
        	buckets.add(tmp);
		}
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets vairable. Usefull for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    	
    	//getting the index of the key
    	int index = hashFunction(key);
    	
    	if (key==null) {
    		return null;
    	}
    	
    	//checking if the key already exists
    	if (get(key)!=null) {
    		int newNumE = this.numEntries + 1;
    		if (((double) newNumE/(double) numBuckets)> MAX_LOAD_FACTOR) {
    			rehash();
    		}
    	}
    	
    	//retrieving the linkedlist at the index
    	LinkedList<HashPair<K,V>> list = buckets.get(index);
    	for (HashPair<K,V> pair: list) {
    		//if the key already exists
    		if (pair.getKey().equals(key)) {
    			V oldValue = pair.getValue();
    			pair.setValue(value);
    			list.remove(list.element());
    			list.add(pair);
    			return oldValue;
    		}
    	}
    	
    	//if there was no key 
    	HashPair<K,V> newPair = new HashPair<>(key,value);
    	list.add(newPair);
    	this.numEntries++;
        return null;
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    	int index = hashFunction(key);
    	
    	LinkedList<HashPair<K,V>> list = buckets.get(index);
    	//iterating through the list
    	for (HashPair<K,V> pair: list) {
    		if (pair.getKey().equals(key)) {
    			return pair.getValue();
    		}
    	}
  
    	return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair correspoinding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	if (key==null) {
    		return null;
    	}
    	//getting the index of the key
    	int index = hashFunction(key);;
    	
    	//retrieving the linkedlist at the index
    	LinkedList<HashPair<K,V>> list = buckets.get(index);
    	//iterating through the list
    	for (HashPair<K,V> pair: list) {
    		if (pair.getKey().equals(key)) {
    			V oldValue = pair.getValue();
    			list.remove(pair);
    			this.numEntries--;
    			return oldValue;
    		}
    	}
    	
        return null;
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
    	MyHashTable<K,V> tmp = new MyHashTable<K,V>(this.numBuckets*2);
    	
    	for (LinkedList<HashPair<K,V>> list: buckets) {
    		for (HashPair<K,V> pair: list) {
    			tmp.put(pair.getKey(), pair.getValue());
    		}
    	}
    	//updating the attributes
    	this.numBuckets = tmp.numBuckets;
    	this.numEntries = tmp.numEntries;
        this.buckets = tmp.buckets;
        
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
    	
    	//creating a new array to hold the keys
    	ArrayList<K> allKeys = new ArrayList<>(numEntries);
    	//looping through all the buckets
    	for (LinkedList<HashPair<K,V>> list: buckets) {
    		//looping through the hash pairs of the linked lists
    		for (HashPair<K,V> pair: list) {
    			allKeys.add(pair.getKey());
    		}
    	}
    	
    	return allKeys;
       
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
        
    	ArrayList<V> uniqueValues = new ArrayList<>();
    	//looping through all the buckets
    	for (LinkedList<HashPair<K,V>> list: buckets) {
    		for (HashPair<K,V> pair: list) {
    			V isUnique = pair.getValue();
    			if (!uniqueValues.contains(isUnique)) {
    				uniqueValues.add(isUnique);
    			}
    		}
    	}
    	
    	return uniqueValues;
    	
        //ADD CODE ABOVE HERE
    }
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        	for (int i=0; i<numBuckets; i++) {
        		LinkedList<HashPair<K,V>> list = buckets.get(i);
        		this.entries.addAll(list);
        	}
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	return (!entries.isEmpty() && entries.peek()!=null);
      
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	
        	return entries.poll();
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
} 
