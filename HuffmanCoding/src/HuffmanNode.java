
public class HuffmanNode implements Comparable<HuffmanNode>{
	
	private int ch;
	private int weight;
	private HuffmanNode right;
	private HuffmanNode left;
	private HuffmanNode parent;
	
	public HuffmanNode(){
		
	}
	
	public HuffmanNode(int ch, int weight, HuffmanNode right,
			HuffmanNode left, HuffmanNode parent) {
		this.ch = ch;
		this.weight = weight;
		this.right = right;
		this.left = left;
		this.parent = parent;
	} 
	
	public int compareTo(HuffmanNode tmp){		
		return weight - tmp.getWeight();
	}
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public void setCh(char ch){
		this.ch = ch;
	}
	
	public void setParent(HuffmanNode tmp){
		this.parent = tmp;
	}
	
	public int getChar() {
		return ch;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public HuffmanNode get_HuffmanNode_right() {
		return right;
	}
	
	public HuffmanNode get_HuffmanNode_left() {
		return left;
	}
	
	public HuffmanNode get_HuffmanNode_parent() {
		return parent;
	}
	
}
