import java.io.*;

public class TestCompress {
	
	public TestCompress(){
		
	}
	
	public static void compress( String inFile ) throws IOException {
		String compressedFile = inFile + ".huf";
		InputStream in = new BufferedInputStream(new FileInputStream( inFile ) );
		OutputStream fout = new BufferedOutputStream(new FileOutputStream( compressedFile ) );
		HZIPOutputStream hzout = new HZIPOutputStream( fout );
		int ch;
		while( ( ch = in.read( ))!=-1) {
			hzout.write( ch );
		}
		in.close( );
		hzout.close( );
	}
	
	public static void uncompress( String compressedFile ) throws IOException {
		
		String inFile;
		String extension;

		inFile = compressedFile.substring( 0, compressedFile.length( ) - 4 );
		extension = compressedFile.substring( compressedFile.length( ) - 4 );
	
		if( !extension.equals( ".huf" ) ) {
			System.out.println( "Not a compressed file!" );
			return;
		}
	
		inFile += ""; // for debugging, to not clobber original
		InputStream fin = new BufferedInputStream( new FileInputStream( compressedFile ) );
		DataInputStream in = new DataInputStream( fin );
		HZIPInputStream hzin = new HZIPInputStream( in );
	
		OutputStream fout = new BufferedOutputStream( new FileOutputStream( inFile ) );
		int ch;
		while((ch=hzin.read())!=-1) {
//			System.out.println("ch:"+ch);
			fout.write( ch );
		}
	
		hzin.close( );
		fout.close( ); 
	}
	
	public static void main(String[] args) throws IOException{
		compress("Hamlet.txt");
	}
}
