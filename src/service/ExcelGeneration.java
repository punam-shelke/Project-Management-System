package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.Project;
import model.Task;

public class ExcelGeneration {
	private ProjectData projectData = new ProjectData();
	private Project project;
	private String filename, projectStatus;
	private File file;
	private Row row;
	private Cell cell;
	private HSSFWorkbook hwb;
	private FileOutputStream out;
	private FileInputStream in;
	private int rowNumber, taskNumber;
	private List<Task> tasklist;
	private Set<Integer> responsible, consult, inform;
	private int accountable;
	private List<String> rowData;
	private HSSFSheet sheet;
	private boolean flag;

	public void generateRACI(int projectId) {
		/*
		 * calling function will pass the project id it will be already verified you
		 * need to get the project using project id using filter then check if file with
		 * project name exist or not if not create one and all the tasks and RACI
		 * details to it if it already exist then check how many rows are filled and see
		 * if rows==tasks in project if they are equal then file is already up to date
		 * and display the same if rows are lesser then tasks have been added and modify
		 * the excel
		 */
		flag = false;
		project = projectData.getAllProjects().stream().filter(p -> p.getId() == projectId).collect(Collectors.toList())
				.get(0);
		filename = "/home/academic/Desktop/" + project.getName() + ".xlsx";
		file = new File(filename);
		if (file.exists()) {
			System.out.println("File already exist\n");
			try {
				in = new FileInputStream(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				hwb = new HSSFWorkbook(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = hwb.getSheetAt(0);
			rowNumber = sheet.getLastRowNum();
			// check the project approval change
			projectStatus = sheet.getRow(0).getCell(2).getStringCellValue();
			if (!projectStatus.equals(project.getApprovalStatus().toString())) {
				flag = true;
				sheet.getRow(0).getCell(2).setCellValue(project.getApprovalStatus().toString());
				System.out.println("\nProject Status updated!");
			}

			// check status change for existing task
			for (int i = 2; i <= rowNumber; i++) {
				row = sheet.getRow(i);
				cell = row.getCell(6);
				if (!cell.getStringCellValue().equals(project.getTaksList().get(i - 2).getStatus())) {
					flag = true;
					cell.setCellValue(project.getTaksList().get(i - 2).getStatus());
					System.out.println("\nTask Status updated!");
				}
			}
			// add the new tasks
			tasklist = project.getTaksList();
			taskNumber = rowNumber - 1;
			for (Task task : tasklist) {
				if (task.getTaskId() <= taskNumber)
					continue;
				responsible = task.getResponsible();
				consult = task.getConsult();
				inform = task.getInform();
				accountable = task.getAccountable();
				rowData = createRowData(responsible, accountable, consult, inform);
				row = sheet.createRow(task.getTaskId() + 1);
				cell = row.createCell(0);
				cell.setCellValue(task.getTaskName());
				cell = row.createCell(1);
				cell.setCellValue(rowData.get(0));
				cell = row.createCell(2);
				cell.setCellValue(rowData.get(1));
				cell = row.createCell(3);
				cell.setCellValue(rowData.get(2));
				cell = row.createCell(4);
				cell.setCellValue(rowData.get(3));
				cell = row.createCell(5);
				cell.setCellValue(rowData.get(4));
				cell = row.createCell(6);
				cell.setCellValue(task.getStatus());
				cell = row.createCell(7);
				cell.setCellValue(task.getDeadline().toString());
				System.out.println("\nTasks added!");
				flag = true;
			}
			try {
				out = new FileOutputStream(filename);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				hwb.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!flag) {
				System.out.println("\nFile is already up-to date");
			}
		} else {
			try {
				System.out.println("\nCreating new excel!");
				hwb = new HSSFWorkbook();
				sheet = hwb.createSheet(project.getName());
				row = sheet.createRow(0);
				cell = row.createCell(1);
				cell.setCellValue("Project Approval");
				cell = row.createCell(2);
				cell.setCellValue(project.getApprovalStatus().toString());
				row = sheet.createRow(1);
				cell = row.createCell(1);
				cell.setCellValue("Board Chair");
				cell = row.createCell(2);
				cell.setCellValue("Board Member");
				cell = row.createCell(3);
				cell.setCellValue("Project Manager");
				cell = row.createCell(4);
				cell.setCellValue("Marketing Lead");
				cell = row.createCell(5);
				cell.setCellValue("Volunteer Lead");
				cell = row.createCell(6);
				cell.setCellValue("Status");
				cell = row.createCell(7);
				cell.setCellValue("Deadline");
				tasklist = project.getTaksList();
				// taskNumber = tasklist.size();
				int i = 2;
				for (Task task : tasklist) {
					responsible = task.getResponsible();
					consult = task.getConsult();
					inform = task.getInform();
					accountable = task.getAccountable();
					rowData = createRowData(responsible, accountable, consult, inform);
					row = sheet.createRow(i);
					cell = row.createCell(0);
					cell.setCellValue(task.getTaskName());
					cell = row.createCell(1);
					cell.setCellValue(rowData.get(0));
					cell = row.createCell(2);
					cell.setCellValue(rowData.get(1));
					cell = row.createCell(3);
					cell.setCellValue(rowData.get(2));
					cell = row.createCell(4);
					cell.setCellValue(rowData.get(3));
					cell = row.createCell(5);
					cell.setCellValue(rowData.get(4));
					cell = row.createCell(6);
					cell.setCellValue(task.getStatus());
					cell = row.createCell(7);
					cell.setCellValue(task.getDeadline().toString());
					i++;
				}
				out = new FileOutputStream(filename);
				hwb.write(out);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private List<String> createRowData(Set<Integer> responsible, int accountable, Set<Integer> consult,
			Set<Integer> inform) {
		List<String> rowValues = new ArrayList<>();
		String boardChair, boardMember, projectManager, marketingLead, volunteerLead;
		boardChair = createCellData(responsible, accountable, consult, inform, 1);
		boardMember = createCellData(responsible, accountable, consult, inform, 2);
		projectManager = createCellData(responsible, accountable, consult, inform, 3);
		marketingLead = createCellData(responsible, accountable, consult, inform, 4);
		volunteerLead = createCellData(responsible, accountable, consult, inform, 5);
		rowValues.add(boardChair);
		rowValues.add(boardMember);
		rowValues.add(projectManager);
		rowValues.add(marketingLead);
		rowValues.add(volunteerLead);
		return rowValues;
	}

	private String createCellData(Set<Integer> responsible, int accountable, Set<Integer> consult, Set<Integer> inform,
			int cellNumber) {
		String cellData = "";
		if (responsible.contains(cellNumber))
			cellData += "R ";
		if (accountable == cellNumber)
			cellData += "A ";
		if (consult.contains(cellNumber))
			cellData += "C ";
		if (inform.contains(cellNumber))
			cellData += "I ";
		return cellData;
	}
}
