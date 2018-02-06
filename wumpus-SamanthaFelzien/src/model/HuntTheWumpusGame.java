package model;

import java.awt.Point;
import java.io.IOException;
import java.util.Scanner;

public class HuntTheWumpusGame {
	
	private Map map = new Map(); 
	private char[][] setMap = map.getMap();
	private char[][] dynamicMap = new char[12][12];
	private int height = 11; 
	private int width = 11; 
	private Point hunterPos;
	private boolean playerStatus = true; 
	private Point wumpusPos = map.getWumpus();
	private char charPrev; 
	private char charAtIndex; 

	public HuntTheWumpusGame() {
		
		
		initializeMap();
		getHunterMoves();

	}
	
	//Returns message and playerstatus (dead or alive) based on character at index from hunter move
	public boolean moveOutcome(char charAtIndex) {
		
		//if hunter moves into wumps
		if (charAtIndex == 'w') {
			playerStatus = false; 
			System.out.println("You walked into a wumpus! Death!");
			setChar('w');
			endMap();
		//if hunter moves in pit	
		} else if (charAtIndex == 'p') {
			playerStatus = false; 
			System.out.println("You fell into a pit! Death!");
			setChar('p');
			playerStatus = false; 
			endMap();
			
		}
		//if hunter walks into slime
		else if (charAtIndex == 's') {
			System.out.println("I can hear the wind");
			setChar('s');
			playerStatus = true; 
			
		}
		
		//if hunter walks into blood
		else if (charAtIndex == 'b') {
		System.out.println("I smell something foul");
		setChar('b');
		playerStatus = true; 
		}
		//if hunter walks into goop
		else if(  charAtIndex == 'g') {
			System.out.println("I smell something foul");
			System.out.println("I can hear the wind");
			setChar('g');
			playerStatus = true; 
			
			
		} else {
			//if space is available
			playerStatus = true; 
			setChar('o');
			
		}
		
		return playerStatus; 
	
	}
	
	public char setChar(char gameChar){
		charAtIndex = gameChar; 
		return charAtIndex;
	}

	
	public void getHunterMoves() {
		Scanner sc=new Scanner(System.in); 
		
		String input = "";
		 
	     
		while (playerStatus == true) {
				System.out.println("Move n, s, e, w or arrow");  
				input = sc.nextLine().toLowerCase();
			
		   
		   //System.out.println(move);
		   dynamicMap[hunterPos.x][hunterPos.y] = charPrev;
		   if (charPrev == 'o') {
			   dynamicMap[hunterPos.x][hunterPos.y] = ' ';
		   } 
		   
		 if (input.equals("n")) {
		   try {
			   // row = hunterRow-1;
			   hunterPos.x -= 1; 
			   charAtIndex = setMap[hunterPos.x][hunterPos.y];
			   moveOutcome(charAtIndex);
		   } catch (ArrayIndexOutOfBoundsException e) {
       			hunterPos.x = height;
       			charAtIndex = setMap[hunterPos.x][hunterPos.y];
       			moveOutcome(charAtIndex);
       			System.out.println("Char at index " + charAtIndex);
		   }
		   dynamicMap[hunterPos.x][hunterPos.y] = charAtIndex;
		 }
		   
		   if (input.equals("s")) {
			   try {
				   // row = hunterRow-1;
				   hunterPos.x += 1; 
				   charAtIndex = setMap[hunterPos.x][hunterPos.y];
				   moveOutcome(charAtIndex);
			   } catch (ArrayIndexOutOfBoundsException e) {
	       			hunterPos.x = 0;
	       			charAtIndex = setMap[hunterPos.x][hunterPos.y];
	       			moveOutcome(charAtIndex);	 
			   }
			   dynamicMap[hunterPos.x][hunterPos.y] = charAtIndex;
		   }
		   
		   if (input.equals("e")) {
			   try {
				   // row = hunterRow-1;
				   hunterPos.y += 1; 
				   charAtIndex = setMap[hunterPos.x][hunterPos.y];
				   moveOutcome(charAtIndex);
			   } catch (ArrayIndexOutOfBoundsException e) {
	       			hunterPos.y = 0;
	       			charAtIndex = setMap[hunterPos.x][hunterPos.y];
	       			moveOutcome(charAtIndex);	 
			   }
			   dynamicMap[hunterPos.x][hunterPos.y] = charAtIndex;
		   }
		   
		   if (input.equals("w")) {
			   try {
				   // row = hunterRow-1;
				   hunterPos.y -= 1; 
				   charAtIndex = setMap[hunterPos.x][hunterPos.y];
				   moveOutcome(charAtIndex);
			   } catch (ArrayIndexOutOfBoundsException e) {
	       			hunterPos.y = width;
	       			charAtIndex = setMap[hunterPos.x][hunterPos.y];
	       			moveOutcome(charAtIndex);	 
			   }
			   dynamicMap[hunterPos.x][hunterPos.y] = charAtIndex;
		   }
		   
		   if (input.equals("arrow")) {
			   String shotDir = "";
			   
			   System.out.println("Which direction? (n, s, e, w?)");  
				shotDir = sc.nextLine().toLowerCase();
				if (shotDir.equals("n") || shotDir.equals("s")) {
					if (hunterPos.y == wumpusPos.y) {
						System.out.println("You shot the wumpus!");
						System.out.println("*******************************************");
						System.out.println("*  **   *   **   **   **  **");
						System.out.println("*  **   *   **   **   **  **");
						System.out.println(" * * * *    **   ***  **  **");
						System.out.println(" **   **    **   ** ****    ");
						System.out.println("  *   *     **   ** ****  ** ");
						System.out.println("   						  ** ");
						playerStatus = false;  
					} else {
						System.out.println("You shot yourself!");
						playerStatus = false; 
						endMap();
					}
				}
				
					if (shotDir.equals("e") || shotDir.equals("w")) {
						if (hunterPos.x == wumpusPos.x) {
								System.out.println("It's a hit!");
								playerStatus = false;  
							} else {
								System.out.println("You shot yourself!");
								playerStatus = false; 
								endMap();
							}
					}
			   dynamicMap[wumpusPos.x][wumpusPos.y] = 'w';
		   }
			   		
			   charPrev = charAtIndex; 
			   if (playerStatus != false) {printMap();}
			}
			}

	public void endMap() {
		map.printMap();
		
	}
	
	public void initializeMap() {
		for (int row = 0; row < dynamicMap.length; row++) {
			for (int col = 0; col < dynamicMap[row].length; col++) {
				dynamicMap[row][col] = 'x';
			}
		}
		hunterPos= map.getHunter();
		
		dynamicMap[hunterPos.x][hunterPos.y] = 'o';
		printMap();
	}
	
	public void printMap() {
		 for (int i = 0; i < dynamicMap.length; i++) {
	            for (int j = 0; j < dynamicMap[i].length; j++) {
	                System.out.print(dynamicMap[i][j] + " ");
	            }
	            System.out.println();
	}
	
	
}
}
