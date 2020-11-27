package PractisePackage;

import java.io.Serializable;

public class Employee implements Serializable{
	public  String name;
	public int salary;
	public int year;
	public int month;
	public int day;
	public Employee(String name,int salary,int year,int month,int day) {
		this.name  = name;
		this.salary = salary;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	public String getName() {
		return name;
	}
	public int getSalary() {
		return salary;
	}
	public int getYear() {
		return year;
	}
	public int getMonth() {
		return month;
	}
	public int getDay() {
		return day;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public void raiseSalary(int percent){
		this.salary = this.salary+percent;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", year=" + year + ", month=" + month + ", day=" + day
				+ "]";
	}
}
