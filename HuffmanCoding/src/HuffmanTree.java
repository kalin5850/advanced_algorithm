import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class HuffmanTree {

	private CharFrequency freq;
	private PriorityQueue<HuffmanNode> tree;
	private HuffmanNode root;
	private HuffmanNode node;
	private int maxBitsLength;
	
	public HuffmanTree(CharFrequency freq) {
		this.freq = freq;
		tree = new PriorityQueue<HuffmanNode>();
		root = null;
		maxBitsLength = 0;
		createTree();
	}
	
	// to expand bit to byte
	protected String[] expand(String[] st) {
		int countBits = 0;
		for (int i = 0; i < st.length; i++){
			if (st[i] != null && st[i].length() >= countBits) {
				countBits = st[i].length();
			}
		}
		
		if (countBits % 8 != 0) {
			maxBitsLength = 8 - (countBits % 8) + countBits;
		}
		else{
			maxBitsLength = countBits;
		}
		for (int i = 0; i < st.length; i++) {
			if(st[i] != null && st[i].length() < maxBitsLength) {
				int shift = maxBitsLength - st[i].length();
				String tmp = "";
				for (int j = 0; j < shift; j++){
					tmp += "0";
				}
				st[i] += tmp;
			}
		}
		
		return st;
	} 
	
	protected String[] BuildCode(HuffmanNode root) {
		String[] st = new String[256];
		buildCode(st, root, "");
		return st;
	}
	
	private void buildCode(String[] st, HuffmanNode node, String s) {
		if (node.isLeaf()) {
			st[node.getChar()] = s;
			return;
		}
		buildCode(st, node.get_HuffmanNode_left(), s + '0');
		buildCode(st, node.get_HuffmanNode_right(), s + '1');
	}
	
	protected int getCharacters(String code) {
		HuffmanNode p = root;
		char ch = 0;
		int i = 0;
		return getChara(i, ch, p, code);
	}
	
	private int getChara(int i, int ch, HuffmanNode node, String code) {
		if (node.isLeaf()) {
			ch = node.getChar();
			return ch;
		}
		
		if (code.charAt(i) == '0')
			return getChara(i += 1, ch, node.get_HuffmanNode_left(), code);
		else
			return getChara(i += 1, ch, node.get_HuffmanNode_right(), code);
	}
	
	private void createTree() {
		for (Map.Entry<Integer, Integer> map : freq.getMap().entrySet()){
			node = new HuffmanNode(map.getKey(), map.getValue(), null, null, null);
			tree.add(node);
		}
		
		while(tree.size() > 1) {
			HuffmanNode n1 = tree.poll();
			HuffmanNode n2 = tree.poll();
			HuffmanNode tmpParent = new HuffmanNode('\0',
					n1.getWeight() + n2.getWeight(), n1, n2, null);
			n1.setParent(tmpParent);
			n2.setParent(tmpParent);
			tree.add(tmpParent);		
		}
		root = tree.peek();
	}
	
	public PriorityQueue<HuffmanNode> getTree() {
		return tree;
	}
	
	public HuffmanNode getRoot(){
		return root;
	}
	
	public int getMaxBitsLength() {
		return maxBitsLength;
	}
	
	public String toString(){
		System.out.println("-----------------------------------------");
		String [] code = BuildCode(root);
		for (int i = 0; i < code.length; i++){
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream( );
			if (code[i] != null){
				byteOut.write(i);
				System.out.println("Ascii: "+ byteOut.toString() +" \tcode: "+ code[i] + "----" + "code.length: "+code[i].length());
			}
		}	
		return "";
	}
	
}
