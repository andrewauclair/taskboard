package taskboard;

import java.util.ArrayList;

public class Commands {

	private TaskBoard board = new TaskBoard();

	public String execute(String command) {
		String[] split = command.split(" ");
		if (command.startsWith("new task")) {
			String name = command.substring(command.indexOf('"') + 1, command.lastIndexOf('"'));
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
		else if (command.startsWith("finish") && split.length == 3) {
			int id = Integer.parseInt(split[split.length - 1]);
			Task task = board.getTask(id);
			board.finishTask(id);
			return "finished task \"" + task.getName() + "\" (id " + id + "), " + task.getHours() + " hours";
		}
		else if (command.equals("show icebox")) {
			return formatTaskList(board.getIcebox());
		}
		else if (command.equals("show backlog")) {
			return formatTaskList(board.getBacklog());
		}
		else if (command.equals("totals")) {
			return "unstarted: " + board.unstartedTasks() + ", started: " + board.startedTasks() + ", finished: " + board.finishedTasks();
		}
		return "Invalid command";
	}

	private String formatTaskList(TaskBin bin) {
		ArrayList<Task> tasks = bin.getTasks();
		int highestID = bin.highestID();
		
		String result = "";
		for (Task task : tasks) {
			result += String.format("%" + Integer.toString(highestID).length() + "d", task.getID());
			result += " - " + task.getName() + ", " + task.getHours() + " hours\n";
		}
		return result;
	}

	public TaskBoard getTaskBoard() {
		return board;
	}

}
