package taskboard;

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
}
