package serial;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Akhilesh Godi(CS10B037)
 * @date March 4, 2012
 *
 */
public class Driver 
{
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Create 3 objects of class StudentDetails and set attributes.
		StudentDetails student1 = new StudentDetails("Akhilesh", 18, "Male");
		StudentDetails student2 = new StudentDetails("Dhanvin", 19, "Male");
		StudentDetails student3= new StudentDetails("Karthik", 17, "Male");
		
		//Add the objects into a list.
		List <StudentDetails> StudentList = new ArrayList<StudentDetails>();
		StudentList.add(student1);
		StudentList.add(student2);
		StudentList.add(student3);
		
		String filename = "student.txt";
		
		//Persist the objects to a file
		StudentPersist.persist(StudentList, filename);
		
		//Retrieve them back 
		GetStudentDetails.retrieve(filename);
	}
	
}

