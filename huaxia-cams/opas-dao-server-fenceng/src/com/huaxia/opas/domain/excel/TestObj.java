package com.huaxia.opas.domain.excel;

import java.io.Serializable;

public class TestObj implements Serializable {

	private static final long serialVersionUID = -7341819039449285255L;

	private String num;
	private String name;
	private String age;
	private String score;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "TestObj [num=" + num + ", name=" + name + ", age=" + age
				+ ", score=" + score + "]";
	}

}
