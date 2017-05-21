package taskboard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import taskboard.Commands;
import taskboard.Task;
import taskboard.TaskBoard;

public class CommandsTest {
	private static final String taskName = "Test Name";
	private static final int taskHours = 8;
	
	Commands commands;
	TaskBoard board;
	
	@Before
	public void setup() {
		commands = new Commands();
		board = commands.getTaskBoard();
	}
	
	@Test
	public void shouldCreateNewTaskOnNewTaskCommand() {
		assertEquals("added task \"" + taskName + "\", 8 hours, id 1", commands.execute("new task \"" + taskName + "\" 8"));
		
		TaskBoard board = commands.getTaskBoard();
		
		assertEquals(1, board.iceboxCount());
		
		Task task = board.getTask(1);
		
		assertEquals(taskName, task.getName());
		assertEquals(taskHours, task.getHours());
	}
	
	@Test
	public void shouldStartTaskOnStartTaskCommand() {
		Task task = createTask();
		
		assertEquals("started task \"" + taskName + "\" (id 1), " + taskHours + " hours", commands.execute("start task 1"));
		assertTrue(task.started());
	}
	
	@Test
	public void shouldFinishTaskOnFinishTaskCommand() {
		Task task = createTask();
		task.start();
		
		assertEquals("finished task \"" + taskName + "\" (id 1), " + taskHours + " hours", commands.execute("finish task 1"));
		assertTrue(task.finished());
	}
	
	@Test
	public void shouldShowTotalsOnTotalsCommand() {
		createTask();
		createTask();
		createTask();
		board.startTask(1);
		board.startTask(2);
		board.finishTask(1);
		
		assertEquals("unstarted: 1, started: 1, finished: 1", commands.execute("totals"));
	}

	@Test
	public void shouldShowIceboxOnShowCommand() {
		board.createTask("Task 1", taskHours);
		board.createTask("Task 2", taskHours);
		board.createTask("Task 3", 4);
		board.createTask("Task 4", 2);
		board.createTask("Task 5", 2);
		board.createTask("Task 6", 2);
		board.createTask("Task 7", 2);
		board.createTask("Task 8", 2);
		board.createTask("Task 9", 2);
		board.createTask("Task 10", 2);
		
		assertEquals(" 1 - Task 1, " + taskHours + " hours\n" + 
				" 2 - Task 2, " + taskHours + " hours\n" +
				" 3 - Task 3, 4 hours\n" + 
				" 4 - Task 4, 2 hours\n" + 
				" 5 - Task 5, 2 hours\n" + 
				" 6 - Task 6, 2 hours\n" + 
				" 7 - Task 7, 2 hours\n" + 
				" 8 - Task 8, 2 hours\n" + 
				" 9 - Task 9, 2 hours\n" + 
				"10 - Task 10, 2 hours\n", commands.execute("show icebox"));
	}
	
	@Test
	public void shouldShowBacklogOnShowCommand() {
		board.createTask("Task 1", taskHours);
		board.createTask("Task 2", taskHours);
		board.createTask("Task 3", 4);
		board.createTask("Task 4", 2);
		board.createTask("Task 5", 2);
		board.createTask("Task 6", 2);
		board.createTask("Task 7", 2);
		board.createTask("Task 8", 2);
		board.createTask("Task 9", 2);
		board.createTask("Task 10", 2);
		
		for (int i = 1; i <= 10; i++) {
			board.startTask(i);
		}
		
		assertEquals(" 1 - Task 1, " + taskHours + " hours\n" + 
				" 2 - Task 2, " + taskHours + " hours\n" +
				" 3 - Task 3, 4 hours\n" + 
				" 4 - Task 4, 2 hours\n" + 
				" 5 - Task 5, 2 hours\n" + 
				" 6 - Task 6, 2 hours\n" + 
				" 7 - Task 7, 2 hours\n" + 
				" 8 - Task 8, 2 hours\n" + 
				" 9 - Task 9, 2 hours\n" + 
				"10 - Task 10, 2 hours\n", commands.execute("show backlog"));
	}
	
	private Task createTask() {
		return board.createTask(taskName, taskHours);
	}
	
	@Test
	public void shouldReturnInvalidForInvalidCommands() {
		assertEquals("Invalid command", commands.execute("invalid command"));
	}
}
