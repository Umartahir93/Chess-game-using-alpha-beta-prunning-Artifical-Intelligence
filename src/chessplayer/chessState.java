/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessplayer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mubasher.baig
 */
public class chessState {
	//I have used the HashMap it is data structure with key and value
	//here key will be player and value be score for checkmate
     HashMap<Integer, Integer> check_evaluation_score = new HashMap<Integer , Integer>();
	//This will store the state of the chess
	int state[][];
    
	//This will store the current player 
	int player;
    
	//Here we will store all the moves
    ArrayList<chessMove> moves = new ArrayList<chessMove>();
    ArrayList<chessMove> final_moves = new ArrayList<chessMove>();
    
    //chess constructor
   chessState(){
	   state = new int[8][8];
	   player = 1;
   
   }
   
   
   //this will make a move
   boolean makeMove (chessMove move_piece)
    {
	   //change the state of the chess
	   state[move_piece.toRow][move_piece.toColumn] =  state[move_piece.fromRow][move_piece.fromColumn];
	   state[move_piece.fromRow][move_piece.fromColumn] = 0;
	   //change the player move
	   player = -1;
	   
	   return true;
	}
   
   
   
   //This will show the tree of the state
   void showTree()
   {
	   
   }
   //This will show the current state of the chess
   void showCurrentState()
   {
	   
   }
   
   // This function creates a list of all the legal moves 
   // for the current player in the current state of the game and
   //it also count total moves

   int getLegalMoveList() {
   int piece;		
   int count = 0;
for (int i = 0; i < 8; i++)
{
	for (int j = 0; j < 8; j++)
	{
		piece = state[i][j];
		switch (player)
		{
		case 1:
			switch (piece)
			{
			case 1:
			WhitePawn(state, i, j, moves);
			break;
			case 2:
			Knight(state, i, j, moves, piece>0);
			break;
			case 3:
			Bishop(state, i, j, moves, piece>0);
			break;
			case 4:
			Rook(state, i, j, moves, piece>0);
			break;
			case 5:
			Queen(state, i, j, moves, piece>0);
			break;
			case 6:
			King(state, i, j, moves, piece>0);
			break;
			default:
			break;
			}
			break;

		case -1:
			switch (piece)
			{
			case -1:
			BlackPawn(state, i, j, moves);
			break;
			case -2:
			Knight(state, i, j, moves, piece>0);
			break;
			case -3:
			Bishop(state, i, j, moves, piece>0);
			break;
			case -4:
			Rook(state, i, j, moves, piece>0);
			break;
			case -5:
			Queen(state, i, j, moves, piece>0);
			break;
			case -6:
			King(state, i, j, moves, piece>0);
			break;
			default:
			break;
			}
			break;

			}
		}
	}
		
		for (int i = 0; i < moves.size(); ++i) 
		{
			chessState state = new chessState();
			state.makeMove(moves.get(i));
		if (!check_mate(state, this.player)){
				final_moves.add(moves.get(i));
				++count;
			}
		else
			check_evaluation_score.put(this.player, 10); 
			//10 is the score I added for the checkmate
		}
		return count;
		}
   
