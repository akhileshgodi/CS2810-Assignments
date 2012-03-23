import java.util.Scanner;


public class NQueens 
{
	/**
	 * Read 'N' from terminal and generate a valid arrangement of N queens on an NxN chess board.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Scanner i = new Scanner(System.in);
		int noOfQueens = Integer.parseInt(i.next());
	
		int[] positions= placeQueens(noOfQueens);
		if(positions!= null)
		{
			for(int j=0; j<noOfQueens;j++)
			positionString(j,positions[j]);
		}
		else System.out.print("No Solution.");
		//printBoard(positions);		
	}
	/**
	 * Find a valid arrangement of n queens on a nxn chess-board.
	 * Hint: Use a recursive solution
	 * @param n
	 * @return
	 */
	private static int[] placeQueens(int n) 
	{
		int[] positions = new int[n];
		boolean b = placeQueens(positions,0);
		if(b==true) return positions;
		return null;
	}	
	
	
	private static boolean checkValid(int[] positions,int row)
	{
		//Rows need not be checked now. So, we check for columns and diagonals.
		for(int i=0; i<row; i++)
		{
			if(((positions[row]-positions[i])== row-i) || 
					((positions[i]-positions[row] ) == row-i) //Checks both sides of the diagonals
							|| (positions[i] == positions[row])) //Checks previous rows
				return false;
		}
		
		return true;
	}
	/**
	 * Place queen number 'row' so as not to conflict with any of the previous positions
	 * Return false if no such placement is possible   
	 * @param positions
	 * @param row
	 * @return 
	 */
	public static boolean i = false;	//Used to terminate recursion
	private static boolean placeQueens(int[] positions, int row) 
	{
		if(row == positions.length)
		{
			i = true;
			return true;
		}
		for(int cols = 0; cols<positions.length; cols++)
		{
			positions[row] = cols;
			if(checkValid(positions, row))
			{	
				placeQueens(positions,row+1);
				if(i==true) return true;
			}
			
		}
		return false;
	}
	

	/**
	 * Print the position of a piece in i-th row and j-th column in chess format. 
	 * @param i
	 * @param j
	 * @return
	 */
	private static void positionString(int i, int j) 
	{
		System.out.format("%c%d\n", 'a'+i,j+1);
	}
	
	/**
	 * Helper function that prints the position of all queens given the array
	 * @param positions
	 */
/*	
	private static void printBoard( int[] positions ) 
	{
		int n = positions.length;
		
		System.err.print( "XX|" );
		for( int j = 0; j<n; j++ ) 
		{
			System.err.format( "%c|", 'a'+j);
		}
		System.err.println();
		for( int i = 0; i<n; i++ ) 
		{		
			System.err.format( "%2d|", i+1);
			for( int j = 0; j<n; j++ ) 
			{
				if( j == positions[i] )
					System.err.print( "Q|" );
				else
					System.err.print( " |" );
			}
			System.err.println();
		}
	}
*/
}
