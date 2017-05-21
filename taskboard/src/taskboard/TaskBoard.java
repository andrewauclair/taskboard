package taskboard;

public class TaskBoard {
	TaskBin backlog = new TaskBin();
	TaskBin icebox = new TaskBin();
	int uniqueID = 1;
	
	public Task createTask(String name, int hours) {
		Task task = new Task(name, hours, uniqueID++);
		icebox.addTask(task);
		return task;
	}

	public int iceboxCount() {
		return icebox.count();
	}

	public void moveToBacklog(int id) {
		icebox.move(id, backlog);
	}

	public int backlogCount() {
		return backlog.count();
	}
}
