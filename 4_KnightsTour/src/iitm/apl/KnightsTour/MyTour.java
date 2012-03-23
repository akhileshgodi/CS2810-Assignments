/* The Kinght's Tour Problem
 * Author : Akhilesh G (CS10B037)
 * CS2810 - Lab Session 4 
 */
package iitm.apl.KnightsTour;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MyTour extends ChessBoard
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		MyTour tour = new MyTour();
		initialise(tour);
	}

	@Override
	List<Move> tour(Move m) 
	{
		Move present = m;
		makeMove(present);
		int i = getBoardSize();
		int noOfSquares = i*i;
		List<Move> validMoves = new ArrayList<Move>();
		for(int j = noOfSquares; j>0;j--)
		{
			Move[] available = present.reachableMoves();
			if(available.length == 0)
				return null;
			Move leastAccessible = null;
			int noOfPositions = 8;
			for(i = 0; i < available.length; i++ )
			{
				if(isNextMoveValid(available[i]))
				{
					makeMove(available[i]);
					Move[] nextAvailable = available[i].reachableMoves();
					int count = 0;
					for(int k=0; k < nextAvailable.length; k++)
					{
						if(isNextMoveValid(nextAvailable[k]))
							count++;
					}
					if(count < noOfPositions)
					{
						noOfPositions = count;
						leastAccessible = available[i];
					}	
					undoMove();
				}
			}
			if(leastAccessible!=null)
			{
				validMoves.add(leastAccessible);
				present = leastAccessible;
				makeMove(present);
			}	
		}
		return validMoves;
	}

}
