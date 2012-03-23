/**
  *@Author : Akhilesh Godi (CS10B037)
  *Assignment 7 - Tic Tac Toe
  *CS 2810 - Advanced Programming Lab
  */
package cse.iitm.cs10b037.tictactoe;

//This implementation makes the moves at random
public class TicTacToeEasy implements BotInterface
{

		private int row, col;
	    private TicTacToeBoard temp;
	    private static int N1;
	    
	    // Makes a new AI with access to the original game board
	    public TicTacToeEasy(TicTacToeBoard newboard)
	    {
	        temp = newboard;
	        TicTacToeEasy.setN1(TicTacToeBoard.N);
	    }
	    
	    //Continues to guess a move until it gets a valid spot.
	    public void getNextMove()
	    {
	        this.row = (int)(Math.random() * N1);
	        this.col = (int) (Math.random() * N1);
	        while (temp.canMove(row, col) != true)
	        {
	            this.row = (int)(Math.random() * N1);
	            this.col = (int) (Math.random() * N1);
	        }
	    }
	    
	    /* Calls getNextMove then makes the move*/
	    public void makeNextMove()
	    {
	    	if(!temp.isFilled())
	    	{
	    		this.getNextMove();
	    		//temp.markXO(row, col);
	    		System.out.println(row+" " + col);
	    
	    	}
	    }

		//Getter and setter
	    public static int getN1() 
		{
			return N1;
		}

		public static void setN1(int n1) {
			N1 = n1;
		}

		@Override
		public int getRow() 
		{
			// TODO Auto-generated method stub
			return this.row;
		}

		@Override
		public int getColumn() {
			// TODO Auto-generated method stub
			return this.col;
		}
	    
	}


