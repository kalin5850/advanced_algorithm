import java.io.*;

public class BitInputStream {
	
	 private InputStream in;
	 private int buffer;
	 private int bufferPos;
	
	public BitInputStream( InputStream is ){
		in = is;
		bufferPos = BitUtils.BITS_PER_BYTES;
	}
	
	// to read 8-bits = a byte into buffer, 
	public int readBit( ) throws IOException{
		if(bufferPos == BitUtils.BITS_PER_BYTES){
			buffer = in.read( ); 
			System.out.println("buffer:"+buffer);
			if( buffer == -1 )
				return -1;
			// how much of the buffer is unused
			bufferPos = 0;
		}
		return getBit( buffer, bufferPos++ );
	 }
	
	 public void close( ) throws IOException{
		 in.close( );
	 }
	 
	 // to access a bit in an 8-bits
	 private static int getBit( int pack, int pos ){
		 System.out.println("pack:"+pack+"----"+"pos:"+pos);
		 return ( pack & ( 1 << pos ) ) != 0 ? 1 : 0;
	 }

}
