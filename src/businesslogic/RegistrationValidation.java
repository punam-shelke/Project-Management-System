package businesslogic;

import java.util.regex.Pattern;
import service.GeneralUser;

public class RegistrationValidation {

	public boolean checkUserDetails(String email, String password, String confirmPassword) {
		if (validEmail(email) && validPassword(password, confirmPassword)) {
			return true;
		}
		return false;
	}

	private boolean validPassword(String password, String confirmPassword) {
		Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
		Pattern lowerCasePatten = Pattern.compile("[a-z ]");
		Pattern digitCasePatten = Pattern.compile("[0-9 ]");
		boolean flag = true;

		if (!password.equals(confirmPassword)) {
			flag = false;
		}
		if (password.length() < 8 || password.length() > 20) {
			flag = false;
		}
		if (!specailCharPatten.matcher(password).find()) {
			flag = false;
		}
		if (!UpperCasePatten.matcher(password).find()) {
			flag = false;
		}
		if (!lowerCasePatten.matcher(password).find()) {
			flag = false;
		}
		if (!digitCasePatten.matcher(password).find()) {
			flag = false;
		}

		return flag;
	}

	private boolean validEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}

	public boolean signupCheck(GeneralUser generalUser, String email, String password) {
		if (generalUser.getUserDetails().containsKey(email)) {
			System.out.println("Sorry:(This mail is already in use.\n");
			return false;
		} else {
			System.out.println("Registration successful:)");
			return true;
		}
	}

	public boolean loginCheck(GeneralUser generalUser, String email, String password) {
		if (generalUser.getUserDetails().containsKey(email)) {
			System.out.println("Stored password = " + generalUser.getUserDetails().get(email));
			if (generalUser.getUserDetails().get(email).equals(password)) {
				System.out.println("Authentication Successful");
				return true;
			} else {
				System.out.println("Password Mismatch");
				return false;
			}
		} else {
			System.out.println("Email not registered!");
			return false;
		}
	}
}