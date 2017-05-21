package taskboard.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import taskboard.Task;

public class TaskTest {
	Task task;
	
	@Before
	public void setup() {
		task = new Task("Task Name", 8, 123);
	}
	
	@Test
	public void shouldCreateTaskWithNameHoursAndID() {
		assertEquals("name", "Task Name", task.getName());
		assertEquals("hours", 8, task.getHours());
		assertEquals("ID", 123, task.getID());
	}
	
	@Test
	public void shouldStartUnstartedTask() {
		task.start();
		assertTrue(task.started());
	}
	
	@Test
	public void shouldFinishStartedTask() {
		task.start();
		task.finish();
		assertTrue(task.finished());
		assertFalse(task.started());
	}
	
	@Test
	public void shouldNotFinishUnstartedTask() {
		task.finish();
		assertFalse(task.finished());
	}
	
	@Test
	public void shouldNotStartFinishedTask() {
		task.start();
		task.finish();
		task.start();
		assertFalse(task.started());
	}
}
