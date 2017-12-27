/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chessplayer;

/**
 *
 * @author mubasher.baig
 */
abstract class ChessPlayer {
    
	ChessPlayer()
	{
		
	}
	
    public abstract chessMove decideMoveUsingMinimax(int game_State [][], int MaxDepth, int Player);
    public abstract int Maximizer(chessState chess_state, int alpha, int beta, int depth);
	public abstract int Minimizer(chessState gameState, int alpha, int beta, int depth);
    
}

