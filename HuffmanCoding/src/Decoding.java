import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Decoding {
	private InputStream in;
	private DataInputStream fin;
	private HuffmanTree tree;
	private CharFrequency freq;
	private HashMap<String,Integer> decodeTable;
	private String buffer;
	private DataOutputStream fout;
	private ByteArrayOutputStream byteOut;
	
	public Decoding(String filename) throws IOException{
		in = new BufferedInputStream(new FileInputStream(filename));
		fin = new DataInputStream(in);
		freq = new CharFrequency(fin);
		tree = new HuffmanTree(freq);
		tree.toString();
		
		decodeTable = new HashMap<String,Integer>();
		decodingTable();
		
		OutputStream out = new BufferedOutputStream(new FileOutputStream("Hamlet.txt"));
		fout = new DataOutputStream(out);
		buffer = "";
		decode();

	}
		
	protected void decode() throws IOException {		
		Integer ch;
		String bits = "";

		while((ch=fin.read()) != -1) {
			bits = Integer.toBinaryString(ch);
			if (bits.length() < 8) {
				int tmp = 8 - bits.length();
				for(int i = 0; i < tmp; i++) {
					bits = "0" + bits;
				}
			}
			getValue(buffer + bits);
		}
		close();
	}
	
	private void getValue(String code) throws IOException{
		int chValue = 0;
		int codeLength = 0;
		String tmp = "" + code.charAt(codeLength);
		while (true) {
			if (decodeTable.containsKey(tmp)) {
				byteOut = new ByteArrayOutputStream( );
				chValue = decodeTable.get(tmp);
				buffer = code.substring(tmp.length());
				fout.write(chValue);
				chValue = 0;
				return;
			}
			else {
				if (codeLength >= code.length() - 1) {
					decode();
				}
				codeLength++;
				tmp += code.charAt(codeLength);			
			}
		}
		
	}
	
	private void close() throws IOException{
		while(buffer != null && buffer.length() > 0) {			
			getValue(buffer);
		}
		fin.close();
		fout.close();
	}
	
	private void decodingTable () {
		String [] code = tree.BuildCode(tree.getRoot());
		for (int i = 0; i < code.length; i++){
			if (code[i] != null){			
				decodeTable.put(code[i], i);
			}
		}
		
		for (Map.Entry<String, Integer> map : decodeTable.entrySet()) {
			System.out.println(map.getKey() + " : " + map.getValue());
		}
	}	
}
