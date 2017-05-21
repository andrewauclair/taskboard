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
	
	public Task getTask(int id) {
		if (backlog.hasTask(id)) {
			return backlog.getTask(id);
		}
		if (icebox.hasTask(id)) {
			return icebox.getTask(id);
		}
		return null;
	}

	public void startTask(int id) {
		if (backlog.hasTask(id)) {
			backlog.getTask(id).start();
		}
		if (icebox.hasTask(id)) {
			Task task = icebox.getTask(id);
			task.start();
			icebox.move(id, backlog);
		}
	}

	public void finishTask(int id) {
		if (backlog.hasTask(id)) {
			backlog.getTask(id).finish();
		}
		if (icebox.hasTask(id)) {
			icebox.getTask(id).finish();
		}
	}

	public int unstartedTasks() {
		return backlog.tasksUnstarted() + icebox.tasksUnstarted();
	}

	public void moveToIcebox(int id) {
		backlog.move(id, icebox);
	}

	public int startedTasks() {
		return backlog.tasksStarted() + icebox.tasksStarted();
	}

	public int finishedTasks() {
		return backlog.tasksFinished() + icebox.tasksFinished();
	}
}
