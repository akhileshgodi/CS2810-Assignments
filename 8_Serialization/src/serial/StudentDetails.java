package serial;
/**
 * @author Akhilesh Godi

 * @date March 15, 2012
 *
 */
import java.io.Serializable;

public class StudentDetails implements Serializable,Comparable<StudentDetails>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8465116787318420577L;
	private String name;
	private int age;
	private String sex;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public StudentDetails(String name, int age, String sex) 
	{
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	@Override
	public int compareTo(StudentDetails arg0) 
	{
		// TODO Auto-generated method stub
		if(this.getAge() == arg0.getAge())			//Replace with whatever getxxxx() for the appropriate one!
		return 0;
		else if(this.getAge() > arg0.getAge())
			return 1;
		else return -1;
				
			
	}
}
