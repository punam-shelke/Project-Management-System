package controller;

import java.util.List;
import java.util.Scanner;
import service.BoardChair;
import service.ExcelGeneration;
import model.Project;
import service.ProjectData;

public class BoardChairWelcome {
	private ProjectData pd = new ProjectData();
	private List<Project> allProjects = pd.getAllProjects();
	private ExcelGeneration excel = new ExcelGeneration();
	// object for BoardChair
	BoardChair boardChair = new BoardChair();
	// variable to store user choice
	int choice, projectId;
	int i = 0;
	// creating scanner class object to take input
	Scanner inputScanner = new Scanner(System.in);

	// create a basic view for a boardChair
	public void mainView() {
		System.out.println("\n---Welcome to Board-Chair DashBoard---\n");
		do {
			System.out.println(
					"\n1.Create a Project\n2.Approve a project\n3.Edit Existing project\n4.View Existing Project\n5.Download RACI chart\n6.Logout");
			choice = Integer.parseInt(inputScanner.nextLine());
			switch (choice) {
			case 1:// Create a Project
				boardChair.createProject();
				break;
			case 2:// Approve a project
				boardChair.approveProject();
				break;
			case 3:// Edit Existing project;
				boardChair.editProjects();
				break;
			case 4:// View Existing Project
				if (allProjects.size() > 0)
					boardChair.viewProjects(allProjects);
				else {
					System.out.println("\nThere are no projects to show");
				}
				break;
			case 5:// Download RACI chart
				if (allProjects.size() == 0) {
					System.out.println("There are no existing projects!");
					break;
				}
				System.out.println("\nFollowing are the projects");
				boardChair.viewProjects(allProjects);
				System.out.println("\nPlease enter project id to generate raci");
				projectId = boardChair.getValidProjectId(allProjects);
				;
				excel.generateRACI(projectId);
				break;
			case 6:
				return;
			default:
				System.out.println("Please Enter valid choice!\n");
			}
		} while (true);
	}

}
