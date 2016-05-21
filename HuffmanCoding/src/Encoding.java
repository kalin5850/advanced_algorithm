import java.io.*;
import java.util.*;

public class Encoding {

	private int length = 0;  // to get the file size
	private DataOutputStream fout;
	private int numBitsWritten;
	private Integer currentByte;
	BufferedWriter bufferedWriter;
		
	public Encoding(HuffmanTree compress, String filename, CharFrequency freq) throws IOException{
		FileWriter tmp1 = new FileWriter("tmp.txt");
        bufferedWriter = new BufferedWriter(tmp1);
		OutputStream out = new BufferedOutputStream(new FileOutputStream(filename + ".huf"));
		fout = new DataOutputStream(out);
		String [] code = compress.BuildCode(compress.getRoot());
		numBitsWritten = 0;
		currentByte = 0;
		fout.writeInt(freq.getMap().size());
		for (Map.Entry<Integer, Integer> map : freq.getMap().entrySet()) {
			fout.writeByte(map.getKey());
			fout.writeInt(map.getValue());
			length += map.getValue();
		}
		fout.writeByte(0);
		fout.writeInt(0);
		
		InputStream in = new BufferedInputStream(new FileInputStream(filename));		
		int ch = 0;
		while((ch = in.read()) != -1) {
			int [] codeInt = new int [code[ch].length()];
			codeInt = StringToInt(code[ch]);
			for (int tmp : codeInt) {
				writeBits(tmp);
			}
		}
		close();
	}
	
	private void writeBits(int bit) throws IOException{
		if (bit < 0 || bit > 1)
            throw new IllegalArgumentException("Argument to writeBit: bit = " + bit);

        numBitsWritten++;
        currentByte |= bit << (8 - numBitsWritten);
        if (numBitsWritten == 8) { 
        	bufferedWriter.write(currentByte.toString() + "\n");      	
        	fout.writeByte(currentByte);
            numBitsWritten = 0;
            currentByte = 0;
        }
	}
	
	private int [] StringToInt(String code) {
		int [] result = new int[code.length()];
		for( int i = 0; i < result.length; i++ )
			result[ i ] = code.charAt( i ) == '0' ? 0 : 1;
		return result;
	}
	
	private void close() throws IOException {
		fout.writeByte(currentByte);
		fout.writeByte(numBitsWritten);
		fout.flush();
		fout.close();
		bufferedWriter.close();
	}
	
	public String toString(){
		return "";
	}
}
