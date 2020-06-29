package service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.BoardMemberInterface;
import model.Project;

public class BoardMember extends ProjectManager implements BoardMemberInterface {
	Scanner inputScanner = new Scanner(System.in);
	int projectId;
	ProjectData projectData = new ProjectData();
	List<Project> allProjects;
	List<Project> filtered;

	@Override
	public void approveProject() {
		allProjects = projectData.getAllProjects();
		filtered = allProjects.stream().filter(project -> project.getApprovalStatus() == false)
				.collect(Collectors.toList());
		if (filtered.size() == 0) {
			System.out.println("There are no pending approvals\n");
			return;
		}
		System.out.println("Following projects need approval\n");
		viewProjects(filtered);
		// ask project id for approval
		System.out.println("Please enter Id of project to be approved:");
		do {// pass this Id to approve the project
			projectId = Integer.parseInt(inputScanner.nextLine());
			if (projectData.approve(projectId)) {
				System.out.println("Project approved successfully!");
				break;
			} else {
				System.out.println("Please enter valid projectId");
			}
		} while (true);

	}
}
