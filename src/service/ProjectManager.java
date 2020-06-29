package service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Project;
import model.ProjectManagerInterface;
import model.Task;

public class ProjectManager extends GeneralUser implements ProjectManagerInterface {
	private Scanner sc = new Scanner(System.in);
	private Task task;
	private int projectId, choice;
	private ProjectData projectData = new ProjectData();
	private List<Project> allProjects = projectData.getAllProjects();
	private String taskName;
	private LocalDate deadline;
	private Set<Integer> responsible = new HashSet<>();
	private Set<Integer> consult = new HashSet<>();
	private Set<Integer> inform = new HashSet<>();
	private int accountable;
	private String status;
	private int noOfTasks;

	@Override
	public void addTask() {
		System.out.println("Task Name:");
		this.taskName = sc.nextLine();
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
		System.out.println("Choose Task status:\n");
		status = addTaskStatus();
		task = new Task(noOfTasks + 1, taskName, deadline, LocalDate.now(), responsible, consult, inform, accountable,
				status);
		// add this task to the list
		projectData.addTask(projectId, task);
		System.out.println("Task added successfully!");
	}

	public void editProjects() {
		if (allProjects.size() == 0) {
			System.out.println("There are no projects to show\n");
			return;
		}
		System.out.println("These are the existing projects\n");
		viewProjects(allProjects);
		System.out.println("Enter Project Id to edit:");
		do {
			projectId = Integer.parseInt(sc.nextLine());
			if (projectId > allProjects.size() || projectId < 1) {
				System.out.println("Please enter valid project Id\n");
			} else {
				break;
			}
		} while (true);

		System.out.println("1.Add a Task\n2.Edit a Task status\n");
		do {
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:// add a task
				noOfTasks = allProjects.get(projectId - 1).getTaksList().size();
				addTask();
				return;
			case 2:// edit a task status
				changeStatus(projectId);
				return;
			default:
				System.out.println("Please enter valid choice\n");
			}
		} while (true);
	}

	protected void add(Set<Integer> set) {
		String choice;
		int ch;
		boolean flag = false;
		do {
			System.out.println("1.Board Chair\n2.Board Member\n3.Project Manager\n4.Marketing Lead\n5.Volunteer Lead");
			ch = Integer.parseInt(sc.nextLine());
			switch (ch) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				set.add(ch);
				System.out.println("Do you want to add more?(y/n)");

				do {
					choice = sc.nextLine();
					if (choice.equalsIgnoreCase("y")) {
						break;
					} else if (choice.equalsIgnoreCase("n")) {
						flag = true;
						break;
					} else {
						System.out.println("Please enter valid choice");
					}
				} while (true);
				break;
			default:
				System.out.println("Please enter valid choice");
			}
		} while (!flag);
	}

	protected int addAccountable() {
		System.out.println("1.Board Chair\n2.Board Member\n3.Project Manager\n4.Marketing Lead\n5.Volunteer Lead");
		int choice = Integer.parseInt(sc.nextLine());
		do {
			switch (choice) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return choice;
			default:
				System.out.println("Please enter valid choice");
			}
		} while (true);
	}
}
