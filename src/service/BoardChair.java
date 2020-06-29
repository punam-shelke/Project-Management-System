package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.BoardChairInterface;
import model.Project;
import model.Task;

public class BoardChair extends BoardMember implements BoardChairInterface {
	private ProjectData pd = new ProjectData();
	private Scanner sc = new Scanner(System.in);
	private int id = 1;
	private Project newProject;
	private Boolean approvalStatus;// Is project approved?
	private String name;// Name of the project
	private String purpose;// Purpose of the project
	private String goals;// Goals of the project
	private List<Task> taskList = new ArrayList<>();// list of tasks to complete the project
	private int noOfTask;
	private String taskName;
	private LocalDate deadline;
	private Set<Integer> responsible = new HashSet<>();
	private Set<Integer> consult = new HashSet<>();
	private Set<Integer> inform = new HashSet<>();
	private int accountable;
	private String status;

	@Override
	public void createProject() {
		System.out.println("Please Enter following details\n");
		System.out.println("Project Name:");
		name = sc.nextLine();
		System.out.println("Project Purpose:");
		purpose = sc.nextLine();
		System.out.println("Project Goal:");
		goals = sc.nextLine();
		System.out.println("Add tasks to project\nHow many tasks you want to add?");
		noOfTask = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < noOfTask; i++) {
			System.out.println("Task Name:");
			taskName = sc.nextLine();
			System.out.println("Deadline:(yyyy-MM-dd):");
			deadline = LocalDate.parse(sc.nextLine());
			System.out.println("Choose responsible:");
			add(responsible);
			System.out.println("Choose accountable:");
			accountable = addAccountable();
			System.out.println("Choose consult:");
			add(consult);
			System.out.println("Choose inform:");
			add(inform);
			System.out.println("Choose Task status:");
			status = addTaskStatus();
			taskList.add(new Task(i + 1, taskName, deadline, LocalDate.now(), responsible, consult, inform, accountable,
					status));
			System.out.println("\nTask added successfully!");
		}
		System.out.println("Do you want to approve this project?(y/n)");
		approve();
		newProject = new Project(id++, approvalStatus, name, purpose, goals, taskList);
		pd.addProject(newProject);
		System.out.println("Project successfully created!");
		taskList.clear();
		responsible.clear();
		consult.clear();
		inform.clear();
	}

	private void approve() {
		do {
			String choice = sc.nextLine();
			if (choice.equalsIgnoreCase("y")) {
				approvalStatus = true;
				break;
			} else if (choice.equalsIgnoreCase("n")) {
				approvalStatus = false;
				break;
			} else {
				System.out.println("Please enter valid choice");
			}
		} while (true);
	}
}
