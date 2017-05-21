package taskboard.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import taskboard.Task;
import taskboard.TaskBin;

public class TaskBinTest {
	TaskBin bin;
	
	@Before
	public void setup() {
		bin = new TaskBin();
	}
	
	@Test
	public void shouldAddTaskToBin() {
		bin.addTask(new Task("Test", 2, 1));
		bin.addTask(new Task("", 2, 2));
		assertEquals(2, bin.count());
		assertTrue(bin.hasTask(1));
		assertTrue(bin.hasTask(2));
	}
	
	@Test
	public void shouldMoveTaskToOtherBin() {
		Task task = new Task("Test", 2, 1);
		bin.addTask(task);
		
		TaskBin otherBin = new TaskBin();
		
		bin.move(1, otherBin);
		
		assertFalse(bin.hasTask(1));
		assertTrue(otherBin.hasTask(1));
	}
	
	@Test
	public void shouldReturnTotalHours() {
		bin.addTask(new Task("", 8, 1));
		bin.addTask(new Task("", 4, 2));
		bin.addTask(new Task("", 2, 3));
		assertEquals(14, bin.totalHours());
	}
}
