import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Homework5 {
	
	public Homework5(){
		
	}
	
	private static void compress(String filename) throws IOException {
		CharFrequency freq = new CharFrequency(filename);
		HuffmanTree compress = new HuffmanTree(freq);
		compress.toString();
		Encoding myEncoding = new Encoding(compress, filename, freq);
	}
	
	private static void uncompress(String filename) throws IOException {
		Decoding myDecoding = new Decoding(filename);

	}
	
	public static void main(String [] args) throws IOException {
		long runtimeTotal = Runtime.getRuntime().totalMemory();
		long runtimeFree = Runtime.getRuntime().freeMemory();
		if (args[0].equals("-h")) {
			compress(args[1]);
		}
		else {
			uncompress(args[1]);
		}
		System.out.println(runtimeTotal - runtimeFree);
	}
}
