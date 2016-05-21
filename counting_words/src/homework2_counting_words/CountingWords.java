/**
 * This class is to implement the word's frequency in the Hamlet.txt.
 * @author changkailin
 * @version 1.2
 */
package homework2_counting_words;
import java.io.*;
import java.util.*;

public class CountingWords {
	private HashMap<String, Integer> myHashMap;
	private LinkedList<String> [] wordsTable;
	private int maxCountFrequency;
	
	/**
	 * This is a constructor to initialize maxCountFrequency and myHashMap.
	 * @param maxCountFrequency the frequency of a word
	 * @param myHashMap myHashMap is a collection of storing key and value.
	 * 	 	  Key is a word, and value is the frequency of a word.
	 */
	public CountingWords(){
		maxCountFrequency = 0;
		myHashMap = new HashMap<String, Integer>();
	}
	
	/**
	 * This function is to check a string that only includes words without special
	 * characters, punctuation, digits and so on; also switch string into lower case. 
	 * @param words It is a variable for a string.
	 * @return It returns lower case.
	 */
	private static String checkWord(String words){
		
		if (words.matches("[^A-Za-z]") || words.matches(".*\\d+.*")) return "";
		return words.toLowerCase();
	}
	
	/**
	 * This it to create a hashtable and iterate each string into an object's index of array linkedlist
	 * based on word's frequency.
	 * @param myHashMap myHashMap is a hashtable.
	 * @param maxCountFrequency It is to count which word has maximum frequency.
	 */
	@SuppressWarnings("unchecked")
	private void hashTable(HashMap<String, Integer> myHashMap, int maxCountFrequency){
		wordsTable = (LinkedList<String> []) new LinkedList[maxCountFrequency + 1];
		for (int i = 0;i <= maxCountFrequency; i++){
			wordsTable[i] = new LinkedList<String>();
		}
		for (String tmp : myHashMap.keySet()){
			wordsTable[myHashMap.get(tmp)].add(tmp);
		}	
	}
	
	/**
	 * This is to get maxCountFrequency.
	 * @return
	 */
	public int getMaxCountFrequency(){
		return maxCountFrequency;
	}
	
	/**
	 * This it to get myHashMap.
	 * @return
	 */
	public HashMap<String, Integer> getHashMap(){
		return myHashMap;
	}
	
	/**
	 * This is to get wordsTable.
	 * @return
	 */
	public LinkedList<String> [] getWordTable(){
		return wordsTable;
	}
	
	/**
	 * This is to print the result.
	 */
	public String toString(){
		String result = "";
		for (int i = wordsTable.length - 1; i > 0; i--){
			if (!wordsTable[i].isEmpty()){
				result += "Word Frequency:" + i + " " + wordsTable[i].toString() + "\n";
			}
		}
		return result;
	}
	
	/**
	 * This is a main to iterate the each string at Hamlet.txt.
	 * @param args - not used
	 * @throws FileNotFoundException
	 */
	public static void main(String [] args) throws FileNotFoundException{
		Scanner text = new Scanner(new File("Hamlet.txt"));
		CountingWords myCountingWords = new CountingWords();
		try{
			do{
				String word = checkWord(text.next());
				if (myCountingWords.myHashMap.containsKey(word)){
					myCountingWords.myHashMap.put(word, myCountingWords.myHashMap.get(word) + 1);
					if (myCountingWords.myHashMap.get(word) > myCountingWords.maxCountFrequency){
						myCountingWords.maxCountFrequency = myCountingWords.myHashMap.get(word);
					}
				}
				else{
					myCountingWords.myHashMap.put(word, 1);
				}
			}
			while(text.hasNext());						
		}
		catch(Exception e){
			System.out.println("Bad File !!");
			System.exit(0);
		}
		myCountingWords.hashTable(myCountingWords.myHashMap, myCountingWords.maxCountFrequency);
		System.out.println(myCountingWords);
	}
}
