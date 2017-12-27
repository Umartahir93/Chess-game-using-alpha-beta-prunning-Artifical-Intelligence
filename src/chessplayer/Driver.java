package chessplayer;

import java.util.Scanner;

public class Driver {
	
	static int Player =1; 
	static int[][] move (chessMove obj , int gameState[][])
	    {
		   //change the state of the chess
		   gameState[obj.toRow][obj.toColumn] =  gameState[obj.fromRow][obj.fromColumn];
		   gameState[obj.fromRow][obj.fromColumn] = 0;
		   //change the player move
		   if(Player == 1)
			   Player = -1;
		   else
			   Player = 1;
		   return gameState;
		}
	
	
	
static public void main(String [] args)
{
	
	chessState obj = new chessState();
	int gameState[][] = {{-4,-2,-3,-5,-6,-3,-2,-4},
						 {-1,-1,-1,-1,-1,-1,-1,-1},
						 { 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
						 { 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
						 { 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
						 { 0 ,0 ,0 ,0 ,0 ,0 ,0 ,0},
						 { 1 ,1 ,1 ,1 ,1 ,1 ,1 ,1},
						 { 4 ,2 ,3 ,5 ,6 ,3 ,2 ,4}};
	
	chessMove makemove = new chessMove();
	ChessPlayer white = new L5947();
	ChessPlayer black = new L5947();
	Scanner scan = new Scanner(System.in);
	System.out.println("Input the depth you want");
	int input_depth = scan.nextInt();
	//System.out.println(gameState[2][1]);
	//I have placed it just for the sake of convenience
	while(true) {
	makemove = white.decideMoveUsingMinimax(gameState, input_depth, Player);
	gameState = move(makemove , gameState);
	makemove = black.decideMoveUsingMinimax(gameState, input_depth, Player);
	gameState = move(makemove , gameState);
	}	
}	
	
	
	
	
}
	
