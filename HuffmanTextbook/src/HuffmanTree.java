import java.io.*;
import java.util.*;

public class HuffmanTree {
	
	public static final int ERROR = -3;
	public static final int INCOMPLETE_CODE = -2;
	public static final int END = BitUtils.DIFF_BYTES;
	
	// CharCounter object that can be used to initialize the tree nodes.
	private CharCounter theCounts;
	// to maps each character to the tree node that contains it.
	private HuffNode [ ] theNodes = new HuffNode[ BitUtils.DIFF_BYTES + 1 ];
	private HuffNode root;
	
	public HuffmanTree(){
		theCounts = new CharCounter( );
		root = null;
	}
	
	// to initialize the CharCounter object
	public HuffmanTree(CharCounter cc){
		theCounts = cc; 
		root = null; 
		createTree( );
	}
	
	public int [ ] getCode(int ch ){
		HuffNode current = theNodes[ ch ]; 
		if( current == null )
			return null;
		
		String v = "";
		HuffNode par = current.parent;
		while ( par != null ) {
			if( par.left == current ) 
				v = "0" + v;
			else
				v = "1" + v;
			current = current.parent;
			par = current.parent; 
		}
		int [ ] result = new int[ v.length( ) ];
		for( int i = 0; i < result.length; i++ )
			result[ i ] = v.charAt( i ) == '0' ? 0 : 1;
		for (int a : result)
			System.out.println("ch:"+ a);
		return result;
	}
	
	public int getChar( String code ){
		HuffNode p = root;
		for( int i = 0; p != null && i < code.length( ); i++ )
			if(code.charAt( i ) == '0')
				p = p.left;
			else
				p = p.right;
		if( p == null ) 
			return ERROR;
		return p.value;
	}
	
	// to write the tree out to an output stream
	public void writeEncodingTable( DataOutputStream out ) throws IOException{
		for( int i = 0; i < BitUtils.DIFF_BYTES; i++ ){ // i is Dec number in the Ascii code, eg 105 = i
			if( theCounts.getCount(i) > 0){
				out.writeByte(i);
				out.writeInt(theCounts.getCount(i));
			}
		}
		out.writeByte( 0 ); 
		out.writeInt( 0 );
	}
	
	// re-build tree
	public void readEncodingTable( DataInputStream in ) throws IOException{
		for( int i = 0; i < BitUtils.DIFF_BYTES; i++ ) 
			theCounts.setCount( i, 0 );
		int ch; 
		int num;
		for(;; ){
			ch= in.readByte( ); 
			num = in.readInt( );
//			System.out.println("ch: "+ch+" --- "+"num: "+num);     // to get each character and frequency
			if( num==0)
				break;
			theCounts.setCount( ch, num );
		}
		createTree( );
	}
	
	private void createTree( ){
		
		PriorityQueue<HuffNode> pq = new PriorityQueue<HuffNode>();
		for( int i = 0; i < BitUtils.DIFF_BYTES; i++ ) 
			if( theCounts.getCount( i ) > 0 ){
				HuffNode newNode = new HuffNode( i,theCounts.getCount( i ), null, null, null );
				theNodes[ i ] = newNode;
				pq.add( newNode ); 
			}
		theNodes[ END ] = new HuffNode( END, 1, null, null, null ); 
		pq.add( theNodes[ END ] );
		
		while( pq.size( ) > 1 ){
			HuffNode n1 = pq.remove( );
			HuffNode n2 = pq.remove( );
			HuffNode result = new HuffNode( INCOMPLETE_CODE, n1.weight + n2.weight, n1, n2, null ); 
			n1.parent = n2.parent = result;
			pq.add( result ); 
		}
		root = pq.element( );
		
	}
}
