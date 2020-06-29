package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Task {
	int taskId;
	String taskName;
	LocalDate deadline;
	LocalDate createdAt;
	Set<Integer> responsible = new HashSet<>();
	Set<Integer> consult = new HashSet<>();
	Set<Integer> inform = new HashSet<>();
	int accountable;
	String status;

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the deadline
	 */
	public LocalDate getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline the deadline to set
	 */
	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the responsible
	 */
	public Set<Integer> getResponsible() {
		return responsible;
	}

	/**
	 * @param responsible the responsible to set
	 */
	public void setResponsible(Set<Integer> responsible) {
		this.responsible = responsible;
	}

	/**
	 * @return the consult
	 */
	public Set<Integer> getConsult() {
		return consult;
	}

	/**
	 * @param consult the consult to set
	 */
	public void setConsult(Set<Integer> consult) {
		this.consult = consult;
	}

	/**
	 * @return the inform
	 */
	public Set<Integer> getInform() {
		return inform;
	}

	/**
	 * @param inform the inform to set
	 */
	public void setInform(Set<Integer> inform) {
		this.inform = inform;
	}

	/**
	 * @return the accountable
	 */
	public int getAccountable() {
		return accountable;
	}

	/**
	 * @param accountable the accountable to set
	 */
	public void setAccountable(int accountable) {
		this.accountable = accountable;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public Task(int taskId, String taskName, LocalDate deadline, LocalDate createdAt, Set<Integer> responsible,
			Set<Integer> consult, Set<Integer> inform, int accountable, String status) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.deadline = deadline;
		this.createdAt = createdAt;
		this.responsible = new HashSet<Integer>(responsible);
		this.consult =new HashSet<Integer>(consult);
		this.inform = new HashSet<Integer>(inform);
		this.accountable = accountable;
		this.status = status;
	}

	@Override
	public String toString() {
		String output = "Task Id = " + this.getTaskId() + "\nTask Name =" + this.getTaskName() + "\nDeadline:"
				+ this.getDeadline() + "\nCreated At:" + this.getCreatedAt() + "\nstatus" + this.getStatus() + "\n";
		return output;
	}

}
