package main;

import java.util.Scanner;

public class Main {

	private static Scanner s  = new Scanner(System.in);

	public static void main(String[] args) {
	
		String command = "help";
		while(command.equalsIgnoreCase("stop")) {
			if(command.equalsIgnoreCase("help")) {
				System.out.println("help\npart1\npart2\nstop");
			}
			else if(command.equalsIgnoreCase("part1")) {
				part2();
			}
			else if(command.equalsIgnoreCase("part2")) {
				part1();
			}
			else if(!command.equalsIgnoreCase("stop")) {
				System.out.println("no such command");
			}
			command = s.next();
		}
		
	}
	
	public static void part1() {
		
	}
	
	public static void part2() {
		
	}
	
}
