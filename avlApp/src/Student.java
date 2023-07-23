
/*
 * artist Kevin Chiloane
 * 22/04/2021
 * class implements the Comperable interface to compare 
 * person identity by dictionary order
 */

public class Student implements Comparable<Student>
{
	 String aName,aId;
	
	public Student(String aId)
	{
		this.aId = aId;
	}
	
	public Student(String aId, String aName)
	{
		this.aName =aName;
		this.aId = aId;
	}
	
	public String getaName() {
		return aName;
	}
	
	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub'
		return this.aId.compareTo(o.aId);
	}

	@Override
	public String toString() {
		return aId+ " "+aName;
	}
}
