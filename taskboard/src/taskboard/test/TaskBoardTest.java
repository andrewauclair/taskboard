package taskboard.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import taskboard.Task;
import taskboard.TaskBoard;

public class TaskBoardTest {
	TaskBoard board;
	
	@Before
	public void setup() {
		board = new TaskBoard();
	}
	
	@Test
	public void shouldCreateNewTaskInIcebox() {
		board.createTask("Test Name", 8);
		assertEquals(1, board.iceboxCount());
	}
	
	@Test
	public void shouldMoveTaskToBacklog() {
		Task task = board.createTask("Test Name", 8);
		board.moveToBacklog(task.getID());
		assertEquals(0, board.iceboxCount());
		assertEquals(1, board.backlogCount());
	}
	
	@Test
	public void shouldAssignIncrementingIDToEachNewTask() {
		assertEquals(1, board.createTask("", 0).getID());
		assertEquals(2, board.createTask("", 0).getID());
		assertEquals(3, board.createTask("", 0).getID());
	}
	
}
