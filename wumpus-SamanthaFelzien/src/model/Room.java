package model;

public abstract class Room{
	
	private char symbol; 
	private String message; 
	
	public Room() {
		
		this.symbol = ' ';
		this.message = "";
	}

	public String getMessage(){
	
		return message; 
	}
	
	public char getSymbol() {
		
		return symbol; 
	}
	
	public String setMessage(){
		
		return message; 
	}
	
	public char setSymbol() {
		
		return symbol; 
	}
	
}
