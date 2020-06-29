package controller;

import java.util.List;
import java.util.Scanner;
import model.Project;
import service.ExcelGeneration;
import service.MarketingLead;
import service.ProjectData;

public class MarketingLeadWelcome {
	private ProjectData pd = new ProjectData();
	private List<Project> allProjects = pd.getAllProjects();
	// object for BoardChair
	MarketingLead marketingLead = new MarketingLead();
	// variable to store user choice
	private int choice, projectId;
	private ExcelGeneration excel = new ExcelGeneration();
	// creating scanner class object to take input
	Scanner inputScanner = new Scanner(System.in);

	// create a basic view for a boardChair
	public void mainView() {
		System.out.println("\n---Welcome to MarketingLead DashBoard---\n");
		do {
			System.out.println("\n1.Edit task status\n2.View Existing Project\n3.Download RACI chart\n4.Logout");
			choice = Integer.parseInt(inputScanner.nextLine());
			switch (choice) {
			case 1:// Edit task status
				if (allProjects.size() == 0) {
					System.out.println("\nSorry! No projects to show");
					break;
				}
				System.out.println("\nfollowing are projects and task list:");
				marketingLead.viewProjects(allProjects);
				System.out.println("\nEnter projectId in which task is");
				do {
					projectId = Integer.parseInt(inputScanner.nextLine());
					if (projectId > allProjects.size() || projectId < 1) {
						System.out.println("\nPlease enter valid project Id");
					} else {
						marketingLead.changeStatus(projectId);
						break;
					}
				} while (true);
				break;
			case 2:// View Existing Project
				if (allProjects.size() > 0)
					marketingLead.viewProjects(allProjects);
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
				marketingLead.viewProjects(allProjects);
				System.out.println("\nPlease enter project id to generate raci");
				projectId = marketingLead.getValidProjectId(allProjects);
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
