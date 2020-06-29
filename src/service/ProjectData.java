package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Project;
import model.Task;

public class ProjectData {
	private static List<Project> allProjects = new ArrayList<>();

	/**
	 * @return the allProjects
	 */
	public List<Project> getAllProjects() {
		return allProjects;
	}

	public void addProject(Project p) {
		allProjects.add(p);
	}

	public void changeTaskStatus(int projectId, int taskId, String status) {
		this.getAllProjects().stream().filter(p -> p.getId() == projectId).collect(Collectors.toList()).get(0)
		.getTaksList().stream().filter(task -> task.getTaskId() == taskId).collect(Collectors.toList()).get(0)
		.setStatus(status);
		System.out.println("\nStatus changed successfully!");
		return;
	}

	public boolean approve(int id) {
		if (id <= allProjects.size() && id >0) {
			allProjects.stream().filter(project -> project.getId() == id).collect(Collectors.toList()).get(0)
			.setApprovalStatus(true);
			return true;
		}
		return false;
	}

	public void addTask(int projectId, Task task) {
		List<Task> list = allProjects.get(projectId - 1).getTaksList();
		list.add(task);
		allProjects.get(projectId - 1).setTaksList(list);
	}

}
