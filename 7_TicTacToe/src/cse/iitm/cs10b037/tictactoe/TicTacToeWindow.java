/**
  *@Author : Akhilesh Godi (CS10B037)
  *Assignment 7 - Tic Tac Toe
  *CS 2810 - Advanced Programming Lab
  */
package cse.iitm.cs10b037.tictactoe;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class TicTacToeWindow 
{
	private TicTacToeBoard board;		//This is the board
	boolean[][] revealed;		//Array of the positions of X and O that have already been revealed
	JFrame mainFrame;			
	JPanel gamePanel;
	JPanel scorePanel;
	
	public static JButton[][] gameButtons;
	JPanel leftPanel;
	JPanel rightPanel;
	
	JLabel modeOfBoard;
	JLabel sizeLabel;
	JLabel statusLabel;
	JLabel space;
	
	JLabel scoreLabel;
	JLabel Player1ScoreLabel;
	JLabel Player2ScoreLabel;
	JLabel BotScoreLabel;
	Container mainPane;
	private static int modeInWindow = 0;		//Initial Conditions
	private static int sizeInWindow = 3;		//Initial Conditions
	//TODO: Player Scores
	private static int player1Score = 0;
	private static int player2Score = 0;
	private static int botScore = 0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	
	boolean wonFlag = false;
	TicTacToeWindow (TicTacToeBoard b)
	{
		board = new TicTacToeBoard(TicTacToeBoard.N ,0);
	}
	
	TicTacToeWindow (int N,int mode)
	{
		board = new TicTacToeBoard (TicTacToeBoard.N, mode);
	}
	
	public void GUI ()
	{
		//GUI Design adapted from Minesweeper Code by Arun Chaganty.
		installLnF();
		mainFrame = new JFrame ("Tic Tac Toe");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setMinimumSize(new Dimension(800, 400));
		
		mainPane = mainFrame.getContentPane();
		

		// Create a menu bar
		JMenuBar mBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		
		// Create new game
		JMenuItem newItem = fileMenu.add("New Game");
		newItem.addActionListener(new ActionListener() {
			@Override 
		public void actionPerformed(ActionEvent arg0) {newGame();}});
		
			
		// Quit
		JMenuItem quitItem = fileMenu.add("Quit");
		quitItem.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.dispose();
			}
		});
		
		//Help
		JMenuItem helpItem = helpMenu.add("How to play");
		helpItem.addActionListener(new ActionListener() {
			@Override 
		public void actionPerformed(ActionEvent arg0) {OpenPageInDefaultBrowser();}});
		
		JMenuItem aboutItem = helpMenu.add("About");
		
		aboutItem.addActionListener(new ActionListener() {
			@Override 
		public void actionPerformed(ActionEvent arg0) {JOptionPane.showMessageDialog(mainFrame, "This game was developed as a part of an Advanced Proramming Lab Course to learn GUI. \nCredits : \nAkhilesh Godi (CS10B037)\nUndergraduate II Year\nDepartment of Computer Science and Engineering.\nIIT Madras.");
		}});
		
		mBar.add(fileMenu);
		mBar.add(helpMenu);

		mainPane.add(mBar, BorderLayout.PAGE_START);

		// Create status bar
		statusLabel = new JLabel("Start a new game");
		mainPane.add(statusLabel, BorderLayout.PAGE_END);

	
		String modeArray[] = { "Player1 V/S Player2","Player V/S Computer (Easy)","Player V/S Computer(Medium)","Player V/S Computer(Difficult)"};
		final JComboBox modeBox= new JComboBox(modeArray);
		modeBox.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
				modeInWindow = (modeBox.getSelectedIndex());
				
			}
		});
		
		String sizeArray[] = { "3","4","5","6","7","8"};
		final JComboBox sizeBox= new JComboBox(sizeArray);
		sizeBox.getSelectedItem();
		sizeBox.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				// TODO Auto-generated method stub
				
				sizeInWindow = (sizeBox.getSelectedIndex()+3);
				
			}
		});
		
		leftPanel = new JPanel(new GridLayout(10,1,5,12));
		modeOfBoard = new JLabel("Select the Mode.");
		JLabel sizeOfBoard = new JLabel("Select the size of board.");
		leftPanel.add(modeOfBoard);
		leftPanel.add(modeBox,BorderLayout.WEST);
		space = new JLabel("");
		
		leftPanel.add(sizeOfBoard);
		leftPanel.add(sizeBox);
		leftPanel.add(space);
		JButton Reset = new JButton("Start New Game");
		Reset.addActionListener(new ActionListener() {
			@Override 
		public void actionPerformed(ActionEvent arg0) {newGame();}}); 
		leftPanel.add(Reset);
		JButton resetScores = new JButton("Reset Scores");
		resetScores.addActionListener(new ActionListener() {
			@Override 
		public void actionPerformed(ActionEvent arg0) {resetScores();}});
		//leftPanel.add(space);
		leftPanel.add(resetScores);
		

		
		
		rightPanel = new JPanel(new GridLayout(6, 1, 0, 3 ));
		rightPanel.setBackground(Color.pink);
		//ImageIcon scoreImg = new ImageIcon("score.jpeg");
		
		//(scoreImg, BorderLayout.BEFORE_LINE_BEGINS);
		
		
		scoreLabel = new JLabel("SCORES:    ");
		rightPanel.add(scoreLabel);
		
		Player1ScoreLabel = new JLabel("Player1  :    " + player1Score + "            			");
		rightPanel.add(Player1ScoreLabel);
		
		Player2ScoreLabel = new JLabel("Player2  :    " + player2Score + "           		 	");
		BotScoreLabel =     new JLabel("Computer :    " + botScore+ "              				 ");
		
		resetScorePanel();
		mainPane.add(rightPanel,BorderLayout.EAST);
		
		gamePanel = new JPanel(new GridLayout(TicTacToeBoard.getN(), TicTacToeBoard.getN()));
		mainPane.add(gamePanel, BorderLayout.CENTER);
		
		mainPane.add(leftPanel,BorderLayout.WEST);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}

	private void resetScorePanel()
	{
		rightPanel.remove(Player1ScoreLabel);
		rightPanel.remove(Player2ScoreLabel);
		rightPanel.remove(BotScoreLabel);
		Player1ScoreLabel = new JLabel("Player1  :    " + player1Score + "                ");
		rightPanel.add(Player1ScoreLabel);
	
		Player2ScoreLabel = new JLabel("Player2  :    " + player2Score + "                ");
		BotScoreLabel =     new JLabel("Computer :    " + botScore+ "             ");
		if(TicTacToeBoard.mode == 0)
			rightPanel.add(Player2ScoreLabel);
		else
			rightPanel.add(BotScoreLabel);
		mainFrame.pack();
	}
	
	private void resetScores()
	{
		player1Score = 0;
		player2Score = 0;
		botScore = 0;
		resetScorePanel();
	}

	private void newGame() 
	{
		// Create an NxN grid layout
		TicTacToeBoard.setMode(modeInWindow);
		TicTacToeBoard.setN(sizeInWindow);
		
		resetScorePanel();
		if(TicTacToeBoard.mode == 0)
		{	
			BotScoreLabel.setVisible(false);
			rightPanel.add(Player2ScoreLabel);
			Player2ScoreLabel.setVisible(true);
		}
		else
		{
			Player2ScoreLabel.setVisible(false);
			rightPanel.add(BotScoreLabel);
			BotScoreLabel.setVisible(true);
		}
		wonFlag = false;
		revealed = new boolean[TicTacToeBoard.getN()][TicTacToeBoard.getN()];
		board = new TicTacToeBoard(TicTacToeBoard.getN(),TicTacToeBoard.mode);
		gamePanel.setLayout(new GridLayout(TicTacToeBoard.getN(), TicTacToeBoard.getN()));
		mainFrame.pack();
		for (int i = 0; i < TicTacToeBoard.getN(); i++)
		{
			for (int j = 0; j < TicTacToeBoard.getN(); j++)
			{
				revealed[i][j] = false;
			}
		}	
		
		board.clearBoard();
		
		// Remove everything in the panel, and reset it
		gamePanel.removeAll();
		gameButtons = new JButton[TicTacToeBoard.getN()][TicTacToeBoard.getN()];

		for (int i = 0; i < TicTacToeBoard.getN(); i++) 
		{	
			for (int j = 0; j < TicTacToeBoard.getN(); j++) 
			{
				final int mi = i;
				final int mj = j;
				gameButtons[i][j] = new JButton("");
				
				gameButtons[i][j].addMouseListener( new MouseListener() 
				{
					
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						
						// TODO Auto-generated method stub
						if ( arg0.getButton() == MouseEvent.BUTTON1 && wonFlag == false)
						{
							
							if(TicTacToeBoard.mode == 0 || ((TicTacToeBoard.mode==1 || TicTacToeBoard.mode==2 ||  TicTacToeBoard.mode==3 ) && board.getTurn()==true))
							{
								//System.out.println( coords );
								putXO(mi, mj);
								board.printBoard();
								updateStatus(board.turn);
							}
							
							//Check if Mode is 1. i.e Player v/s Computer
							if( (TicTacToeBoard.mode == 1 || TicTacToeBoard.mode == 2 || TicTacToeBoard.mode == 3)   
									&& board.turn == false && wonFlag == false)
							{
								//System.out.println("BLAH");
								
								board.makeBotMove();
								putXO(board.getBotRow(),board.getBotColumn());	
								updateStatus(board.turn);
								board.printBoard();
							}
						}
					}

					@Override
					public void mouseEntered(MouseEvent arg0) 
					{
					}
							@Override
					public void mouseExited(MouseEvent arg0) 
					{
					}

					@Override
					public void mousePressed(MouseEvent arg0) 
					{
					}

					@Override
					public void mouseReleased(MouseEvent arg0) 
					{
					}
					
				});
						
			gamePanel.add(gameButtons[i][j]);
			}
		}
			
		if(board.isFilled() == true && board.Winner() == -1)
			statusLabel.setText(String.format("DRAW."));
			mainFrame.pack();
	}
	
	
	private void putXO(int i, int j) 
	{
		// First check if the square has already been revealed.
		if (revealed[i][j])
			return;
		else 
		{
			if(board.getTurn() == true)
			{
				//gameButtons[i][j].setBackground( Color.green );
				ImageIcon XImage = new ImageIcon("X.jpeg");
				gameButtons[i][j].setIcon(XImage);
				gameButtons[i][j].setDisabledIcon(XImage);
			}
			else
			{	
				//gameButtons[i][j].setBackground( Color.yellow );
				ImageIcon XImage = new ImageIcon("O.jpeg");
				gameButtons[i][j].setIcon(XImage);
				gameButtons[i][j].setDisabledIcon(XImage);
			}	
			revealed[i][j] = true;
			board.markXO(i, j);
			//gameButtons[i][j].setText(String.valueOf(board.get(i, j)));
			
			gameButtons[i][j].setEnabled(false);
		}
	}
	
	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}
	
	private void disableBoard()
	{
		for(int i = 0 ; i < TicTacToeBoard.N;i++)
			for(int j = 0 ; j < TicTacToeBoard.N;j++)
				gameButtons[i][j].setEnabled(false);
	}
	private void updateStatus(boolean turn) 
	{
		
		if (turn == true && wonFlag == false)
		{
			statusLabel.setText(String.format("Player 1's turn."));
		} 
		else if(turn == false && wonFlag == false)
		{
			if(TicTacToeBoard.mode == 0)
				statusLabel.setText(String.format("Player 2's turn."));
			
		}
		
		int checkIfWon = board.Winner();
		
		if (checkIfWon == 1  && wonFlag == false)
		{		
			statusLabel.setText(String.format("Player 1 Won. Congratulations! :)"));
			JOptionPane.showMessageDialog(mainFrame, "Player 1 won the game");
			player1Score++;
			wonFlag = true;
			disableBoard();
			resetScorePanel();
		}
		else if (checkIfWon == 2 && wonFlag == false)
		{
			wonFlag = true;
			if(TicTacToeBoard.mode == 0)
			{
				statusLabel.setText(String.format("Player 2 Won. Congratulations"));
				JOptionPane.showMessageDialog(mainFrame, "Player 2 won the game");
				++player2Score;
				disableBoard();
				//System.out.println("Player 2 Won " + player2Score);
				resetScorePanel();
			}
			
			else
			{
				statusLabel.setText(String.format("Computer Won :D"));
				JOptionPane.showMessageDialog(mainFrame, "Computer won the game");
				botScore++;
				disableBoard();
				resetScorePanel();
			}	
		}
		if(board.isFilled() == true && (board.Winner() == -1 || board.Winner()== 0))
		{
			statusLabel.setText(String.format("DRAW."));
			JOptionPane.showMessageDialog(mainFrame, "The game is a draw");
			
		}
	}
	
	public void OpenPageInDefaultBrowser ()
	{
		try {
				 String url = "http://en.wikipedia.org/wiki/Tic-tac-toe";
		         java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
		       }
		       catch (java.io.IOException e) {
		           System.out.println(e.getMessage());
		       }
	}	   
		


}	
					
