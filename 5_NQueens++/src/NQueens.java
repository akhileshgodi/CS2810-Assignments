/*Author : Akhilesh Godi (CS10B037)
 * Assignment 5 - CS2810
 * NQueens ++
 */
import java.util.Scanner;


public class NQueens {

	/**
	 * @param args
	 */
	public static int noOfQueens,cases;
	static int x[];
	static int y[];
	public static void main(String[] args) 
	{
		Scanner read = new Scanner(System.in);
		noOfQueens = read.nextInt();
		cases = read.nextInt();
		int positions[] = new int[noOfQueens];
		for(int k =0; k < noOfQueens; k++)
			positions[k] = -1;
		x = new int[cases];
		y = new int[cases];
		for(int i = 0; i < cases; i++)
		{
			x[i] = read.nextInt()-1;
			y[i] = read.nextInt()-1;
			positions[x[i]]=y[i];
		}
			givePositions(0,positions);	
	}
	
	private static boolean givePositions(int row, int [] positions) 
	{
		
		if(row == positions.length)
		{
			printPositions(positions);
			return true;
		}
		
		boolean flag = false;
		boolean b = true;
		for(int i = 0; i< cases;i++)
		{
			if(row == x[i])
			{
				flag = true;
				if(checkIsValid(positions,row) == false) 
					return false;
				i = cases+1;
				b = givePositions(row+1,positions);
			}
		}
		if(b==false) return false;
		
		if(flag == false)
		{
			for(int cols = 0; cols<positions.length; cols++)
			{
				positions[row] = cols;
				if(checkIsValid(positions, row))
				{	
					givePositions(row+1,positions);
				}
			}
			
		}
		return false;
	}
	
	//Checks if the value can be inserted or no
	private static boolean checkIsValid(int[] positions,int row)
	{
		for(int i=0; i<row; i++)
		{
			if(i!=row)
			{
				if(((positions[row]-positions[i])== row-i) || 
						((positions[i]-positions[row] ) == row-i)
									|| (positions[i] == positions[row]))
				return false;
			}
		}
		
		return true;
	}

	//Prints the Positions
	private static void printPositions(int [] positions)
	{
		for(int i =0 ; i< positions.length; i++)
		{
			char letter = (char) ('a' + i);
			if(i<positions.length-1)
				System.out.print( letter+""+(positions[i]+1)+" ");
			else
				System.out.print( letter+""+(positions[i]+1));
		}
		System.out.println("");
	}
}
