package taskboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TaskBin {
	private Map<Integer, Task> tasks = new HashMap<>();
	
	public void addTask(Task task) {
		tasks.put(task.getID(), task);
	}

	public int count() {
		return tasks.size();
	}

	public boolean hasTask(int id) {
		return tasks.containsKey(id);
	}

	public void move(int id, TaskBin bin) {
		if (hasTask(id)) {
			bin.addTask(tasks.get(id));
			tasks.remove(id);		
		}
	}

	public int totalHours() {
		int hours = 0;
		for (Task task : tasks.values()) {
			hours += task.getHours();
		}
		return hours;
	}

	public Task getTask(int id) {
		return tasks.get(id);
	}

	public int tasksUnstarted() {
		int count = 0;
		for (Task task : tasks.values()) {
			if (!task.started() && !task.finished()) {
				count++;
			}
		}
		return count;
	}

	public int tasksStarted() {
		int count = 0;
		for (Task task : tasks.values()) {
			if (task.started()) {
				count++;
			}
		}
		return count;
	}

	public int tasksFinished() {
		int count = 0;
		for (Task task : tasks.values()) {
			if (task.finished()) {
				count++;
			}
		}
		return count;
	}
	
	public ArrayList<Task> getTasks() {
		ArrayList<Task> taskList = new ArrayList<>();
		for (Task task : tasks.values()) {
			taskList.add(task);
		}
		return taskList;
	}

	public int highestID() {
		int highest = 0;
		for (Integer id : tasks.keySet()) {
			if (id > highest) {
				highest = id;
			}
		}
		return highest;
	}
}
