package net.mypos.MyProject.Controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.mypos.MyProject.Model.UserModel;
import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dao.SalesDAO;
import net.mypos.MyProjectBackend.dao.SubcategoryDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Sales;
import net.mypos.MyProjectBackend.dto.Staff;
import net.mypos.MyProjectBackend.dto.Subcategory;
import net.mypos.MyProjectBackend.dto.Userinfo;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CatergoryDAO categoryDAO;
	
	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	@Autowired
	private SalesDAO salesDAO;
	
	@Autowired
	private HttpSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);	
	
	// Returning only the active products - For user.
	@RequestMapping("/all/products")
	@ResponseBody	
	public List<Product> getAllProducts(){
		return productDAO.listActiveProducts();
	}
	
	// Returning all the products( active/inactive) - For Admin
	@RequestMapping("/admin/all/products")
	@ResponseBody	
	public List<Product> getAllProductsForAdmin(){
		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getAllProductsByCategory(@PathVariable int id){
		return productDAO.listActiveProductsByCategory(id);
	}
	
	// Returning all the categories - For Admin
	@RequestMapping("/admin/all/categories")
	@ResponseBody	
	public List<Category> getAllCategoriesForAdmin(){
		return categoryDAO.list();
	}
	
	// Returning all the sub-categories - For Admin
	@RequestMapping("/admin/all/subcategories")
	@ResponseBody	
	public List<Subcategory> getAllSubCategoriesForAdmin(){
		return subcategoryDAO.listActiveSubcategories();
	}
	
	@RequestMapping("/admin/all/users")
	@ResponseBody
	public List<Userinfo> getAllListForAdmin(){
		return userinfoDAO.list();
	}
	
	@RequestMapping("/search/products")
	@ResponseBody
	public List<Product> getAllProductsByCode(@RequestParam(name="item=", required=true) String code){
		logger.info("Code : ", code);
		return productDAO.listActiveProductsByCode(code);
	}
	
	@RequestMapping("/sales/today")
	@ResponseBody
	public List<Sales> getTodaysSalesHistory(){
		UserModel usermodel = ((UserModel)session.getAttribute("userModel"));
		if(usermodel.getRole().equals("STAFF")) {
			int userID = usermodel.getId();
			Staff staff = userinfoDAO.getStaffByPersonId(userID);
			if(staff == null) {
				return Collections.emptyList();
			}
			
			return salesDAO.getTodaysSalesById(staff.getId());
		}
		else if(usermodel.getRole().equals("ADMIN")) {
			return salesDAO.getTodaysSales();
		}
		else {
			return Collections.emptyList();
		}
	}
	
	@RequestMapping("/sales/complete")
	@ResponseBody
	public List<Sales> getCompleteSalesHistory(){
		UserModel usermodel = ((UserModel)session.getAttribute("userModel"));
		if(usermodel.getRole().equals( "STAFF")) {
			int userID = usermodel.getId();
			Staff staff = userinfoDAO.getStaffByPersonId(userID);
			if(staff == null) {
				return Collections.emptyList();
			}
			
			return salesDAO.getCompleteSalesById(staff.getId());
		}
		else if(usermodel.getRole().equals( "ADMIN")) {
			return salesDAO.getCompleteSales();
		}
		else {
			return Collections.emptyList();
		}
	}
	
}
