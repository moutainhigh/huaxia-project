package JavaMultiThreadProgramming.innerClass;

import JavaMultiThreadProgramming.innerClass.PublicClass2.PrivateClass;

public class Run2 {
	public static void main(String args[]){
		PublicClass publicClass = new PublicClass();
		publicClass.setUsername("usernameValue");
		publicClass.setPassword("passwordVaoue");
		System.out.println(publicClass.getUsername()+" "+publicClass.getPassword());
		PrivateClass privateClass = new PrivateClass();
		privateClass.setAge("ageValue");
		privateClass.setAddress("addressValue");
		System.out.println(privateClass.getAge()+" "+privateClass.getAddress());
	}
}
