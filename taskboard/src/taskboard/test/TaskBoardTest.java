package taskboard.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void shouldMoveTaskToIcebox() {
		board.createTask("Test Name", 8);
		board.startTask(1);
		board.moveToIcebox(1);
		assertEquals(0, board.backlogCount());
		assertEquals(1, board.iceboxCount());
	}
	
	@Test
	public void shouldAssignIncrementingIDToEachNewTask() {
		assertEquals(1, board.createTask("", 0).getID());
		assertEquals(2, board.createTask("", 0).getID());
		assertEquals(3, board.createTask("", 0).getID());
	}
	
	@Test
	public void shouldStartAndMoveTaskToBacklog() {
		board.createTask("Test Name", 8);
		board.startTask(1);
		
		Task task = board.getTask(1);
		
		assertTrue(task.started());
		assertEquals(0, board.iceboxCount());
		assertEquals(1, board.backlogCount());
	}
	
	@Test
	public void shouldStartTaskInBacklog() {
		board.createTask("Test Name", 8);
		board.moveToBacklog(1);
		board.startTask(1);
		
		Task task = board.getTask(1);
		
		assertTrue(task.started());
	}
	
	@Test
	public void shouldFinishTaskOnBoard() {
		board.createTask("Test Name", 8);
		board.createTask("Test Name", 8);
		board.startTask(1);
		board.startTask(2);
		board.moveToIcebox(2);
		board.finishTask(1);
		board.finishTask(2);
		
		assertTrue("should finish task in backlog", board.getTask(1).finished());
		assertTrue("should finish task in icebox", board.getTask(2).finished());
	}
	
	@Test
	public void shouldCountUnstartedTasks() {
		board.createTask("Test Name", 8);
		board.createTask("Test Name", 8);
		board.moveToBacklog(1);
		
		assertEquals(2, board.unstartedTasks());
	}
	
	@Test
	public void shouldCountStartedTasks() {
		board.createTask("Test Name", 8);
		board.createTask("Test Name", 8);
		board.startTask(1);
		board.startTask(2);
		board.moveToIcebox(1);
		
		assertEquals(2, board.startedTasks());
	}
	
	@Test
	public void shouldCountFinishedTasks() {
		board.createTask("Test Name", 8);
		board.createTask("Test Name", 8);
		board.startTask(1);
		board.startTask(2);
		board.moveToIcebox(1);
		board.finishTask(1);
		board.finishTask(2);
		
		assertEquals(2, board.finishedTasks());
	}
}
