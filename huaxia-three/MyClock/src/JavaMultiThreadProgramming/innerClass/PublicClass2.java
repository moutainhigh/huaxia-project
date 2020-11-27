package JavaMultiThreadProgramming.innerClass;

public class PublicClass2 {
	static	private String username;
	static	private String password;
	static	class PrivateClass{
		private String age;
		private String address;
		public String getAge() {
			return age;
		}
		public String getAddress() {
			return address;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public void setAddress(String address) {
			this.address = address;
		}
	}
	public static String getUsername() {
		return username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setUsername(String username) {
		PublicClass2.username = username;
	}
	public static void setPassword(String password) {
		PublicClass2.password = password;
	}
}
