package homework2_counting_words;

public class Cell {
	private String name;
	
	public Cell(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String setName(String name){
		return this.name + " " + name;
	}
}
