package taskboard;

import java.util.Scanner;

public final class Main {
	private static final Commands commands = new Commands();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Task Board Alpha 0.1");
		
		while (true) {
			String line = scanner.nextLine();
			
			if (line.equals("exit")) {
				break;
			}
			else {
				System.out.println(commands.execute(line));
			}
		}
		
		scanner.close();
	}
}
