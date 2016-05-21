import java.io.*;

public class HZIPOutputStream {
	
	private ByteArrayOutputStream byteOut = new ByteArrayOutputStream( );
	private DataOutputStream dout;
	
	public HZIPOutputStream( OutputStream out ) throws IOException{		
		dout = new DataOutputStream( out );
	}
	
	public void write( int ch ) throws IOException{
		byteOut.write( ch ); // to read each character in the file, as byte in Ascii code Dec
	}
	
	public void close( ) throws IOException{
		byte [ ] theInput = byteOut.toByteArray( );  // to get the total characters in the file including space and newline
		ByteArrayInputStream byteIn = new ByteArrayInputStream( theInput );

		CharCounter countObj = new CharCounter( byteIn ); 
		byteIn.close( );

		HuffmanTree codeTree = new HuffmanTree( countObj );
		codeTree.writeEncodingTable( dout );				// dout.size = 385
		
		BitOutputStream bout = new BitOutputStream( dout ); 
		
//		System.out.printf("i ascii code is: %d\n", theInput[ 0 ] & 0xff);
		for( int i = 0; i < theInput.length; i++ )	{
			bout.writeBits( codeTree.getCode( theInput[ i ] & 0xff ) );
			System.out.println(theInput[ i ] & 0xff );
		}
		bout.writeBits( codeTree.getCode( BitUtils.EOF ) );
		
	
		bout.close( );		
		byteOut.close( );
	}
	
}