 //These all are the function of the pieces to get the legal moves  
   
//1. Movement of the white Pawn   

void WhitePawn(int state[][], int current_row, int current_column,
		ArrayList<chessMove> moves)
{
	if (current_row == 6)
	{	
		//white pawn first move on first move pawn can move
		//either one block or two in my case i have it two
		//blocks to make it simpler
		if (state[current_row - 1][current_column] == 0 &&
			state[current_row - 2][current_column] == 0){
			moves.add(new chessMove(current_row, current_column,
					current_row - 2, current_column));	
			}
	}
	//movement of pawn when the current row is greater then one
	//I have put this current row greater equal then 1
	//because on 1 pawn gets replace with other key
	else if (current_row >= 1)
	{
		//you can move it diagonally if there is any black piece
		if (current_column >= 1 &&
			state[current_row - 1][current_column - 1] < 0)						
		{
			moves.add(new chessMove(current_row, current_column,
						current_row - 1, current_column - 1));
		}
		//you can move it diagonally if there is any black piece
		else if (current_column <= 6 &&
				 state[current_row - 1][current_column + 1] < 0)						
		{
			moves.add(new chessMove(current_row, current_column,
						current_row - 1, current_column + 1));
		}
		//or you can move it straight if next is empty
		else if (state[current_row - 1][current_column] == 0)										
		{
			moves.add(new chessMove(current_row, current_column,
						current_row - 1, current_column) );
		}
		
	}
}

   
//2. Movement of the Black Pawn
void BlackPawn(int state[][], int current_row, int current_column, ArrayList<chessMove> moves)
{
	if (current_row == 1)
	{
		if (state[current_row + 1][current_column] == 0 &&
				state[current_row + 2][current_column] == 0){
				moves.add(new chessMove(current_row, current_column,
							current_row + 2, current_column));}
	}
	
	else if (current_row <= 6)
	{
			if (current_column >= 1 && 
				state[current_row + 1][current_column - 1] > 0){
				moves.add(new chessMove(current_row, current_column,
							current_row + 1, current_column - 1));
			}
			
			else if (current_column <= 6 &&
					state[current_row + 1][current_column + 1] > 0){
				moves.add(new chessMove(current_row, current_column, 
							current_row + 1, current_column + 1));
			}
			
			else if (state[current_row + 1][current_column] == 0)										
			{
				moves.add(new chessMove(current_row, current_column, 
						current_row + 1, current_column) );
			}
	}

}

//3. Movement of Bishop

//it includes all the conditions and all the situations 
//upright , upleft , downright, downleft

void Bishop(int state[][], int rows, int columns, 
		ArrayList<chessMove> moves , boolean currentplayerwhite){
	
	boolean check  = true;

//This condition holds for when the bishop is in first row	

if (rows == 0)
	{
		if (columns == 0)
		{
			//DownRight movement of the bishop
			
			check = true;
			for (int i = rows + 1, j = columns + 1; check &&
					i <= 7 && j <= 7; ++i, ++j)	{
				//move in the empty spaces
				if (state[i][j] == 0)															
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//move to capture the pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));								
					check = false;
				}
				//Flag check for the pieces  
				else if (state[i][j] > 0 == currentplayerwhite)					
				{
					check = false;
				}
			}
		
		}
		
		else if (columns == 7)
		{
			//DownLeft movement of the bishop
			
			check = true;
			for (int i = rows + 1, j = columns - 1; check &&
				i <= 7 && j >= 0; ++i, --j){
				//if the spaces are empty move the piece
				if (state[i][j] == 0)															
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//move to capture the pieces 
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));									
					check = false;
				}
				//check for the same pieces (flag)
				else if (state[i][j] > 0 == currentplayerwhite)											
				{
					check = false;
				}
			}
		}
		
		else
		{
			//DownRight movement of the Bishop
			check = true;
			for (int i = rows + 1, j = columns + 1; check &&
						i <= 7 && j <= 7; ++i, ++j)	{
				//move the pieces in the empty position
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//move to capture pieces 
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));								
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
			
			//DownLeft movement of the Bishop
				check = true;
			for (int i = rows + 1, j = columns - 1; check &&
					i <= 7 && j >= 0; ++i, --j){
				//move the pieces in the empty position
				if (state[i][j] == 0)															
					{
						moves.add(new chessMove(rows, columns, i, j));
					}
				//move to capture piece
				else if (currentplayerwhite == (state[i][j] < 0))
					{
					moves.add(new chessMove(rows, columns, i, j));									
					check = false;
					}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)											
					{
					check = false;
					}
				}
			}
		}
	
//This condition holds for when the bishop is in the last row	
else if (rows == 7)
	{
		if (columns == 0)
		{
			//UpRight movement of the Bishop
			
			check = true;
			for (int i = rows - 1, j = columns + 1; check
					&& i >= 0 && j <= 7; --i, ++j){
				//move the pieces in empty position
				if (state[i][j] == 0)															
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//capture the pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));									
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)											
				{
					check = false;
				}
			}
		}
		else if (columns == 7)
		{
			//UpLeft movement of the bishop
			check = true;
		
			for (int i = rows - 1, j = columns - 1; check
				&& i >= 0 && j >= 0; --i, --j){
				//move the pieces in the empty position
				if (state[i][j] == 0)														
				{
					moves.add(new  chessMove(rows, columns, i, j));
				}
				//move to capture the pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));								
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
		}
		
		else
		{
			//UpRight movement of the Bishop
			check = true;
			for (int i = rows - 1, j = columns + 1; check
					&& i >= 0 && j <= 7; --i, ++j){
				//movement in the empty spaces
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//movement to capture the pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));								
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)										
				{
					check = false;
				}
			}
			
			//UpLeft movement of the bishop
			
			check = true;
			for (int i = rows - 1, j = columns - 1; check
					&& i >= 0 && j >= 0; --i, --j){
				//movement of the bishop in the empty position
				if (state[i][j] == 0)														
				{
					moves.add(new  chessMove(rows, columns, i, j));
				}
				//movement of the bishop to capture pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));												
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
		
		}
	}
	
