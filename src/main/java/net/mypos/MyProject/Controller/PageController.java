package net.mypos.MyProject.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProject.Service.CartService;
import net.mypos.MyProject.exception.ProductNotFoundException;
import net.mypos.MyProjectBackend.dao.CatergoryDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dao.SubcategoryDAO;
import net.mypos.MyProjectBackend.dao.UserinfoDAO;
import net.mypos.MyProjectBackend.daoimpl.UserinfoDAOImpl;
import net.mypos.MyProjectBackend.dto.Admin;
import net.mypos.MyProjectBackend.dto.Cart;
import net.mypos.MyProjectBackend.dto.CartLine;
import net.mypos.MyProjectBackend.dto.Category;
import net.mypos.MyProjectBackend.dto.Product;
import net.mypos.MyProjectBackend.dto.Subcategory;
import net.mypos.MyProjectBackend.dto.Userinfo;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	private static final String SecurityContextHold = null;
	
	@Autowired
	CartService cartService;

	@Autowired
	private CatergoryDAO categoryDAO;

	@Autowired
	private SubcategoryDAO subcategoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private UserinfoDAO userinfoDAO;
	
	//@Autowired
	//private SettingsDAO settingsDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickedHome", true);
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("productList",productDAO.listActiveProducts());
		
		//Settings curSettings = settingsDAO.get();
		mv.addObject("bOrderItems", true);
		//mv.addObject("ShopName", curSettings.getShopName());
		mv.addObject("currencyIcon", "&#163;");
		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about(HttpSession session) {
		int t = (int) session.getAttribute("test");
		Integer test1 = t;

		logger.info(session.getId());
		logger.info(test1.toString());
		logger.info("in about");
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickedAbout", true);
		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact(HttpSession session) {
		int t = 100;
		session.setAttribute("test", t);

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickedContact", true);
		return mv;
	}
	
	@RequestMapping(value = { "/history" })
	public ModelAndView history() {
		ModelAndView mv = new ModelAndView("history");
		mv.addObject("title", "History");
		return mv;
	}
	
	@RequestMapping(value = { "/settings" })
	public ModelAndView showSettings() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "History");
		mv.addObject("userClickedSettings", true);
		return mv;
	}
	
	
	@RequestMapping(value = "get/categories", method = RequestMethod.GET)
	@ResponseBody
    public String getCategories() {
		logger.info("Retrieving categories..");
		List<Category> categories = categoryDAO.list();
		logger.info("Total Categories retrieved : " + categories.size());
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for(Category categoryItem : categories)
		{
			JSONObject json = new JSONObject();
			json.put("id", categoryItem.getId());
			json.put("name",categoryItem.getName());
			jsonList.add(json);
		}
		return jsonList.toString();
	}
	
	@RequestMapping(value = "get/subcategories", method = RequestMethod.GET)
	@ResponseBody
    public String getSubCategories(@ModelAttribute("id") int id) {
		logger.info("Category id is " + id);
		List<Subcategory> subcategories = subcategoryDAO.listActiveSubcategoriesByCategory(id);
		logger.info("Total SubCategories retrieved : " + subcategories.size());
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for(Subcategory subcategoryItem : subcategories)
		{
			JSONObject json = new JSONObject();
			json.put("id", subcategoryItem.getId());
			json.put("name",subcategoryItem.getName());
			jsonList.add(json);
		}
		return jsonList.toString();
	}
	
	@RequestMapping(value = "/get/products", method = RequestMethod.GET)
	@ResponseBody
    public String getProducts(@ModelAttribute("id") int id) {
		logger.info("SubCategory id is " + id);
		List<Product> products = productDAO.listActiveProductsByCategory(id);
		logger.info("Total Products retrieved : " + products.size());
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for(Product productItem : products)
		{
			JSONObject json = new JSONObject();
			json.put("id", productItem.getId());
			json.put("name",productItem.getName());
			json.put("price", String.valueOf(productItem.getUnit_price()));
			jsonList.add(json);
		}
		return jsonList.toString();
	}
	
	@RequestMapping(value = "/get/hotProducts", method = RequestMethod.GET)
	@ResponseBody
    public String gethotProducts() {
		List<Product> products = productDAO.listActiveProducts();// Change it to hot products configured.
		logger.info("Hot Products retrieved : " + products.size());
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for(Product productItem : products)
		{
			JSONObject json = new JSONObject();
			json.put("id", productItem.getId());
			json.put("name",productItem.getName());
			json.put("price", String.valueOf(productItem.getUnit_price()));
			jsonList.add(json);
		}
		return jsonList.toString();
	}
	
	
	/*
	 * Viewing a single product
	 **/
	@RequestMapping(value = "/search/products", method = RequestMethod.POST)
	@ResponseBody
	public Product searchProduct(@ModelAttribute("code") String code) throws ProductNotFoundException {
		logger.info("retieveProduct " + code);
		Product product = productDAO.get(code);
		if (product == null) {
			throw new ProductNotFoundException();
		}
		return product;
	}

	@RequestMapping(value = { "/show/{id}/product" })
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		if (product == null) {
			throw new ProductNotFoundException();
		}
		logger.info("In show single product");
		logger.info(product.getName());
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickedShowProduct", true);
		return mv;
	}

	/*
	 * Login
	 **/
	@RequestMapping(value = { "/login" })
	public ModelAndView showLogin(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout, HttpSession session) {
		int test = 1;
		
		session.setAttribute("test", test);
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid user name or password");
		}

		if (logout != null) {
			mv.addObject("logout", "User has successfully logout!");
		}

		mv.addObject("title", "Login");
		mv.addObject("users", userinfoDAO.getActiveUserslist());
		logger.info(userinfoDAO.getActiveUserslist().toString());
		return mv;
	}

	@RequestMapping(value = { "/perform-logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login?logout";
	}

	/*
	 * Access denied page
	 **/
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView showAccessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("ErrorTitle", "Error!!");
		mv.addObject("ErrorDescription", "You are not authorised to view this page.");
		return mv;
	}

	@RequestMapping(value = { "/print" })
	public ModelAndView print() {
		logger.info("In print");
		ModelAndView mv = new ModelAndView("print");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("ErrorTitle", "Error!!");
		mv.addObject("ErrorDescription", "You are not authorised to view this page.");
		return mv;
	}
	
	@RequestMapping("/add/product/{id}")
	public String addCartLine(@PathVariable("id") int id) {
	    cartService.addCartLine(id);
		return "success";

	}
}
