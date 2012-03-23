package iitm.apl.MazeGenerator;
import java.util.Random;
import java.util.Stack;

/**
*
* @author Akhilesh Godi (CS10B037)
*/

public class MyMaze extends Maze
{
	int j = 0;
	public MyMaze(int rows, int cols) 
	{
		super(rows, cols);
	}
	
	@Override
	public void generate(Recorder recorder) 
	{
		DisjointSet cells[] = new DisjointSet[rows*cols];
		for (int i = 0; i < cells.length; i++) 
		{
			cells[i] = new DisjointSet(i + 1);
		}
		int X = 1;
		int Y;
		Stack <Integer> s = new Stack<Integer>();
		int checks = 0;
		Random randGen = new Random();
		
		while (cells[0].size < rows*cols) 
		{
			//System.out.println(cells[0].size);
			boolean flag = false;
			int possibleY[] = findNeighbour(X);
			Y = possibleY[randGen.nextInt(possibleY.length)];
			for(int i = 0 ; i< possibleY.length; i++)
			{
				if(cells[X-1].findSet()!=cells[possibleY[i]-1].findSet())
					flag = true;
			}
			
			if(flag == false)
			{
				X = s.pop();
				possibleY = findNeighbour(X);
			}
			
			if (cells[X - 1].findSet() != cells[Y - 1].findSet()) 
			{
				breakWall(X, Y);
				cells[X - 1].Union(cells[Y - 1]);
				s.push(X);
				checks++;
				recorder.takeSnap(this);
				X = Y;
			}
		}				
	}
		
	public int[] findNeighbour(int x)
	{
		int noOfChoices;
		if (x == 1) 
		{
			noOfChoices = 2;
			int[] choices = new int[noOfChoices];
			choices[0] = 1;
			choices[1] = 1 + cols;
			return choices;
		} 
		
		else if (x == cols) 
		{
			noOfChoices = 2;
			int[] choices = new int[noOfChoices];
			choices[0] = cols - 1;
			choices[1] = 2 * cols;
			return choices;
		}
		
		else if (x ==(rows - 1) * cols + 1) 
		{
			noOfChoices = 2;
			int[] choices = new int[noOfChoices];
			choices[0] = x - cols;
			choices[1] = x + 1;
			return choices;
		} 
		
		else if (x == rows * cols) 
		{
			noOfChoices = 2;
			int[] choices = new int[noOfChoices];
			choices[0] = x - 1;
			choices[1] = x - cols;
			return choices;
		} 
		
		else if (x % cols == 1) 
		{
			noOfChoices = 3;
			int[] choices = new int[noOfChoices];
			choices[0] = x + 1;
			choices[1] = x + cols;
			choices[2] = x - cols;
			return choices;
		}
		
		else if (x % cols == 0) 
		{
			noOfChoices = 3;
			int[] choices = new int[noOfChoices];
			choices[0] = x - 1;
			choices[1] = x + cols;
			choices[2] = x - cols;
			return choices;
		} 
		
		else if (x < cols) 
		{
			noOfChoices = 3;
			int[] choices = new int[noOfChoices];
			choices[0] = x + 1;
			choices[1] = x + cols;
			choices[2] = x - 1;
			return choices;
		} 
		
		else if (x > (rows - 1) * cols) {
			noOfChoices = 3;
			int[] choices = new int[noOfChoices];
			choices[0] = x + 1;
			choices[1] = x - cols;
			choices[2] = x - 1;
			return choices;
		} 
		
		else 
		{
			noOfChoices = 4;
			int[] choices = new int[noOfChoices];
			choices[0] = x + 1;
			choices[1] = x - cols;
			choices[2] = x - 1;
			choices[3] = x + cols;
			return choices;
		}
		
	}

		
}
