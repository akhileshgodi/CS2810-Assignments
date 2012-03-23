package serial;
/**
 * @author Akhilesh Godi	
 * @date March 15,2012
 *
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class GetStudentDetails
{

	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void retrieve(String filename) 
	{
		// TODO Retrieve the object written in the file "students.txt".
		
		List<StudentDetails> details = null;
		FileInputStream FileInput = null;
		ObjectInputStream in = null;
		try 
		{
			FileInput = new FileInputStream(filename);
			in = new ObjectInputStream(FileInput);
			details = (ArrayList<StudentDetails>) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		insertionSort(details);
		StudentDetails s1 = details.get(0);
		StudentDetails s2 = details.get(1);
		StudentDetails s3 = details.get(2);

		//Print the details of the object.
		System.out.println(" Name          " + "   Age   "   + "   Sex   ");
		System.out.println(s1.getName() + "          " + s1.getAge() + "        " + s1.getSex());
		System.out.println(s2.getName() + "           " + s2.getAge() + "        " + s2.getSex());
		System.out.println(s3.getName() + "           " + s3.getAge() + "        " + s3.getSex());
		
	}

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
}


