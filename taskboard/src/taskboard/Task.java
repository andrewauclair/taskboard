package taskboard;

public class Task {

	private int hours;
	private String name;
	private int id;
	private boolean started;
	private boolean finished;

	public Task(String name, int hours, int id) {
		this.name = name;
		this.hours = hours;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getHours() {
		return hours;
	}

	public int getID() {
		return id;
	}

	public void start() {
		started = true;
	}

	public boolean started() {
		return started;
	}

	public void finish() {
		if (started()) {
			finished = true;
		}
	}

	public boolean finished() {
		return finished;
	}

}
