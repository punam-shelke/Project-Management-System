package controller;

import java.util.List;
import java.util.Scanner;
import service.BoardMember;
import service.ExcelGeneration;
import model.Project;
import service.ProjectData;

public class BoardMemberWelcome {
	private ProjectData pd = new ProjectData();
	private List<Project> allProjects = pd.getAllProjects();
	// object for BoardChair
	BoardMember boardMember = new BoardMember();
	// variable to store user choice
	private int choice, projectId;
	private ExcelGeneration excel = new ExcelGeneration();
	// creating scanner class object to take input
	Scanner inputScanner = new Scanner(System.in);

	//create a basic view for a boardChair
	public void mainView() {
		System.out.println("\n---Welcome to Board-Member DashBoard---\n");
		do {
			System.out.println(
					"\n1.Approve a project\n2.Edit Existing project\n3.View Existing Project\n4.Download RACI chart\n5.Logout");
			choice = Integer.parseInt(inputScanner.nextLine());
			switch (choice) {
			case 1:// Approve a project
				boardMember.approveProject();
				break;
			case 2:// Edit Existing project;
				boardMember.editProjects();
				break;
			case 3:// View Existing Project
				if (allProjects.size() > 0)
					boardMember.viewProjects(allProjects);
				else {
					System.out.println("\nThere are no projects to show");
				}
				break;
			case 4:// Download RACI chart
				if (allProjects.size() == 0) {
					System.out.println("There are no existing projects!");
					break;
				}
				System.out.println("\nFollowing are the projects");
				boardMember.viewProjects(allProjects);
				System.out.println("\nPlease enter project id to generate raci");
				projectId = boardMember.getValidProjectId(allProjects);
				;
				excel.generateRACI(projectId);
				break;
			case 5:
				return;
			default:
				System.out.println("Please Enter valid choice!\n");
			}
		} while (true);
	}

}
