/**
  *@Author : Akhilesh Godi (CS10B037)
  *Assignment 7 - Tic Tac Toe
  *CS 2810 - Advanced Programming Lab
  */
package cse.iitm.cs10b037.tictactoe;

import java.util.Random;

//If it can win it will Win and if it can block it will block
public class TicTacToeHard implements BotInterface
{
	private int row, col; 
    private int botInput =2;
    private TicTacToeBoard copyOfBoard;
    private int N;
   
    public TicTacToeHard(TicTacToeBoard board)
    {
        copyOfBoard = board;
        this.N = TicTacToeBoard.getN();
    }
    
    //Gets the best possible move
    public void getNextMove()
    {
        int possibleMoves[][] = new int[N][N]; 
        //Attaching weights to each of the positions
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                possibleMoves[i][j] = attachWeights(i, j);
            }
        }
        
        //Selects the one with the best possible weightage. 
        //TODO: Write a better algo for finding minima! <Easy>
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (possibleMoves[row][col] < possibleMoves[i][j])
                {
                    row = i;
                    col = j;
                }
            }
        }
        
    }
    
    
    public int attachWeights(int rowPosition, int colPosition)
    {
        int weightage = 0;
        if (copyOfBoard.canMove(rowPosition, colPosition) != true)
            return -100;			//We cannot Move here
        if (isWinner(rowPosition, colPosition))
            weightage += 500;		//If it can Win it will do that first. Most weight to this.
        if (isBlocker(rowPosition, colPosition))
            weightage += 250;		//If it can block it will do that. 
        if (isCenter(rowPosition, colPosition))
            weightage += 100;		//If it can put it at the center. Do that first. Trap can be created.
        if (isTrap(rowPosition, colPosition))
            weightage += 50;		// TODO : Can we have other traps for bigger N?
        if (isCorner(rowPosition, colPosition))
            weightage += 10;		//Least weight to the corner
        Random randGen = new Random();
        return weightage + randGen.nextInt(30); 
    }
    
    private boolean isTrap(int rowPosition,int colPosition)
    {
    	boolean isSide = !isCorner(rowPosition, colPosition) && !isCenter(rowPosition, colPosition);
        int playerInput = 1;
        if (botInput == 1)
            playerInput = 2;
        int[][] temp = copyOfBoard.getCopyOfBoard();
        boolean cornerTrap = (temp[0][0] == playerInput && temp[N-1][N-1] == playerInput) || 
                           (temp[N-1][0] == playerInput && temp[0][N-1] == playerInput);
       
        //if(cornerTrap==true)
        //	System.out.println("TRAP");
        return isSide && cornerTrap;
    }
    
    private boolean isCenter(int rowPosition,int colPosition)
    {
        return( (rowPosition == N/2) && (colPosition == N/2));
    }
    
    //Useful in the case of 3X3. To check if it is at the corner.
    private boolean isCorner(int rowPosition, int colPosition)
    {
        boolean flag = false;
        if (rowPosition == 0 && (colPosition == 0 || colPosition == N-1))
            flag = true;
        if (rowPosition == N-1 && (colPosition == 0 || colPosition == N-1))
            flag = true;
        return flag;
    }
    
    //Checks if the Player can win.
    private boolean isBlocker(int rowPosition, int colPosition) 
    {
        int newbotInput = 1;
        if (botInput == 1) 
            newbotInput = 2;
        int[][] temp = copyOfBoard.getCopyOfBoard();
        temp[rowPosition][colPosition] = newbotInput;	
        return diagonalWinner(temp) || rowWinner(temp)|| colWinner(temp);
    }
    
  //-------------------------------------------------------------------------------//
    
    //Checks if there is a Winner
    private boolean isWinner(int rowPosition,int colPosition)
    {
        int temp[][] = copyOfBoard.getCopyOfBoard();
        temp[rowPosition][colPosition] = botInput;
        return diagonalWinner(temp) || rowWinner(temp)|| colWinner(temp);
    }
    
    //Checks Diagonals
    private boolean diagonalWinner(int gameBoard[][])
 	{
    	
 		boolean flag = true;
 		for(int i = 0 ; i < TicTacToeBoard.N-1 ; i++)
 		{
 			if(gameBoard[i][i]!=gameBoard[i+1][i+1] || gameBoard[i][i] == 0 || gameBoard[i+1][i+1] == 0)
 				flag = false;
 		}
     
 		//Other Diagonal
 		if(flag == true)
 		{
 			return true;
 		}
     
 		flag = true;
 		for(int i = 0 ; i < TicTacToeBoard.N-1 ; i++)
 		{
 			if(gameBoard[i][TicTacToeBoard.N-i-1] != gameBoard[i+1][TicTacToeBoard.N-i-2] || gameBoard[i][N-i-1]==0 || gameBoard[i+1][TicTacToeBoard.N-i-2] == 0 )
 				flag = false;
 		}
 		if(flag == true)
 		{
 			return true;
 		}
 		return false;
 	
 	}
 	
    //Checks Rows
    private boolean rowWinner(int gameBoard[][])
    {
    	for(int i = 0; i < N ; i++)
    	{
    		boolean flag = false;
    		for(int j = 0 ; j < N-1 ; j++)
    		{
    			if(gameBoard[i][j]!= gameBoard[i][j+1] || gameBoard[i][j] == 0|| gameBoard[i][j+1] == 0)
    				flag = true;
    			
    		}
    		if(flag == false)
    		{
    			return true;
    		}
    	}
		return false;
    }
    
    //Checks columns
    private boolean colWinner(int gameBoard[][])
 	{
 		for(int i = 0; i <N ; i++)
 		{
 			boolean flag = false;
 			for(int j = 0 ; j < N-1 ; j++)
 			{
 				if(gameBoard[j][i]!= gameBoard[j+1][i] || gameBoard[j][i] == 0 || gameBoard[j+1][i]==0)
 				flag = true;
 			}
 			if(flag == false)
 			{
 				return true;
 			}
 		}
 		return false;
 	}
    //--------------------------------------------------------------------------------------//
    
    // Calls getNextMove if the Board is not filled
    public void makeNextMove()
    {
        if (!copyOfBoard.isFilled())
        {
        	getNextMove();
        }
    }

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return this.row;
	}

	@Override
	public int getColumn() 
	{
		// TODO Auto-generated method stub
		return this.col;
	}
    
}
