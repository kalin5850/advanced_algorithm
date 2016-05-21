import java.io.*;

public class BitOutputStream {
	
	private OutputStream out;
	private int buffer;
	private int bufferPos;
	
	public BitOutputStream( OutputStream os ){ 
		bufferPos = 0; 
		buffer = 0; 
		out = os; 
	}
	
	public void writeBit( int val ) throws IOException
	{
		buffer = setBit( buffer, bufferPos++, val );
//		System.out.println(buffer);
		if( bufferPos == BitUtils.BITS_PER_BYTES ){
			flush( );
		}
	 }
	
	public void writeBits( int [ ] val ) throws IOException{
		for( int i = 0; i < val.length; i++ ) {
			writeBit( val[ i ] );
			System.out.print(val[i]);
		}
		System.out.println();
	}
	
	public void flush( ) throws IOException{
		if( bufferPos == 0 )
			return;
		System.out.println("if == bufferPos:" + bufferPos + "-------" +"buffer:" + buffer );
		out.write( buffer );
		bufferPos = 0;
		buffer = 0;
	}
	
	// flush(), there may be bits left in the buffer at the end of sequence
	// of writeBit calls
	public void close( ) throws IOException{ 
		flush( ); 
		out.close( ); 
	}
	
	private static int setBit( int pack, int pos, int val ){
		if( val == 1)
			pack |= (val << pos);
		return pack;
	}
	
}
