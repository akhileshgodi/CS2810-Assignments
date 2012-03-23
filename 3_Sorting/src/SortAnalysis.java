/**
 * @author Akhilesh G (CS10B037)
 *
 */
import java.util.*;

public class SortAnalysis 
{

	private static int randInt;
	private static long is = 0;
	private static int N = 1000; // Change input size here
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		Runtime prog = Runtime.getRuntime();
		for(int i = 0; i < 20;i++)
		{
			
			List<Integer> array = new ArrayList <Integer>();
			Random randGen = new Random(30);
			for(long j = 0 ; j<N; j++)
			{
				randInt = randGen.nextInt(7000000);
				array.add(randInt);
			}
			  
			 //Uncomment the line below to generate an array of strings and comment the lines above
			//List <String> array = arrayListGenerate(N); 
			
			long startTime = System.nanoTime();
			//array = modifiedSort(array);
			insertionSort(array);
			/**Change the above line to the respective method for the sorting algorithm 
			  *whose performance you want to measure.
			 **/
			long endTime = System.nanoTime();
			is += (endTime - startTime);
		}
		double time = is/10.0;
		System.out.printf( "Insertion sort took  %.6f seconds for an input size of %d\n", time/(1000000000L) , N );
		System.out.println( "Current Memory Usage: " + prog.totalMemory() +"bytes" ); 
		
	}
/*-----------------------------------------------------------------------------------------------------------------*/	
	/*Insertion Sort*/
	public static <T extends Comparable<T>> void insertionSort(List<T> lst)
	{
		for(int i = 1 ; i < lst.size() ; i++)
		{
			T temp = lst.get(i);
			int j = i-1;
			while(j>= 0 && (temp.compareTo(lst.get(j)) < 0))
			{
				lst.set(j+1,lst.get(j));
				j--;
			}
			lst.set(j+1,temp);
		}
	}
/*-----------------------------------------------------------------------------------------------------------------*/	
	/*Merge Sort*/
	public static <T extends Comparable<T>> List<T> mergeSort(List<T> lst)
	{
		if(lst.size()<=1)
			return lst;
		int mid = lst.size()/2;
		List <T> left = lst.subList(0,mid);
		List <T> right = lst.subList(mid,lst.size());
		left = mergeSort(left);
		right = mergeSort(right);
		lst = merge(left,right);
		return lst;
	}
	
	/*Merge*/
	public static <T extends Comparable<T>> List<T> merge(List<T> left, List<T> right)
	{	
		List<T> list = new ArrayList<T>();
		int i = 0;
	    int j =0;
	    while( (i < left.size()) && (j < right.size())) 
	    {
	        if((left.get(i)).compareTo(right.get(j)) < 0)
	        	list.add(left.get(i++));
	         else
	           	list.add(right.get(j++)); 
	    }
	    
	    while(i < left.size())
	    	list.add(left.get(i++));

    	while(j < right.size())
    		list.add((right.get(j++)));
    		
    	return list;
	}
/*--------------------------------------------------------------------------------------------------*/
	/*Bubble Sort*/
	public static <T extends Comparable<T>> void bubbleSort(List<T> lst)
	{
		int length = lst.size();
		boolean swapped = false;
		do
		{
			swapped = false;
			for(int i = 0 ; i < length-1; i++ )
			{
				if(lst.get(i+1).compareTo(lst.get(i)) < 0)
				{
					T temp = lst.get(i);
					lst.set(i,lst.get(i+1));
					lst.set(i+1, temp);
					swapped = true;
				}
			}
		} while(swapped == true);
	}

/*----------------------------------------------------------------------------------------------------*/
	/*Modified Sort*/
	public static <T extends Comparable<T>> List<T> modifiedSort(List<T> lst)
	{
		if(lst.size()<=10)
		{
			insertionSort(lst);
			return lst;
		}
		int mid = lst.size()/2;
		List <T> left = lst.subList(0,mid);
		List <T> right = lst.subList(mid,lst.size());
		left = modifiedSort(left);
		right = modifiedSort(right);
		lst = merge(left,right);
		return lst;
	}
	
/*---------------------------------------------------------------------------------------------------*/
	/* Random String Generator*/
	public static List<String> arrayListGenerate(int n) 
	{
		List<String> array = new ArrayList<String>(); //Change this to Linked List if you want a Linked List
		int length;
		Random rand = new Random(30);
		char[] goodChar = 
		{  
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k','l', 'm', 'n','o',  
				'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',  
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L','M', 'N', 'O',  
				'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',  
        };  
		while(n > 0) 
		{
			length = rand.nextInt(10)+1;
			StringBuffer sb = new StringBuffer();  
			while(length > 0) 
			{
				sb.append(goodChar[rand.nextInt(goodChar.length)]); 
				length--;
			}
			array.add(sb.toString());
			n--;
		}
		//System.out.println(array);
	return array;
	}
/*-----------------------------------------------------------------------------------------------------*/
}