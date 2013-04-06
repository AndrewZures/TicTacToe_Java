
public class ComputerAI {
	private int me = 1;
	private int iCoordinate;
	private int jCoordinate;
	
	public int[] determineBestMove(int[][] board){
		/*since only one computer AI is needed, a constructor is 
		 * not needed.  however, if there were two AIs, one could
		 * create them with different me values
		 */
		
		int[] choice = new int[2];
		if(me == 1){
			getMax(board, 0);
		}
		/*if there were two AIs, the AI would look for the getMin
		thereby allowing two AIs to play against each other but
		using the same code */
		else {
			getMin(board, 0);
		}
		/*after getting min or max
		get chosen i,j coordinate for choice */
		choice[0] = iCoordinate;
		choice[1] = jCoordinate;
		
		//return i,j choice coordinates as best move
		return choice;
	}
	
	public int getMin(int[][] board, int depth){
		
		//check game state to determine if at end of recursion
		int val = checkGameState(board);
		if (val != 2) { return val;}
		
		int min = Integer.MAX_VALUE; //set arbitrarily large min value
		int score;
		
		//iterate through all possible options
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
				//determine if spot is open
				if(board[i][j] == 0){
					
					//if spot is open, temporarily choose it
					board[i][j] = -1;
					
					//recursively get score of that choice
					score = getMax(board, depth+1);
					
					/*if score is less than current min set
					  min to score*/
					if (score < min){
						min = score;
						
						/*if at top depth in recursion get i,j
						 coordinates */
						if (depth == 0){
							iCoordinate = i;
							jCoordinate = j;
						}
					}
					
					//remove temporary choice
					board[i][j] = 0;
				}
			} //end of inner loops
		} //end of outer for loop
		
		//return lowest avialable score
		return min;
	}
	
	public int getMax(int[][] board, int depth){
		
		//check game state to determine if at end of recursion
		int val = checkGameState(board);
		if (val != 2) { return val; } 
		
		
		int max = Integer.MIN_VALUE; //create arbitrarily small max;
		int score;
		
		//iterate over all options
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				
				//determine if spot is open
				if(board[i][j] == 0){
					
					//if spot is open, temporarily choose it
					board[i][j] = 1;
					
					//recursively get score of that choice
					score = getMin(board, depth+1);
	
					/*of that score is greater than current max
						set max to the score */
					if (score > max){
						max = score;
						
						/*if the recursion level is at its highest
						 * depth, get the i, j coordinate for max choice */ 
						if (depth == 0){
							iCoordinate = i;
							jCoordinate = j;
						}	
					}
					
					//remove temporary choice
					board[i][j] = 0;
				}
			} // end of inner for loop
		} //end of outer for loop
		
		//return the maximum score of available choices
		return max;
	}
		
	public int checkGameState(int[][] board){
		/*check status of game, namely if someone has won */
		
		//0 no winner, game is draw
		//1 is computer winner
		//-1 is human winner
		//2 no winner, continue game
		
		//check rows
		
		if(board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1){ return 1;}
		if(board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1){return 1;}
		if(board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1){return 1;}
		
		if(board[0][0] == -1 && board[0][1] == -1 && board[0][2] == -1){ return -1;}
		if(board[1][0] == -1 && board[1][1] == -1 && board[1][2] == -1){return -1;}
		if(board[2][0] == -1 && board[2][1] == -1 && board[2][2] == -1){return -1;}
				
		//check columns
		
		if(board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1){ return 1;}
		if(board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1){ return 1;}
		if(board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1){ return 1;}
		
		if(board[0][0] == -1 && board[1][0] == -1 && board[2][0] == -1){ return -1;}
		if(board[0][1] == -1 && board[1][1] == -1 && board[2][1] == -1){ return -1;}
		if(board[0][2] == -1 && board[1][2] == -1 && board[2][2] == -1){ return -1;}
		
		//check diagonal cases
		
		if(board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1){ return 1;}
		if(board[2][0] == 1 && board[1][1] == 1 && board[0][2] == 1){ return 1;}
		
		if(board[0][0] == -1 && board[1][1] == -1 && board[2][2] == -1){ return -1;}
		if(board[2][0] == -1 && board[1][1] == -1 && board[0][2] == -1){ return -1;}
		
		
		//check for full board, i.e. draw
		int count = 0;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				if(board[i][j] == 0){
					break;
				}
				else{
					count++;
				}
			}  //end inner loop
		} // end outer loop
		
		//if all squares are taken, game is draw
		if(count == 9){
			return 0;  //zero is code for draw
		}
		
		return 2;  //if non of above returned, return 2, meaning game still on, no winner
	}
	
	public int getMe(){
		//return AI number
		//useful when more than one AI
		return me;
	}

} //end of ComputerAI class
