package serial;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author Akhilesh
 * @date March 4, 2012
 *
 */
public class StudentPersist {

	/**
	 * @param list 
	 * @param args
	 */
	public static void persist(List<StudentDetails> list,String filename) 
	{
		// TODO Write the list Object to a file called "student.txt".
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try 
		{
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		} catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}
}




