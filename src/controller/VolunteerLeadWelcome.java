package controller;

import java.util.List;
import java.util.Scanner;
import model.Project;
import service.ExcelGeneration;
import service.ProjectData;
import service.VolunteerLead;

public class VolunteerLeadWelcome {
	private ProjectData pd = new ProjectData();
	private List<Project> allProjects = pd.getAllProjects();
	// object for BoardChair
	VolunteerLead volunteerLead = new VolunteerLead();
	// variable to store user choice
	private int choice, projectId;
	private ExcelGeneration excel = new ExcelGeneration();
	// creating scanner class object to take input
	Scanner inputScanner = new Scanner(System.in);

	// create a basic view for a boardChair
	public void mainView() {
		System.out.println("\n---Welcome to VolunteerLead DashBoard---\n");
		do {
			System.out.println("\n1.Edit task status\n2.View Existing Project\n3.Download RACI chart\n4.Logout");
			choice = Integer.parseInt(inputScanner.nextLine());
			switch (choice) {
			case 1:// Edit task status
				System.out.println("\nfollowing are projects and task list:");
				volunteerLead.viewProjects(allProjects);
				System.out.println("\nEnter projectId in which task is");
				do {
					projectId = Integer.parseInt(inputScanner.nextLine());
					if (projectId > allProjects.size() || projectId < 1) {
						System.out.println("\nPlease enter valid project Id");
					} else {
						volunteerLead.changeStatus(projectId);
						break;
					}
				} while (true);
				break;
			case 2:// View Existing Project
				if (allProjects.size() > 0)
					volunteerLead.viewProjects(allProjects);
				else {
					System.out.println("\nThere are no projects to show");
				}
				break;
			case 3:// Download RACI chart
				if (allProjects.size() == 0) {
					System.out.println("There are no existing projects!");
					break;
				}
				System.out.println("\nFollowing are the projects");
				volunteerLead.viewProjects(allProjects);
				System.out.println("\nPlease enter project id to generate raci");
				projectId = volunteerLead.getValidProjectId(allProjects);
				excel.generateRACI(projectId);
				break;
			case 4:
				return;
			default:
				System.out.println("Please Enter valid choice!\n");
			}
		} while (true);
	}

}
