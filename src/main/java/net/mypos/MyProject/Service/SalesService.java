package net.mypos.MyProject.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mypos.MyProject.Controller.PageController;
import net.mypos.MyProject.Model.UserModel;
import net.mypos.MyProjectBackend.dao.SalesDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Sales;
import net.mypos.MyProjectBackend.dto.Staff;

@Service("salesService")
public class SalesService {
	
	@Autowired
	private SalesDAO salesDAO;
	
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	@Autowired
	private HttpSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	// TODO : Not exactly correct implemention. Update accordingly
	public List<Sales> addSales() {
		logger.info("Adding new sale item");
		
		List <Sales> allItems = new ArrayList<Sales>();;
		int userID = ((UserModel)session.getAttribute("userModel")).getId();
		Staff staff = userinfoDAO.getStaffByPersonId(userID);
		if(staff != null) {
			Sales order = new Sales();
			order.setProductCount(2);
			order.setByingPrice(100);
			order.setTotal(200);
			order.setProductId(1);
			order.setCustomerId(1);
			order.setStaffId(staff.getId());
			order.setDiscount(0);
			order.setPaymentMethod(1);
			order.setTaxPaid(0);
			
			Date currDateTime = new Date();
			order.setDateTime(currDateTime);
			
			salesDAO.add(order);
			allItems.add(order);
		}
		
		return allItems;
	}
}
