package PractisePackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {

	public ObjectStreamTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee harry = new Employee("Harry Hacker",50000,1989,10,1);
		Manager carl = new Manager("Carl Cracker",8000,1987,12,15);
		carl.setSecretary(harry);
		Manager tony = new Manager("Tony Tester",40000,1990,3,15);
		tony.setSecretary(harry);
		Employee[] staff = new Employee[3];
		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;
		for(Employee e:staff)
			System.out.println(e);
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:/eclipse_workPlace/PractiseProject/src/empleyee.bat"))){
			out.writeObject(staff);
		}catch(Exception e){
			e.printStackTrace();
		}
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:/eclipse_workPlace/PractiseProject/src/empleyee.bat"))){
			Employee[] newStaff = (Employee[])in.readObject();
			newStaff[1].raiseSalary(10);
			for(Employee e:newStaff)
				System.out.println(e);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
