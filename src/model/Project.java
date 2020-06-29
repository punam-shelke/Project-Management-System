package model;

import java.util.ArrayList;
import java.util.List;

public class Project {
	private int id;// project ID
	private Boolean approvalStatus;// Is project approved?
	private String name;// Name of the project
	private String purpose;// Purpose of the project
	private String goals;// Goals of the project
	private List<Task> taksList = new ArrayList<>();// list of tasks to complete the project

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the approvalStatus
	 */
	public Boolean getApprovalStatus() {
		return approvalStatus;
	}

	/**
	 * @param approvalStatus the approvalStatus to set
	 */
	public void setApprovalStatus(Boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return the goals
	 */
	public String getGoals() {
		return goals;
	}

	/**
	 * @param goals the goals to set
	 */
	public void setGoals(String goals) {
		this.goals = goals;
	}

	/**
	 * @return the taksList
	 */
	public List<Task> getTaksList() {
		return taksList;
	}

	/**
	 * @param taksList the taksList to set
	 */
	public void setTaksList(List<Task> taksList) {
		this.taksList = taksList;
	}

	public Project(int id, Boolean approvalStatus, String name, String purpose, String goals, List<Task> taksList) {
		super();
		this.id = id;
		this.approvalStatus = approvalStatus;
		this.name = name;
		this.purpose = purpose;
		this.goals = goals;
		this.taksList = new ArrayList<>(taksList);
	}

	@Override
	public String toString() {
		String output = "Project Id = " + this.getId() + "\nProject Name =" + this.getName() + "\nPurpose:"
				+ this.getPurpose() + "\nGoal:" + this.getGoals() + "\nTaskList:" + this.getTaksList()
				+ "\nApproval Status:" + this.getApprovalStatus() + "\n";
		return output;
	}

}
