package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.GeneralInterface;
import model.Project;

public class GeneralUser implements GeneralInterface {
	// HashMap to store id and password
	private Map<String, String> userDetails = new HashMap<>();
	private ProjectData projectData = new ProjectData();
	private Scanner sc = new Scanner(System.in);

	/**
	 * @return the userDetails
	 */
	public Map<String, String> getUserDetails() {
		return userDetails;
	}

	@Override
	public void viewProjects(List<Project> allProjects) {

		for (Project p : allProjects) {
			System.out.println(p);
		}
	}

	@Override
	public void changeStatus(int projectId) {
		// assuming that validation of projectId is done by calling function
		Project project = projectData.getAllProjects().stream().filter(p -> p.getId() == projectId)
				.collect(Collectors.toList()).get(0);
		System.out.println("\nEnter task id to change status");
		int noOfTasks = project.getTaksList().size();
		int taskId;
		String status;
		do {
			taskId = Integer.parseInt(sc.nextLine());
			if (taskId > noOfTasks || taskId < 1) {
				System.out.println("\nPlease Enter valid task Id");
			} else {
				System.out.println("\nChoose the status you want task be updated to");
				status = addTaskStatus();
				break;
			}
		} while (true);
		projectData.changeTaskStatus(projectId, taskId, status);
	}

	public void addUser(String email, String password) {
		System.out.println("\nHere this refers to the class " + this.getClass().getName());
		this.userDetails.put(email, password);
	}

	protected String addTaskStatus() {
		System.out.println("1.Not Started\n2.In progress\n3.Under review\n4.Completed");
		int choice;
		do {
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
			case 2:
			case 3:
			case 4:
				break;
			default:
				System.out.println("Please enter valid choice");
			}
			switch (choice) {
			case 1:
				return "Not Started";
			case 2:
				return "In Progress";
			case 3:
				return "Under Review";
			case 4:
				return "Completed";
			}
		} while (true);
	}

	public int getValidProjectId(List<Project> allProjects) {
		int projectId;
		do {
			projectId = Integer.parseInt(sc.nextLine());
			if (projectId > allProjects.size() || projectId < 1)
				System.out.println("Please enter valid project Id");
			else
				return projectId;
		} while (true);
	}
}
