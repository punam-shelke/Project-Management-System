package controller;

import java.io.IOException;
import java.util.Scanner;
import businesslogic.RegistrationValidation;
import service.BoardChair;
import service.BoardMember;
import service.MarketingLead;
import service.ProjectManager;
import service.VolunteerLead;

public class Welcome {
	// creating scanner class object to take input
	Scanner inputScanner = new Scanner(System.in);
	// fields to enter the choices
	int choice1, choice2;
	// fields to store input
	String email, password, confirmPassword;
	// creating RegistrationValidation object
	RegistrationValidation validator = new RegistrationValidation();
	// creating object for Users of different designation
	// GeneralUser generalUser = new GeneralUser();
	BoardChair boardChair = new BoardChair();
	BoardMember boardMember = new BoardMember();
	MarketingLead marketingLead = new MarketingLead();
	ProjectManager projectManager = new ProjectManager();
	VolunteerLead volunteerLead = new VolunteerLead();
	// creating controller object of designation dashBoard
	BoardChairWelcome boardChairWelcome = new BoardChairWelcome();
	BoardMemberWelcome boardMemberWelcome = new BoardMemberWelcome();
	ProjectManagerWelcome projectManagerWelcome = new ProjectManagerWelcome();
	MarketingLeadWelcome marketingLeadWelcome = new MarketingLeadWelcome();
	VolunteerLeadWelcome volunteerLeadWelcome = new VolunteerLeadWelcome();

	// Main method
	public static void main(String[] args) {
		// Declaring boolean variable
		boolean changeDesignation = false;
		// creating Welcome class object
		Welcome welcome = new Welcome();
		// Welcome message
		System.out.println("----------------------------------------------------------------------");
		System.out.println("==============> Welcome to Project Management System <==================");
		System.out.println("----------------------------------------------------------------------");
		do {
			// setting changeDesignation to false
			changeDesignation = false;

			// Asking for designation
			System.out.println(
					"Choose Your Designation\n1.Board Chair\n2.Board Member\n3.Project Manager\n4.Marketing Lead\n5.Volunteer Lead\n6.Exit");
			// Taking input using inputScanner
			welcome.choice1 = welcome.inputScanner.nextInt();
			switch (welcome.choice1) {
			case 1:// Board Chair
			case 2:// Board Member
			case 3:// Project Manager
			case 4:// Volunteer Lead
			case 5:// Marketing Lead
				do {// Checking if user is existing or new user
					System.out.println("Do you want to\n1.Signup\n2.Login\n3.Change Designation\n4.Exit");
					// Taking input using inputScanner
					welcome.choice2 = welcome.inputScanner.nextInt();
					// directing user according to choice
					switch (welcome.choice2) {
					case 1:// Signup
						/*
						 * Taking email as input Taking password as input Taking confirmPassword as
						 * input
						 */
						System.out.println("Enter Your Email:\n");
						welcome.email = welcome.inputScanner.next();
						System.out.println("Enter Your Password:\n");
						welcome.password = welcome.inputScanner.next();
						System.out.println("Confirm Your Password:\n");
						welcome.confirmPassword = welcome.inputScanner.next();
						/* validating email , password and confirmPassword */
						if (!welcome.validator.checkUserDetails(welcome.email, welcome.password,
								welcome.confirmPassword)) {
							System.out.println(
									"Please enter valid email or password!(Password should contain atleast one capital , number and special character and should be atleast 8 characters long!)");
						} else {
							// get the view options according to the designation
							try {
								welcome.loadView(welcome.choice1, welcome.choice2);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						// call the code to login and pass the designation.
						break;
					case 2:// Login
						System.out.println("Enter Your Email:\n");
						welcome.email = welcome.inputScanner.next();
						System.out.println("Enter Your Password:\n");
						welcome.password = welcome.inputScanner.next();
						// get the view options according to the designation
						try {
							welcome.loadView(welcome.choice1, welcome.choice2);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					case 3:
						changeDesignation = true;
						break;
					case 4:
						// goodbye message and exiting the program
						welcome.exitMessage();
					default:
						System.out.println("Please Enter valid choice!\n");
					}
				} while (!changeDesignation);
				break;
			case 6:
				// goodbye message and exiting the program
				welcome.exitMessage();
			default:
				System.out.println("Please Enter valid choice!\n");
			}
		} while (true);
	}

	public void loadView(int choice1, int choice2) throws IOException {
		switch (choice1) {
		case 1:// Board Chair
				// use choice 2 to check for credentials
			if (choice2 == 1 && validator.signupCheck(boardChair, email, password)) {
				// add the user
				boardChair.addUser(email, password);
				System.out.println("-----loading Board Chair view-------");
				boardChairWelcome.mainView();
			} else if (choice2 == 2 && validator.loginCheck(boardChair, email, password)) {
				System.out.println("-----loading Board Chair view-------");
				boardChairWelcome.mainView();
			}
			break;
		case 2:// Board Member
			if (choice2 == 1 && validator.signupCheck(boardMember, email, password)) {
				boardMember.addUser(email, password);
				System.out.println("-----loading Board Member view-------");
				boardMemberWelcome.mainView();
			} else if (choice2 == 2 && validator.loginCheck(boardMember, email, password)) {
				System.out.println("-----loading Board Member view-------");
				boardMemberWelcome.mainView();
			}
			break;
		case 4:// Marketing Lead
			if (choice2 == 1 && validator.signupCheck(marketingLead, email, password)) {
				marketingLead.addUser(email, password);
				System.out.println("-----loading Marketing Lead view-------");
				marketingLeadWelcome.mainView();
			} else if (choice2 == 2 && validator.loginCheck(boardMember, email, password)) {
				System.out.println("-----loading Marketing Lead view-------");
				marketingLeadWelcome.mainView();
			}
			break;
		case 5:// Volunteer Lead
			if (choice2 == 1 && validator.signupCheck(volunteerLead, email, password)) {
				volunteerLead.addUser(email, password);
				System.out.println("-----loading Volunteer Lead view-------");
				volunteerLeadWelcome.mainView();
			} else if (choice2 == 2 && validator.loginCheck(boardMember, email, password)) {
				System.out.println("-----loading Volunteer Lead view-------");
				volunteerLeadWelcome.mainView();
			}
			break;
		case 3:// Project Manager
			if (choice2 == 1 && validator.signupCheck(projectManager, email, password)) {
				projectManager.addUser(email, password);
				System.out.println("-----loading Project Manager view-------");
				projectManagerWelcome.mainView();
			} else if (choice2 == 2 && validator.loginCheck(boardMember, email, password)) {
				System.out.println("-----loading Project Manager view-------");
				projectManagerWelcome.mainView();
			}
			break;
		}
	}

	public void exitMessage() {
		inputScanner.close();
		System.out.println("----------------------------------------------------------------------");
		System.out.println("<==============> Thank you for visiting! <==================>");
		System.out.println("----------------------------------------------------------------------");
		System.exit(0);

	}

}
