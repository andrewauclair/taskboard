package taskboard;

public class Commands {

	private TaskBoard board = new TaskBoard();

	public String execute(String command) {
		String[] split = command.split(" ");
		if (command.startsWith("new task") && split.length == 4) {
			String name = split[split.length - 2];
			int hours = Integer.parseInt(split[split.length - 1]);
			
			Task task = board.createTask(name, hours);
			
			return "added task \"" + name + "\", " + hours + " hours, id " + task.getID();
		}
		else if (command.startsWith("start") && split.length == 3) {
			int id = Integer.parseInt(split[split.length - 1]);
			Task task = board.getTask(id);
			board.startTask(id);
			return "started task \"" + task.getName() + "\" (id " + id + "), " + task.getHours() + " hours";
		}
		else if (command.equals("totals")) {
			return "unstarted: " + board.unstartedTasks() + ", started: " + board.startedTasks() + ", finished: " + board.finishedTasks();
		}
		return "Invalid command";
	}

	public TaskBoard getTaskBoard() {
		return board;
	}

}
