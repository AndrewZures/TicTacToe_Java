
public class Game {
	private int[][] board; // game board
	private int[] choice = new int[2];
	
	public Game(){
		
		board = createGameBoard();
	}
	
	public int[][] createGameBoard(){
		
		/*create two-D game board */
		int[][] newBoard = new int[3][3];
		
		//populate empty board, using 0 for empty
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++){
				newBoard[i][j] = 0;
			}
		}
		
		return newBoard;	
	}
	
	public void recordUserChoice(int i, int j, int player){
		//record user's choice by putting their mark at i,j coord
		board[i][j] = player; 
	}
	
	public int[][] getBoard(){
		return board;
	}
	
	public void resetBoard(){
		//reset board, used mainly after previous game ended
		
		//iterate through board, setting each element to 0
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				board[i][j] = 0;
			}
		}
	}
	
	public int checkGameState(int[][] board){
		/*NOTE:  This method is the same as the checkGameState method in the ComputerAI class. 
		 * This is because the game, and not the computerAI, should determine 
		 * the winner of the game
		 */
		
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
	
	public int[] convertButtonToIndex(int buttonNum){
		/*convert button number to i,j index numbers */
		
		int i = 0, j = 0;
		
		switch (buttonNum){
			case 1: i = 0; j = 0; break;
			case 2: i = 0; j = 1; break;
			case 3: i = 0; j = 2; break;
			case 4: i = 1; j = 0; break;
			case 5: i = 1; j = 1; break;
			case 6: i = 1; j = 2; break;
			case 7: i = 2; j = 0; break;
			case 8: i = 2; j = 1; break;
			case 9: i = 2; j = 2; break;
		}
		
		choice[0] = i;
		choice[1] = j;
		
		return choice;
		
	}
	
	public int convertIndexToButtonNum(int i, int j){
		
		//convert i,j index to related button number
		//since there are two variables, a switch statement is not possible
		//multiple if else statements in lieu of switch

		if (i == 0){
			if (j == 0){ return 0; }
			else if (j == 1){ return 1; }
			else { return 2; }
		}
		else if (i == 1){
			if (j == 0){ return 3; }
			else if (j == 1){ return 4; }
			else { return 5;	}
		}
		else {
			if (j == 0){	return 6; }
			else if (j == 1){ return 7; }
			else { return 8;	}
		}

	} //end convertIndexToButtonNum
	
} //end of Game class
