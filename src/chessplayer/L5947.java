/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessplayer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author mubasher.baig
 */

public class L5947 extends ChessPlayer{
	//stores the given depth for the solution
	int depth;
	//Stores the best move on a given depth
	chessMove best_move = new chessMove();
	//this stores the game state of the chess
	chessState chess_state = new chessState();
	//this stores the move index
	int move;
	//This is used to call the evaluator
	private final BoardEvaluator boardevaluator = new BoardEvaluator();
	
	//constructor
	L5947(){
		
	}
	
	//overloaded constructor
	L5947(int Maxdepth){
		this.depth = Maxdepth;
	}
	
	
int moveToMake() {
	
	return Maximizer(chess_state, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, depth);
		
	}

	
	
	
	//it is a given function
	@Override
	public chessMove decideMoveUsingMinimax(int gameState[][], int MaxDepth, int Player){
		int selectedmove;
		L5947 obj = new L5947(MaxDepth);
		
		//here I am copying the chess board into chess state
		obj.chess_state.state = Arrays.copyOf(gameState, gameState.length);
		//System.out.println(obj.chess_state.state[2][2]);
		obj.chess_state.player = Player;
		//what is select?
		selectedmove = obj.moveToMake();
		return best_move;
    }
   
	
	//This is maximizer function and used as in co-recursion relation with minimizer
   @Override
   public int Maximizer(chessState chess_state, int alpha, int beta, int depth) {
		
	   
	   if (depth == 0) {
		  int value  = boardevaluator.evaluate(chess_state, chess_state.player);
   		return value;
		  
	   
	   }
	   
	   int value = Integer.MIN_VALUE;
	   chessMove bestMove = null;
	   int move = Integer.MIN_VALUE;
	   ArrayList<chessMove> temp = new ArrayList<chessMove>();
	   int count = chess_state.getLegalMoveList();
	   temp = chess_state.final_moves;
	   
	   for (int i = 0; i < count; ++i)
		{
			chessState nextstate = chess_state;
			nextstate.makeMove(temp.get(i));
			int minimumValue = Minimizer(nextstate, alpha, beta, depth - 1);
			
			if (value < minimumValue) {
				value = minimumValue;
				bestMove = temp.get(i);
				move = i;
			}
			
			if(alpha < value)
			{
				alpha = value;
			}
		
			if (beta <= alpha) {
				break;
			}

		}
			
	   	this.best_move = bestMove;
		this.move = move;
		return value;	
}

	@Override
	public int Minimizer(chessState gameState, int alpha, int beta, int depth) {
		// TODO Auto-generated method stub
		
		if (depth == 0) {
	   		//int value  = boardevaluator.evaluate(chess_State, chess_State.player);
	   		//return value;
		   }
		   
		   int value = Integer.MAX_VALUE;
		   chessMove bestMove = null;
		   int move = Integer.MAX_VALUE;
		   ArrayList<chessMove> temp = new ArrayList<chessMove>();
		   int count = chess_state.getLegalMoveList();
		   temp = chess_state.final_moves;
		   
		   for (int i = 0; i < count; ++i)
			{
				chessState nextstate = chess_state;
				int maximumvalue = Maximizer(nextstate, alpha, beta, depth - 1);
				if (value > maximumvalue) {
					value = maximumvalue;
					bestMove = temp.get(i);
					move = i;
				}
				
				if(beta < value)
				{
					beta = value;
				}
			
				if (beta <= alpha) {
					break;
				}

			}
				
		   	this.best_move = bestMove;
			this.move = move;
			return value;	
	
	}
   
   
              
}
