import java.io.*;

public class HZIPInputStream extends InputStream {
	private BitInputStream bin;
	private HuffmanTree codeTree;
	
	public HZIPInputStream( InputStream in ) throws IOException {
		DataInputStream din = new DataInputStream( in );
		
		codeTree = new HuffmanTree( );
		codeTree.readEncodingTable( din );		
		bin = new BitInputStream( in );
	}
		
	public int read( ) throws IOException {	
		String bits = "";
		int bit;
		int decode;
		
		while( true ){
			bit = bin.readBit( );
			if( bit == -1)
				throw new IOException( "Unexpected EOF" );

			bits += bit;
			System.out.println(bits);           // get characters by each bit
			decode = codeTree.getChar( bits );
			System.out.println("decode:"+decode);
			if( decode == HuffmanTree.INCOMPLETE_CODE )
				continue;
			else if( decode == HuffmanTree.ERROR )
				throw new IOException( "Decoding error" );
			else if( decode == HuffmanTree.END )
				return -1;
			else
				return decode;
		}
	}
		
	public void close( ) throws IOException{ 
		bin.close( );
	}
}
