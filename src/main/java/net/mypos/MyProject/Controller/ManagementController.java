package net.mypos.MyProject.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dao.SubcategoryDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Subcategory;
import net.mypos.MyProjectBackend.dto.Userinfo;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CatergoryDAO categoryDAO;
	
	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private UserinfoDAO userinfoDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation) {
		
		// Create a new Product.
		Product newProduct = new Product();
		newProduct.setSupplier_id(1);
		newProduct.setIs_active(true);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageProducts",true);
		mv.addObject("title","Manage Products");
		mv.addObject("product", newProduct);
		
		if(operation != null) {
			if(operation.equals("product")){
				mv.addObject("message", "Product saved successfully..");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
			
		// Create a new Product.
		Product newProduct = productDAO.get(id);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageProducts",true);
		mv.addObject("title","Manage Products");
		
		// set the product fetch from database
		mv.addObject("product", newProduct);
		return mv;
	} 
	
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product modifiedProduct, BindingResult results, Model model) {
		
		logger.info(modifiedProduct.toString());
		
		// Check for errors.
		if(results.hasErrors()) {
			model.addAttribute("userClickedManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for Product Submited!!");
			return "page";
		}
		
		if(modifiedProduct.getId() == 0) {
			// id = 0 means, product doesn't exists. Hence add a new one. 
			productDAO.add(modifiedProduct);
		}
		else {
			productDAO.update(modifiedProduct);
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		
		Product extProduct = productDAO.get(id);
		boolean isAlredyActive = extProduct.isIs_active();
		extProduct.setIs_active(!isAlredyActive);
		productDAO.update(extProduct);
		
		return isAlredyActive ? "You have succesfully Deactivated the product with id " + id : "You have succesfully Aactivated the product with id " + id; 
	}
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
	}
	
	@ModelAttribute("subcategories")
	public List<Subcategory> getSubcategories(){
		return subcategoryDAO.listActiveSubcategories();
	}

	
	//-------------------------- Manage Categories -----------------------------------------
	@RequestMapping(value="/categories", method=RequestMethod.GET)
	public ModelAndView showManageCategory(@RequestParam(name="operation", required=false) String operation) {
		// Create a new category.
		Category newCategory = new Category();
		newCategory.setActive(true);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageCategory",true);
		mv.addObject("title","Manage Category");
		mv.addObject("category", newCategory);
		
		if(operation != null) {
			if(operation.equals("category")){
				mv.addObject("message", "Category saved successfully..");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/categories", method=RequestMethod.POST)
	public String handleCategorySubmission(@Valid @ModelAttribute("category") Category modifiedCategory, BindingResult results, Model model) {
		
		logger.info(modifiedCategory.toString());
		
		// Check for errors.
		if(results.hasErrors()) {
			model.addAttribute("userClickedManageCategory",true);
			model.addAttribute("title","Manage Category");
			model.addAttribute("message","Validation failed for Category Submited!!");
			return "page";
		}
		
		if(modifiedCategory.getId() == 0) {
			// id = 0 means, product doesn't exists. Hence add a new one. 
			categoryDAO.add(modifiedCategory);
		}
		else {
			categoryDAO.udpate(modifiedCategory);
		}
		
		return "redirect:/manage/categories?operation=category";
	}
	
	
	@RequestMapping(value="/{id}/categories", method=RequestMethod.GET)
	public ModelAndView showEditCategory(@PathVariable int id) {	
		// Fetch the existing Category using id.
		Category newCategory = categoryDAO.get(id);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageCategory",true);
		mv.addObject("title","Manage Category");
		
		// set the product fetch from database
		mv.addObject("category", newCategory);
		return mv;
	} 
	
	
	//-------------------------- Manage SubCategories -----------------------------------------
	@RequestMapping(value="/subcategories", method=RequestMethod.GET)
	public ModelAndView showManageSubCategory(@RequestParam(name="operation", required=false) String operation) {
		// Create a new sub-category.
		Subcategory newSubCategory = new Subcategory();
		newSubCategory.setActive(true);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageSubCategory",true);
		mv.addObject("title","Manage SubCategory");
		mv.addObject("subcategory", newSubCategory);
		
		if(operation != null) {
			if(operation.equals("subcategory")){
				mv.addObject("message", "SubCategory saved successfully..");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/subcategories", method=RequestMethod.POST)
	public String handleSubCategorySubmission(@Valid @ModelAttribute("subcategory") Subcategory modifiedSubCategory, BindingResult results, Model model) {
		
		logger.info(modifiedSubCategory.toString());
		
		// Check for errors.
		if(results.hasErrors()) {
			model.addAttribute("userClickedManageSubCategory",true);
			model.addAttribute("title","Manage SubCategory");
			model.addAttribute("message","Validation failed for SubCategory Submited!!");
			return "page";
		}
		
		if(modifiedSubCategory.getId() == 0) {
			// id = 0 means, sub-category doesn't exists. Hence add a new one. 
			subcategoryDAO.add(modifiedSubCategory);
		}
		else {
			subcategoryDAO.udpate(modifiedSubCategory);
		}
		
		return "redirect:/manage/subcategories?operation=subcategory";
	}
	
	@RequestMapping(value="/{id}/subcategories", method=RequestMethod.GET)
	public ModelAndView showEditSubCategory(@PathVariable int id) {	
		// Fetch the existing Sub-Category using id.
		Subcategory newSubCategory = subcategoryDAO.get(id);
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageSubCategory",true);
		mv.addObject("title","Manage SubCategory");
		
		// set the product fetch from database
		mv.addObject("subcategory", newSubCategory);
		return mv;
	} 
	
	
	//-------------------------- Manage Users -----------------------------------------
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ModelAndView showManageUser(@RequestParam(name="operation", required=false) String operation) {
		// Create a new category.
		Userinfo newUserinfo = new Userinfo();
		newUserinfo.setEnabled(true);
        
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageUser",true);
		mv.addObject("title","Manage User");
		mv.addObject("user", newUserinfo);
		
		if(operation != null) {
			if(operation.equals("user")){
				mv.addObject("message", "User saved successfully..");
			}
		}
		
		return mv;
	}
	
	@RequestMapping(value="/users", method=RequestMethod.POST)
	public String handleUserSubmission(@Valid @ModelAttribute("user") Userinfo modifiedUserinfo, BindingResult results, Model model) {
		
		logger.info("handleUserSubmission");
		logger.info(modifiedUserinfo.toString());
		
		// Check for errors.
		if(results.hasErrors()) {
			model.addAttribute("userClickedManageUser",true);
			model.addAttribute("title","Manage User");
			model.addAttribute("message","Validation failed for User Submited!!");
			return "page";
		}
		
		// Handle password encryption.
		modifiedUserinfo.setPassword(passwordEncoder.encode(modifiedUserinfo.getPassword()));
		
		// id = 0 means, user doesn't exists. Hence add a new one,else update the existing one
		if(modifiedUserinfo.getId() == 0) {
			userinfoDAO.add(modifiedUserinfo, modifiedUserinfo.getRole());
		}
		else {
			userinfoDAO.update(modifiedUserinfo);
		}
		
		return "redirect:/manage/users?operation=user";
	}
	
	@RequestMapping(value="/user/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleUserActivation(@PathVariable int id) {
		
		Userinfo extUser = userinfoDAO.get(id);
		boolean isAlredyEnabled = extUser.getEnabled();
		extUser.setEnabled(!isAlredyEnabled);
		userinfoDAO.update(extUser);
		
		return isAlredyEnabled ? "You have succesfully Disabled the user with id " + id : "You have succesfully Enabled the user with id " + id; 
	}
	
	@RequestMapping(value="/{id}/users", method=RequestMethod.GET)
	public ModelAndView showEditUser(@PathVariable int id) {	

		logger.info("showEditUser");
		
		// Get existing user
		Userinfo existingUserinfo = userinfoDAO.get(id);
		
		logger.info(existingUserinfo.toString());
		
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickedManageUser",true);
		mv.addObject("title","Manage User");
		
		mv.addObject("user", existingUserinfo);
		return mv;
	} 
}
