package PractisePackage;

import java.io.Serializable;

/**
 * 
 * @class_name:Manager2020��8��5��
 * @comments: ����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��5������10:36:51
 */
public class Manager extends Employee implements Serializable{
	private Employee secretary;
	public Manager(String name, int salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		// TODO Auto-generated constructor stub
	}
	public Employee getSecretary() {
		return secretary;
	}
	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}
	@Override
	public String toString() {
		return  "Manager [name=" + super.name + ", salary=" +  super.salary + ", year=" +  super.year + ", month=" +  super.month + ", day=" + day
				+ " secretary=" + secretary + "]";
	}
//	@Override
//	public String toString() {
//		return "Manager [secretary=" + secretary + "]";
//	}
	
}
