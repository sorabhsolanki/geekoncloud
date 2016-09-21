package com.persitence;

import java.util.ArrayList;
import java.util.List;

import com.pract.task.Task;

public class TaskQueue {

	private static final List<Task> list = new ArrayList<Task>();
	
	public static void addTask(Task task){
		list.add(task);
	}

	public static List<Task> getList() {
		return list;
	}

}
