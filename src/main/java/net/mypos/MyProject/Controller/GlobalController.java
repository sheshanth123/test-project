package net.mypos.MyProject.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.mypos.MyProject.Model.UserModel;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Admin;
import net.mypos.MyProjectBackend.dto.Staff;
import net.mypos.MyProjectBackend.dto.Supplier;
import net.mypos.MyProjectBackend.dto.Userinfo;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	private UserModel userModel = null;
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	

	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		logger.info("Inside GlobalController::getUserModel()");
		
		if(session.getAttribute("userModel") == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Userinfo user = userinfoDAO.getbyEmail(auth.getName());
			if(user != null)
			{
				logger.info("user found");
				logger.info(user.getFirstName());
				logger.info(user.getRole());
				
				// Create a new user-model object to pass the user details.
				userModel = new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + " " + user.getLastName());
				
				/*if(user.getRole() == "STAFF") 
				{
					logger.info("Inside STAFF");
					userModel.setStaff(userinfoDAO.getStaffByPersonId(user.getId()));
				}
				else if(user.getRole() == "ADMIN")
				{
					userModel.setAdmin(userinfoDAO.getAdminByPersonId(user.getId()));
				}
				else if(user.getRole() == "SUPPLIER"){
					userModel.setSupplier(userinfoDAO.getSupplierByPersonId(user.getId()));
				}*/
				
				session.setAttribute("userModel", userModel);
				return userModel;
			}
		}
		
		return (UserModel)session.getAttribute("userModel");
	}
}