//This condition holds for all the rows except the first and the
//last row
	else
	{
		if (columns == 0)
		{
			//UpRight movement of the bishop
			check = true;
			for (int i = rows - 1, j = columns + 1; check
					&& i >= 0 && j <= 7; --i, ++j){
				//movement of the bishop in the empty spaces
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//movement to capture the pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
			
			//DownRight movement of the bishop
		
			check = true;
			for (int i = rows + 1, j = columns + 1; check &&
					i <= 7 && j <= 7; ++i, ++j){
				//movement of the bishop in the empty spaces
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//movement to capture the pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
		}
		
		else if (columns == 7)
		{
			//UpLeft movement of the bishop
			
			check = true;
			for (int i = rows - 1, j = columns - 1; check
					&& i >= 0 && j >= 0; --i, --j){
				//movement of the bishop in empty spaces
				if (state[i][j] == 0)														
				{
					moves.add(new  chessMove(rows, columns, i, j));
				}
				//movement of the bishop to capture pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
			
			//DownLeft(board, row, col, isWhite, Moves, index);
			check = true;
			for (int i = rows + 1, j = columns - 1; check &&
					i <= 7 && j >= 0; ++i, --j){
				//movement of the bishop in empty spaces
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				//movement of the bishop to capture pieces
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				//check for the same pieces(flag)
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
		
		}
		else
		{
		
			//UpLeft movement of the Bishop
			
			check = true;
			for (int i = rows - 1, j = columns - 1; check
					&& i >= 0 && j >= 0; --i, --j){
				if (state[i][j] == 0)														
				{
					moves.add(new  chessMove(rows, columns, i, j));
				}
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
			
			//DownLeft movement of the Bishop
			check = true;
			for (int i = rows + 1, j = columns - 1; check &&
					i <= 7 && j >= 0; ++i, --j){
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
		
			//UpRight movement of the bishop
			check = true;
			for (int i = rows - 1, j = columns + 1; check
					&& i >= 0 && j <= 7; --i, ++j){
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
			
			//DownRight of the bishop
		
			check = true;
			for (int i = rows + 1, j = columns + 1; check &&
					i <= 7 && j <= 7; ++i, ++j)	{
				if (state[i][j] == 0)														
				{
					moves.add(new chessMove(rows, columns, i, j));
				}
				else if (currentplayerwhite == (state[i][j] < 0))
				{
					moves.add(new chessMove(rows, columns, i, j));							
					check = false;
				}
				else if (state[i][j] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}		
		}
	} 
}



//4. Movement of the king
void King(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite){

	//all the conditions are for moving the king
	//if you see an empty space move there or
	//if you see a enemy capture it
	
	if (rows > 0 && (state[rows - 1][columns] == 0 ||
			currentplayerwhite == (state[rows - 1][columns] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows - 1, columns));
	}
	
	if (rows < 7 && (state[rows + 1][columns] == 0 ||
			currentplayerwhite == (state[rows + 1][columns] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows + 1, columns));
	}
	
	if (columns > 0 && (state[rows][columns - 1] == 0 ||
			currentplayerwhite == (state[rows][columns - 1] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows, columns - 1));
	}
	
	if (columns < 7 && (state[rows][columns + 1] == 0 ||
			currentplayerwhite == (state[rows][columns + 1] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows, columns + 1));
	}
	
	if (columns > 0 && rows > 0 && (state[rows - 1][columns - 1] == 0 ||
			currentplayerwhite == (state[rows - 1][columns - 1] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows - 1, columns - 1));
	}
	
	if (columns < 7 && rows > 0 && (state[rows - 1][columns + 1] == 0 ||
			currentplayerwhite == (state[rows - 1][columns + 1] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows - 1, columns + 1));
	}
	
	if (columns < 7 && rows < 7 && (state[rows + 1][columns + 1] == 0 ||
			currentplayerwhite == (state[rows + 1][columns + 1] < 0)))
	{
		moves.add(new  chessMove(rows, columns, rows + 1, columns + 1));
	}
	
	if (columns > 0 && rows < 7 && (state[rows + 1][columns - 1] == 0 ||
			currentplayerwhite == (state[rows + 1][columns - 1] < 0)))
	{
		moves.add(new chessMove(rows, columns, rows + 1, columns - 1));
	}


}



//5. Movement of the rock
void Rook(int state[][], int rows, int columns, 
		ArrayList<chessMove> moves , boolean currentplayerwhite){
	boolean check;
	
if (rows > 0)
	{
		check = true;
		for (int i = rows - 1; check && i >= 0; --i)										
		{
			if (state[i][columns] == 0)														
			{
				moves.add(new chessMove(rows, columns, i, columns));
			}
			else if (currentplayerwhite == (state[i][columns] < 0))							
			{
				moves.add(new chessMove(rows, columns, i, columns));
				check = false;
			}
			else if (state[i][columns] > 0 == currentplayerwhite)							
			{
				check = false;
			}
		}
	}
	
if (rows < 7)
		{
		check = true;	
		for (int i = rows + 1; check && i <= 7; ++i)										
			{
				if (state[i][columns] == 0)															 
				{
					moves.add(new chessMove(rows, columns, i, columns));
				}
				else if (currentplayerwhite == (state[i][columns] < 0))								
				{
					moves.add(new chessMove(rows, columns, i, columns));
					check = false;
				}
				else if (state[i][columns] > 0 == currentplayerwhite)								
				{
					check = false;
				}
			}
		}	
		
if (columns > 0)
	{
	
		check = true;
		for (int j = columns - 1; check && j >= 0; --j)											
		{
			if (state[rows][j] == 0)															
			{
				moves.add(new chessMove(rows, columns, rows, j));
			}
			else if (currentplayerwhite == (state[rows][j] < 0))								
			{
				moves.add(new chessMove(rows, columns, rows, j));
				check = false;
			}
			else if (state[rows][j] > 0 == currentplayerwhite)									
			{
				check = false;
			}
		}
	}

if (columns < 7)
	{
		check = true;
		for (int j = columns + 1; check && j <= 7; ++j)											
		{
			if (state[rows][j] == 0)															 
			{
				moves.add(new chessMove(rows, columns, rows, j));
			}
			else if (currentplayerwhite == (state[rows][j] < 0))								
			{
				moves.add(new chessMove(rows, columns, rows, j));
				check = false;
			}
			else if (state[rows][j] > 0 == currentplayerwhite)											
			{
				check = false;
			}
		}
	}
}




//6. Movement of the Queen
void Queen(int state[][], int rows, int columns,ArrayList<chessMove> moves , boolean currentplayerwhite){
	
	Bishop(state, rows, columns, moves ,currentplayerwhite);
	Rook(state, rows, columns, moves ,currentplayerwhite);
}




/*These all are the utility function of the Bishop
I am making the function of every kind of the movements
to make things easier*/

//This utility function is for the one Right and two down movement
void Movement1(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite){
		if (state[rows + 2][columns + 1] == 0 || 
		currentplayerwhite == (state[rows + 2][columns + 1] < 0)){																		
		moves.add(new  chessMove(rows, columns, rows + 2, columns + 1));	
	}
}


//This utility function is for the one left and two down movement
void Movement2(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite){
	if (state[rows + 2][columns - 1] == 0 || 
			currentplayerwhite == (state[rows + 2][columns - 1] < 0)){																		
		moves.add(new chessMove(rows, columns, rows + 2, columns - 1));		
	}
}

//This utility function is for the one Right and two up movement
void Movement3(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite){
	if (state[rows - 2][columns + 1] == 0 ||
			currentplayerwhite == (state[rows - 2][columns + 1] < 0)){																						// capture if enemy
		moves.add(new chessMove(rows, columns, rows - 2, columns + 1));		
	}
}


//This utility function is for the one left and two up movement
void Movement4(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite){
	if (state[rows - 2][columns - 1] == 0 ||
			currentplayerwhite == (state[rows - 2][columns - 1] < 0)){																			
		moves.add(new chessMove(rows, columns, rows - 2, columns - 1));	
	}
}

//This utility function is for the one down and two left movement
void Movement5(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite) {
	if (state[rows + 1][columns - 2] == 0 ||
			currentplayerwhite == (state[rows + 1][columns - 2] < 0)){																	
		moves.add(new chessMove(rows, columns, rows + 1, columns - 2));			
	}
}


//This utility function is for the one UP and two left movement
void Movement6(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite) {
	if (state[rows - 1][columns - 2] == 0 || 
			currentplayerwhite == (state[rows - 1][columns - 2] < 0)){																	
		moves.add(new chessMove(rows, columns, rows - 1, columns - 2));			
	}
}

//This utility function is for the one down and two right movement
void Movement7(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite) {
	if (state[rows + 1][columns + 2] == 0 ||
			currentplayerwhite == (state[rows + 1][columns + 2] < 0)){																
		moves.add(new chessMove(rows, columns, rows + 1, columns + 2));		
	}
}

//This utility function is for the two Right and one up movement
void Movement8 (int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite) {
	if (state[rows - 1][columns + 2] == 0 ||
			currentplayerwhite == (state[rows - 1][columns + 2] < 0)){																	
		moves.add(new chessMove(rows, columns, rows - 1, columns + 2));								
		}
}

//7. Movement of the Knight
void Knight(int state[][], int rows, int columns,
		ArrayList<chessMove> moves , boolean currentplayerwhite)
{
	//Movement when row is less and equal to 1
	if (rows <= 1)	
	{
		if (columns <= 1){
			Movement1(state, rows, columns, moves,currentplayerwhite);
			Movement7(state, rows, columns, moves,currentplayerwhite);
			if (columns == 1){
				Movement2(state, rows, columns, moves,currentplayerwhite);
			}
		}
			
		else if (columns >= 6){
			Movement2(state, rows, columns, moves,currentplayerwhite);
			Movement5(state, rows, columns, moves,currentplayerwhite);
			if (columns == 6){
				Movement1(state, rows, columns, moves,currentplayerwhite);
			}
		}
		else
		{
			Movement5(state, rows, columns, moves,currentplayerwhite);
			Movement2(state, rows, columns, moves,currentplayerwhite);
			Movement1(state, rows, columns, moves,currentplayerwhite);
			Movement7(state, rows, columns, moves,currentplayerwhite);
		}
	}
	
	//Movement when the row is greater and equal to 6 
	else if (rows >= 6)														
	{
		if (columns <= 1){
			Movement3(state, rows, columns, moves,currentplayerwhite);
			Movement8(state, rows, columns, moves,currentplayerwhite);
			if (columns == 1){
				Movement4(state, rows, columns, moves,currentplayerwhite);
			}
		}
		
		else if (columns >= 6){
			Movement4(state, rows, columns, moves,currentplayerwhite);
			Movement6(state, rows, columns, moves,currentplayerwhite);
			if (columns == 6){
				Movement3(state, rows, columns, moves,currentplayerwhite);
			}
		}
		else																	 
		{
			Movement6(state, rows, columns, moves,currentplayerwhite);
			Movement4(state, rows, columns, moves,currentplayerwhite);
			Movement3(state, rows, columns, moves,currentplayerwhite);
			Movement8(state, rows, columns, moves,currentplayerwhite);
		}
	}
	//Movements of knight in mid of table
	else
	{
		if (columns <= 1)
		{
			Movement3(state, rows, columns, moves,currentplayerwhite);
			Movement8(state, rows, columns, moves,currentplayerwhite);
			Movement7(state, rows, columns, moves,currentplayerwhite);
			Movement1(state, rows, columns, moves,currentplayerwhite);
			if (columns == 1)
			{
				Movement4(state, rows, columns, moves,currentplayerwhite);
				Movement2(state, rows, columns, moves,currentplayerwhite);
			}
		}
		else if (columns >= 6)
		{
			Movement4(state, rows, columns, moves,currentplayerwhite);
			Movement6(state, rows, columns, moves,currentplayerwhite);
			Movement5(state, rows, columns, moves,currentplayerwhite);
			Movement2(state, rows, columns, moves,currentplayerwhite);
			if (columns == 6)
			{
				Movement3(state, rows, columns, moves,currentplayerwhite);
				Movement1(state, rows, columns, moves,currentplayerwhite);
			}
		}
		else																			 
		{
			Movement6(state, rows, columns, moves,currentplayerwhite);
			Movement4(state, rows, columns, moves,currentplayerwhite);
			Movement3(state, rows, columns, moves,currentplayerwhite);
			Movement8(state, rows, columns, moves,currentplayerwhite);
			Movement7(state, rows, columns, moves,currentplayerwhite);
			Movement1(state, rows, columns, moves,currentplayerwhite);
			Movement2(state, rows, columns, moves,currentplayerwhite);
			Movement5(state, rows, columns, moves,currentplayerwhite);
		}
	}
}
boolean check_mate(chessState chess_state, int player) {
	
	boolean move;
	int Pawn;
	int Knight;
	int Bishop;
	int Rook;
	int Queen;
	int winnerking;
	int King;
	int temp_king1 = -1;    
	int temp_king2 = 0 ;        

	//when black's turn white pieces are the enemy 
	if (player == -1) {
		Pawn = 1;
		Knight = 2;
		Bishop = 3;
		Rook = 4;
		Queen = 5;
		winnerking = -6;
		King = 6;
	}
	
	//when white's turn black pieces are the enemy
	
	else 
	{
		Pawn = -1;
		Knight = -2;
		Bishop = -3;
		Rook = -4;
		Queen = -5;
		winnerking = 6;
		King = -6;
	}

	
	
	for (int i = 0; i < 8; ++i) {
		for (int j = 0; j < 8; ++j) {
			if (chess_state.state[i][j] == winnerking) {
				temp_king1 = i;
				temp_king2 = j;
				break;
			}
		}
	}

	if(temp_king1 == -1) {
		return true;
	}

	// For Pawn
	//if (temp_king2 != 0 && temp_king2 != 7 && temp_king1 != 7)
	{
		if (Pawn == 1 && (chess_state.state[temp_king1 + 1][temp_king2 - 1] == Pawn 
		|| chess_state.state[temp_king1 + 1][temp_king2 + 1] == Pawn)) {
			return true;
		}
		if (Pawn == -1 && (chess_state.state[temp_king1 - 1][temp_king2 - 1] == 
			Pawn || chess_state.state[temp_king1 - 1][temp_king2 + 1] == Pawn)) {
			return true;
		}
	}
	
	//if (temp_king1 >= 2 && temp_king1 <= 5 && temp_king2 >= 2 && temp_king2 <= 5)
	{
		if (chess_state.state[temp_king1 - 1][temp_king2 - 2] == Knight ||
			chess_state.state[temp_king1 - 2][temp_king2 - 1] == Knight || 
			chess_state.state[temp_king1 - 2][temp_king2 + 1] == Knight ||
			chess_state.state[temp_king1 - 1][temp_king2 + 2] == Knight ||
			chess_state.state[temp_king1 + 1][temp_king2 - 2] == Knight ||
			chess_state.state[temp_king1 + 2][temp_king2 - 1] == Knight ||
			chess_state.state[temp_king1 + 2][temp_king2 + 1] == Knight || 
			chess_state.state[temp_king1 + 1][temp_king2 + 2] == Knight) {
			return true;
		}
	}

	// For Enemy King
	if (temp_king1 > 0 && temp_king1 < 7)
	{
		if (temp_king2 > 0 && temp_king2 < 7)
		{
			if (chess_state.state[temp_king1 - 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2] == King 
				|| chess_state.state[temp_king1 + 1][temp_king2] == King)
			{
				return true;
			}
		}
		else if(temp_king2 == 0)
		{
			if (chess_state.state[temp_king1 - 1][temp_king2] == King
				|| chess_state.state[temp_king1 - 1][temp_king2 + 1] == King 
				|| chess_state.state[temp_king1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2] == King
				|| chess_state.state[temp_king1 + 1][temp_king2] == King)
			{
				return true;
			}
		}
		else if (temp_king2 == 7)
		{
			if (chess_state.state[temp_king1 - 1][temp_king2 - 1] == King 
				|| chess_state.state[temp_king1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2] == King
				|| chess_state.state[temp_king1 + 1][temp_king2] == King)
			{
				return true;
			}
		}
	}
	else if (temp_king1 == 0)
	{
		if (temp_king2 > 0 && temp_king2 < 7)
		{
			if (chess_state.state[temp_king1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2] == King)
			{
				return true;
			}
		}
		else if (temp_king2 == 0)
		{
			if (chess_state.state[temp_king1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2] == King)
			{
				return true;
			}
		}
		else if (temp_king2 == 7)
		{
			if (chess_state.state[temp_king1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 + 1][temp_king2] == King)
			{
				return true;
			}
		}
	}
	else if (temp_king1 == 7)
	{
		if (temp_king2 > 0 && temp_king2 < 7)
		{
			if (chess_state.state[temp_king1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2] == King)
			{
				return true;
			}
		}
		else if (temp_king2 == 0)
		{
			if (chess_state.state[temp_king1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2 + 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2] == King)
			{
				return true;
			}
		}
		else if (temp_king2 == 7)
		{
			if (chess_state.state[temp_king1 - 1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1][temp_king2 - 1] == King
				|| chess_state.state[temp_king1 - 1][temp_king2] == King)
			{
				return true;
			}
		}
	}
	


	// For Rook and Queen
	move = false;
	for (int i = temp_king1 + 1; !move && i < 8; ++i) {
		if (chess_state.state[i][temp_king2] == Rook
			|| chess_state.state[i][temp_king2] == Queen) {
			return true;
		}
		else if (chess_state.state[i][temp_king2] != 0) {
			
			move = true;
		}
	}

	move = false;

	for (int i = temp_king1 - 1; !move && i >= 0; --i) {
		if (chess_state.state[i][temp_king2] == Rook
			|| chess_state.state[i][temp_king2] == Queen) {
			return true;
		}
		else if (chess_state.state[i][temp_king2] != 0) {
			move = true;
		}
	}


	move = false;

	for (int j = temp_king2 - 1; !move && j >= 0; --j) {
		if (chess_state.state[temp_king1][j] == Rook 
			|| chess_state.state[temp_king1][j] == Queen) {
			return true;
		}
		else if (chess_state.state[temp_king1][j] != 0) {
			
			move = true;
		}
	}


	move = false;
	for (int j = temp_king2 + 1; !move && j < 8; ++j) {
		if (chess_state.state[temp_king1][j] == Rook 
			|| chess_state.state[temp_king1][j] == Queen) {
			return true;
		}
		else if (chess_state.state[temp_king1][j] != 0) {
			move = true;
		}
	}

	// For Bishop and Queen
	
	move = false;
	for (int i = 1; !move && temp_king1 + i < 8 && temp_king2 + i < 8; ++i) {
		if (chess_state.state[temp_king1 + i][temp_king2 + i] == Bishop
			|| chess_state.state[temp_king1 + i][temp_king2 + i] == Queen) {
			return true;
		}
		else if (chess_state.state[temp_king1 + i][temp_king2 + i] != 0) {
			move = true;
		}
	}

	move = false;
	for (int i = 1; !move && temp_king1 - i >= 0 && temp_king2 - i >= 0; ++i) {
		if (chess_state.state[temp_king1 - i][temp_king2 - i] == Bishop
			|| chess_state.state[temp_king1 - i][temp_king2 - i] == Queen) {
			return true;
		}
		else if (chess_state.state[temp_king1 - i][temp_king2 - i] != 0) {
			move = true;
		}
	}

	move = false;
	//int j = 1;
	for (int i = 1, j = 1; !move && temp_king1 + i < 8 && temp_king2 - j >= 0; ++i, ++j) {
		if (chess_state.state[temp_king1 + i][temp_king2 - j] == Bishop
			|| chess_state.state[temp_king1 + i][temp_king2 - j] == Queen) {
			return true;
		}
		else if (chess_state.state[temp_king1 + i][temp_king2 - j] != 0) {
			move = true;
		}
	}

	move = false;
	for (int i = 1, j = 1; !move && temp_king1 - i >= 0 && temp_king2 + j < 8; ++i, ++j) {
		if (chess_state.state[temp_king1 - i][temp_king2 + i] == Bishop
			|| chess_state.state[temp_king1 - i][temp_king2 + i] == Queen) {
			return true;
		}
		else if (chess_state.state[temp_king1 - i][temp_king2 + i] != 0) {
			move = true;
		}
	}
	return false;
}












}