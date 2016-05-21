package homework2_counting_words;
import java.io.*;
import java.util.*;

public class CountingWords {
	private HashMap<String, Integer> myHashMap;
	private LinkedList<String> [] wordsTable;
	private int maxCountFrequency;
	
	public CountingWords(){
		maxCountFrequency = 0;
		myHashMap = new HashMap<String, Integer>();
	}
	
	private static String checkWord(String words){		
		if (words.matches("[^A-Za-z]") || words.matches(".*\\d+.*")) return "";
		return words.toLowerCase();
	}
	
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
	
	public int getMaxCountFrequency(){
		return maxCountFrequency;
	}
	
	public HashMap<String, Integer> getHashMap(){
		return myHashMap;
	}
	
	public LinkedList<String> [] getWordTable(){
		return wordsTable;
	}
	
	public String toString(){
		String result = "";
		for (int i = wordsTable.length - 1; i > 0; i--){
			if (!wordsTable[i].isEmpty()){
				result += "Word Frequency:" + i + " " + wordsTable[i].toString() + "\n";
			}
		}
		return result;
	}
	
	public static void main(String [] args) throws FileNotFoundException{
		CountingWords myCountingWords = new CountingWords();
		Scanner text = new Scanner(new File("Hamlet.txt"));
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
