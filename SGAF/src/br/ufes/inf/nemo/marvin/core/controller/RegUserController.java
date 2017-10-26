package br.ufes.inf.nemo.marvin.core.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufes.inf.nemo.jbutler.ejb.controller.JSFController;
import br.ufes.inf.nemo.marvin.core.application.ManageUsersService;
import br.ufes.inf.nemo.marvin.core.application.RegUserService;
import br.ufes.inf.nemo.marvin.core.domain.User;
import br.ufes.inf.nemo.marvin.core.exceptions.SystemInstallFailedException;

@Named
@SessionScoped
public class RegUserController extends JSFController {

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	/** The logger. */
	private static final Logger logger = Logger.getLogger(RegUserController.class.getCanonicalName());

	/**
	 * Path to the folder where the view files (web pages) for this action are
	 * placed.
	 */
	private static final String VIEW_PATH = "/core/regUser/";

	/** The "Manage Actors" service. */
	@EJB
	private RegUserService regUserService;

	/** The "Manage Actors" service. */
	@EJB
	private ManageUsersService manageUsersService;

	User newUser;

	/** Input: the repeated password for the user registration. */
	private String repeatPassword;

	/**
	 * Displays the form for the creation of a new User.
	 * 
	 * @return The view path for the input form.
	 */

	public String create() {
		logger.log(Level.INFO, "Displaying form for user creation");

		// Resets the entity so we can create a new one.
		newUser = new User();

		// Goes to the form.
		return VIEW_PATH + "/index";

	}

	public String registerUser() {
		try {
			regUserService.registerUser(newUser);
		} catch (SystemInstallFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Goes to the index.
		return VIEW_PATH + "/done";
	}

	/**
	 * Checks if both password fields have the same value.
	 * 
	 * This method is intended to be used with AJAX.
	 */
	public void ajaxCheckPasswords() {
		checkPasswords();
	}

	/**
	 * Checks if the contents of the password fields match.
	 * 
	 * @return <code>true</code> if the passwords match, <code>false</code>
	 *         otherwise.
	 */
	private boolean checkPasswords() {
		if (((newUser != null) && (!repeatPassword.equals(newUser.getPassword())))
				|| ((newUser == null) && (newUser.getPassword() != null))) {
			logger.log(Level.INFO, "Password and repeated password are not the same");
			addGlobalI18nMessage("msgsCore", FacesMessage.SEVERITY_WARN,
					"installSystem.error.passwordsDontMatch.summary", "installSystem.error.passwordsDontMatch.detail");
			return false;
		}
		return true;
	}

	/**
	 * Analyzes the name that was given to the administrator and, if the short
	 * name field is still empty, suggests a value for it based on the given
	 * name.
	 * 
	 * This method is intended to be used with AJAX.
	 */
	public void suggestShortName() {
		// If the name was filled and the short name is still empty, suggest the
		// first name as short name.
		String name = newUser.getName();
		String shortName = newUser.getShortName();
		if ((name != null) && ((shortName == null) || (shortName.length() == 0))) {
			int idx = name.indexOf(" ");
			newUser.setShortName((idx == -1) ? name : name.substring(0, idx).trim());
			logger.log(Level.FINE, "Suggested \"{0}\" as short name for \"{1}\"",
					new Object[] { newUser.getShortName(), name });
		} else
			logger.log(Level.FINEST,
					"Short name not suggested: empty name or short name already filled (name is \"{0}\", short name is \"{1}\")",
					new Object[] { name, shortName });
	}

	public ManageUsersService getManageUsersService() {
		return manageUsersService;
	}

	public void setManageUsersService(ManageUsersService manageUsersService) {
		this.manageUsersService = manageUsersService;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public RegUserService getRegUserService() {
		return regUserService;
	}

	public void setRegUserService(RegUserService regUserService) {
		this.regUserService = regUserService;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

}
