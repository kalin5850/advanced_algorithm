
public class HuffNode implements Comparable<HuffNode>{
	
	public int value;
	public int weight;
	
	public int compareTo( HuffNode rhs ){
		return weight - rhs.weight;
	}
	
	HuffNode left;
	HuffNode right;
	HuffNode parent;

	HuffNode( int v, int w, HuffNode lt, HuffNode rt, HuffNode pt ){
		value = v; 
		weight = w; 
		left = lt; 
		right = rt; 
		parent = pt; 
	}
}
