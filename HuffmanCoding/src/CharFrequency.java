import java.io.*;
import java.util.*;

public class CharFrequency {
	
	private HashMap<Integer,Integer> myMap;
	
	// for decoding constructor
	public CharFrequency(DataInputStream fin) throws IOException{
		myMap = new HashMap<Integer,Integer>();
		Integer ch = 0;
		int tmp = fin.readInt(); // to read the size of preamble
		for (int i = 1; i <= tmp; i++) {
			myMap.put(fin.read(), fin.readInt());
		}
		fin.read();
		fin.readInt();
	}
	
	// for encoding constructor
	public CharFrequency(String filename) throws IOException {
		Integer ch = 0;
		myMap = new HashMap<Integer,Integer>();
		InputStream in = new BufferedInputStream(new FileInputStream(filename));

		while((ch = in.read()) != -1){
			if (myMap.containsKey(ch)) {
				myMap.put(ch, myMap.get(ch) + 1);
			}
			else {
				myMap.put(ch, 1);
			}
		}
		
	}
		
	public HashMap<Integer,Integer> getMap() {
		return myMap;
	}
		
	public String toString() {
		
		String result="";	
		for (Map.Entry<Integer, Integer> map : myMap.entrySet()) {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream( );
			byteOut.write(map.getKey());
			result += map.getKey() + " : " + map.getValue() + "  \t" + byteOut.toString() + "\n";
		}		
		return result;
	}
}
