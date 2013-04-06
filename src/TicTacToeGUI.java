
//imports are standard Window Builder Imports
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class TicTacToeGUI implements ActionListener {

	
	private JFrame frame;  //create frame
	private Game game = new Game();  //instantiate game
	private ComputerAI art = new ComputerAI();  //instantiate computerAI
	
	private ArrayList<JButton> buttonList;  //create list of TTT GUI buttons
	private final int USER = -1; //create constant USER with -1 value
	
	private int[] choice = new int[2];  //helper array records i,j coord for given move
	private int winner; //help int keeps track of winner
	
	//TTT buttons
	private JButton gameButton1;
	private JButton gameButton2;
	private JButton gameButton3;
	private JButton gameButton4;
	private JButton gameButton5;
	private JButton gameButton6;
	private JButton gameButton7;
	private JButton gameButton8;
	private JButton gameButton9;
	private JButton resetGameButton;
	
	//other game buttons
	private JLabel resultLabel;
	private JLabel titleLabel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToeGUI window = new TicTacToeGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicTacToeGUI() {
		initialize();
	}

	public void actionPerformed(ActionEvent e) {
		//listens for action performed on the GUI


		//if event source is resetGameButton, reset game
		if (e.getSource() == resetGameButton){
			
			//iterate over buttonList, reseting all buttons
			for(int j = 0; j < buttonList.size(); j++){
				buttonList.get(j).setEnabled(true);
				buttonList.get(j).setText("");	
			}
			
			//reset other labels and buttons 
			resultLabel.setVisible(false);
			resetGameButton.setVisible(false);
			
			//reset the game itself
			game.resetBoard();
		}
		
		else {
		
			/*check to see if event came from a button and record
			that button's index in the buttonList array*/
			int i = 0;
		    for(; i < buttonList.size(); i++){
		    		if(e.getSource() == buttonList.get(i)){
		    			break;
		    		}
		    }
		    
		    //re-check to make sure for-loop didn't complete without match
		    if (e.getSource() == buttonList.get(i)){
		    		
		    		//execute the user's button choice
		    		executeUserChoice(i);
		    		
		    		/*since the user's turn is now up, execute the computer's choice */
		    		executeComputerChoice();
		    }
		}  //end of else statement
	    
		   
	} //end of actionPerformed
	
	private void executeUserChoice(int buttonNum){
		
		//disabled chosen button and update text to correct symbol
		buttonList.get(buttonNum).setEnabled(false);
	    buttonList.get(buttonNum).setText("<html><font color='blue'>O</html>");
	    
	    //convert button number to i,j coordinates needed by game class
	    //record user's choice
	    choice = game.convertButtonToIndex(buttonNum+1);
	    game.recordUserChoice(choice[0], choice[1], USER);
	    
	    //check if there is now a winner
	    winner = game.checkGameState(game.getBoard());
	    if (winner != 2){
	    		getGameWinner(winner);
	    }
		
	}
	
	private void executeComputerChoice(){
	    
		//get the AI's choice and record it
	    choice = art.determineBestMove(game.getBoard());
		game.recordUserChoice(choice[0], choice[1], art.getMe());
		
		//convert the game's i,j coordinates to GUI button number
		int buttonNum = 0;
		buttonNum = game.convertIndexToButtonNum(choice[0], choice[1]);
		
		//disable button and update with correct symbol
		buttonList.get(buttonNum).setEnabled(false);
		buttonList.get(buttonNum).setText("<html><font color='red'>X</html>");
		
		//check to see if there is a winner
		int winner = game.checkGameState(game.getBoard());
	    if (winner != 2){
	    		getGameWinner(winner);
	    }
	}
	
	private void getGameWinner(int winner) {
		/*since a winner has already been decided this method will
		 handle the win */
		
		//disable all buttons
		for(int i = 0; i < buttonList.size(); i++){
			buttonList.get(i).setEnabled(false);
		}
		
		//determine result label text and show result label
		//also show the resetGameButton
		resultLabel.setVisible(true);
		resetGameButton.setVisible(true);
		if (winner == 1){
			//if winner was computer
			resultLabel.setText("You Lose, Computer Wins");
		}
		else if (winner == -1){
			//if winner was user
			resultLabel.setText("You Win!!!");
		}
		else if (winner == 0){
			//if game resulted in draw
			resultLabel.setText("Draw");
		}
		else {
			//if something else happened
			resultLabel.setText("Error: " + winner);
		}
		
	}

	/* AJZ - below is windowbuilder content. much if it is generated by windowbuilder GUI*/
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 500, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//create buttonList Array
		buttonList = new ArrayList<JButton>();
		
		/*
		 * For EACH button, create it, add listener, and add to buttonList array
		 */
		gameButton1 = new JButton();
		gameButton1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton1.setBounds(150, 98, 144, 85);
		gameButton1.addActionListener(this);
		frame.getContentPane().add(gameButton1);
		buttonList.add(gameButton1);
		
		gameButton2 = new JButton();
		gameButton2.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton2.setBounds(306, 98, 144, 85);
		gameButton2.addActionListener(this);
		frame.getContentPane().add(gameButton2);
		buttonList.add(gameButton2);
		
		gameButton3 = new JButton();
		gameButton3.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton3.setBounds(462, 98, 144, 85);
		gameButton3.addActionListener(this);
		frame.getContentPane().add(gameButton3);
		buttonList.add(gameButton3);
		
		gameButton4 = new JButton();
		gameButton4.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton4.setBounds(150, 189, 144, 85);
		gameButton4.addActionListener(this);
		frame.getContentPane().add(gameButton4);
		buttonList.add(gameButton4);
		
		gameButton5 = new JButton();
		gameButton5.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton5.setBounds(306, 189, 144, 85);
		gameButton5.addActionListener(this);
		frame.getContentPane().add(gameButton5);
		buttonList.add(gameButton5);
		
		gameButton6 = new JButton();
		gameButton6.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton6.setBounds(462, 189, 144, 85);
		gameButton6.addActionListener(this);
		frame.getContentPane().add(gameButton6);
		buttonList.add(gameButton6);
		
		gameButton7 = new JButton();
		gameButton7.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton7.setBounds(150, 281, 144, 85);
		gameButton7.addActionListener(this);
		frame.getContentPane().add(gameButton7);
		buttonList.add(gameButton7);
		
		gameButton8 = new JButton();
		gameButton8.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton8.setBounds(306, 281, 144, 85);
		gameButton8.addActionListener(this);
		frame.getContentPane().add(gameButton8);
		buttonList.add(gameButton8);
		
		gameButton9 = new JButton();
		gameButton9.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		gameButton9.setBounds(462, 281, 144, 85);
		gameButton9.addActionListener(this);
		frame.getContentPane().add(gameButton9);
		buttonList.add(gameButton9);
		
		/*
		 *  Create Reset Button, Result Label, and Title Label
		 */
		
		resetGameButton = new JButton("Reset Game");
		resetGameButton.setBounds(323, 422, 117, 29);
		resetGameButton.addActionListener(this);  //reset button needs listener
		frame.getContentPane().add(resetGameButton);
		resetGameButton.setVisible(false);
		
		resultLabel = new JLabel();
		resultLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setBounds(150, 368, 458, 50);
		frame.getContentPane().add(resultLabel);
		
		titleLabel = new JLabel("Tic Tac Toe");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 29));
		titleLabel.setBounds(176, 36, 410, 50);
		frame.getContentPane().add(titleLabel);
		
	} //end of initialize
	
} //end of TicTacToeGUI class
