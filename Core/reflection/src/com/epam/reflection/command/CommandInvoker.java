package com.epam.reflection.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.epam.reflection.ClassExplorer;

public class CommandInvoker {
	private Map<String, Command> commands;
	private ClassExplorer explorer;
	private Scanner scanner;
	private boolean initialized = false;

	public CommandInvoker() {
		scanner = new Scanner(System.in);
		explorer = new ClassExplorer();
		commands = new HashMap<>();
		commands.put("assign", new AssignWithClassCommand());
		commands.put("con", new PrintConstrucorsCommand());
		commands.put("met", new PrintMethodsCommand());
		commands.put("fields", new PrintFieldsCommand());
		commands.put("exit", new ExitCommand());
		commands.put("?", new PrintHelpCommand());
	}

	public void start() {
		System.out.println("Welcome to class inspector.");
		while (true) {
			System.out.println("Please write your command (for help write ?) ");
			String commandName = scanner.nextLine();
			if (commands.containsKey(commandName)) {
				commands.get(commandName).execute();
			} else {
				System.out.println("such command can not be founded... ");
			}

		}
	}

	private class PrintConstrucorsCommand implements Command {

		@Override
		public void execute() {
			if (initialized) {
				
				System.out.println("[Constructors of "+explorer.getTargetClass().getName()+" ]");
				explorer.printAllConstructors();
			} else {
				System.out.println("Please assing class with inspector first... ");
			}

		}

	}

	private class PrintFieldsCommand implements Command {

		@Override
		public void execute() {
			if (initialized) {
				System.out.println("[Fields of "+explorer.getTargetClass().getName()+" ]");
				explorer.printAllFields();
			} else {
				System.out.println("Please assing class with inspector first... s");
			}

		}

	}

	private class PrintMethodsCommand implements Command {

		@Override
		public void execute() {
			if (initialized) {
				System.out.println("[Methods of "+explorer.getTargetClass().getName()+" ]");
				explorer.printAllMethods();
			} else {
				System.out.println("Please assing class with inspector first... s");
			}

		}

	}

	private class PrintHelpCommand implements Command {

		@Override
		public void execute() {

			System.out.println("You can use next commands:\n" + "assign - asiign inspector with class \n"
					+ "con - print constructors\n" + "met - print methods\n" + "fields - pring fields\n"
					+ "exit - close inspector\n");
		}

	}

	private class ExitCommand implements Command {

		@Override
		public void execute() {
			scanner.close();
			System.exit(0);

		}

	}

	private class AssignWithClassCommand implements Command {

		@Override
		public void execute() {
			System.out.println("Please specify class name");
			String className = scanner.nextLine();
			try {
				explorer.assignClassByName(className.trim());
				initialized = true;
				System.out.println(" class " + className + " successfuly assigned to inspector");
			} catch (Exception e) {
				initialized = false;
				System.out.println(" class can not be found in classpath...");
			}

		}

	}

}
