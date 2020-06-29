package controller;

import java.util.List;
import java.util.Scanner;
import model.Project;
import service.ExcelGeneration;
import service.ProjectData;
import service.ProjectManager;

public class ProjectManagerWelcome {
	private ProjectData pd = new ProjectData();
	private List<Project> allProjects = pd.getAllProjects();
	// object for BoardChair
	ProjectManager projectManager = new ProjectManager();
	// variable to store user choice
	private int choice, projectId;
	private ExcelGeneration excel = new ExcelGeneration();
	// creating scanner class object to take input
	Scanner inputScanner = new Scanner(System.in);

	// create a basic view for a boardChair
	public void mainView() {
		System.out.println("\n---Welcome to Project-Manager DashBoard---\n");
		do {
			System.out.println("\n1.Edit Existing project\n2.View Existing Project\n3.Download RACI chart\n4.Logout");
			choice = Integer.parseInt(inputScanner.nextLine());
			switch (choice) {
			case 1:// Edit Existing project;
				projectManager.editProjects();
				break;
			case 2:// View Existing Project
				if (allProjects.size() > 0)
					projectManager.viewProjects(allProjects);
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
				projectManager.viewProjects(allProjects);
				System.out.println("\nPlease enter project id to generate raci");
				projectId = projectManager.getValidProjectId(allProjects);
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
