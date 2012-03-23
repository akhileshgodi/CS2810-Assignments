/**
  *@Author : Akhilesh Godi (CS10B037)
  *Assignment 7 - Tic Tac Toe
  *CS 2810 - Advanced Programming Lab
  */
package cse.iitm.cs10b037.tictactoe;

public class Turn 
{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		javax.swing.SwingUtilities.invokeLater
		( 
			new Runnable() 
			{
				@Override
				public void run() 
				{
					TicTacToeBoard b = new TicTacToeBoard( 3 , 0);	//By default 3X3. and 2 Player
					b.printBoard();
					TicTacToeWindow win = new TicTacToeWindow(3 , 0); // By Default 3X3 and 2 Player
					win.GUI();
				}
			}
		);
	}

}


