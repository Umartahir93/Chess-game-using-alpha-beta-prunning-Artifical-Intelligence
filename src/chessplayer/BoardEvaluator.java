package chessplayer;
public class BoardEvaluator {

	public int evaluate(chessState chess_State, int player) {
		// TODO Auto-generated method stub
	
	//Here I have followed combination of different approaches
	//and calculated a aggregate score
	//First find the total score of the pieces for white and for the 
	//black
	
	//2nd found out how many pieces of black has crossed half of
	//the board
	
	//and how many pieces of white has crossed the half of the board
		
	//1st approach 	
	int black_score = 0;
	int white_score = 0;
	for(int i = 0 ; i < 8 ; i++)
	{
		for(int j = 0 ; j < 8 ; j++)
		{
			if(chess_State.state[i][j] <0)
			{
				black_score += chess_State.state[i][j];
			}
			else
				white_score += chess_State.state[i][j];
		}
	}
		
	//2nd approach
	int count_white = 0;
	int count_black = 0;
	for(int i = 3 ; i < 8 ; i++)
	{
		for(int j = 0 ; j < 8 ; j++)
		{
			if(chess_State.state[i][j] <0)
			{
				count_black++;
			}
			
		}
	}
	
	for(int i = 0 ; i < 4 ; i++)
	{
		for(int j = 0 ; j < 8 ; j++)
		{
			if(chess_State.state[i][j] >0)
			{
				count_white++;
			}
			
		}
	}
	
	
	//3rd approach here I followed is that if you have checkmated your player 
	//then you a very high score will be added to the aggregate because you are
	//more likely to win
	
	//Here I have used the hashmap. It first search the key which is index for
	//player if that key is present then it adds its corresponding score to the
	//player which deserves. Hashmap doesnt have duplicates by default so it wont 
	//add checkmate score twice
		
	int check_score_white = 0;
		int check_score_black = 0;
	if(chess_State.check_evaluation_score.containsKey(1))
			check_score_white += chess_State.check_evaluation_score.get(1);
	else if(chess_State.check_evaluation_score.containsKey(-1))
		check_score_black += chess_State.check_evaluation_score.get(-1);
		
	
	int total_white = check_score_white +count_white*3 + white_score;
	//i am adding negative nos 
	int total_black = check_score_black -count_white*3 - white_score;
	
	total_black = total_black*-1;
	
	//compare absolute value of both the numbers and return the value of player which 
	//has greater absolute value
	//if the score you returned is negative then black is winning
	//otherwise white is winning
	
	if(total_black < total_white)
	{
		return total_white;
	}
	else
		return -1*total_black;

	
	}

}
