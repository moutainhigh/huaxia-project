package com.huaxia.plaze.common;

/**
 * 任务类型
 * 
 * @author zhiguo.li
 *
 */
public enum Task {

	PBOC("0001", "人行征信");

	private String taskType;

	private String taskDesc;

	private Task(String taskType, String taskDesc) {
		this.taskType = taskType;
		this.taskDesc = taskDesc;
	}
	
	public String getTaskType() {
		return taskType;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public static String getTaskDesc(String taskType) {
		Task[] array = Task.values();
		for (int i = 0; i < array.length; i++) {
			if (array[i].getTaskType().equals(taskType)) {
				return array[i].getTaskDesc();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(Task.getTaskDesc(Task.PBOC.taskType));
	}

}
