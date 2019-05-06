package net.mypos.MyProject.Controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProject.Service.CartService;
import net.mypos.MyProjectBackend.dao.CartLineDAO;
import net.mypos.MyProjectBackend.dao.ProductDAO;
import net.mypos.MyProjectBackend.dto.CartLine;
import net.mypos.MyProjectBackend.dto.Product;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	CartLineDAO cartLineDAO;
	
	/*@RequestMapping("/show/json/{id}")
	@ResponseBody
	public List<CartLine> getCartListAsJson(@PathVariable("id") int id){
		
		List<CartLine> cartItems = cartService.getCartLines();
		return cartItems;
	}*/
	
	@RequestMapping("/show/json")
	@ResponseBody
	public String getExistingCartItems(){
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		List<CartLine> cartItems = cartService.getCartLines();
		for(CartLine cart : cartItems){
			Product product = cart.getProduct();
			JSONObject json = new JSONObject();
			json.put("productId", product.getId());
			json.put("productValue", product.getUnit_price());
			json.put("productName", product.getName());
			json.put("productCount", cart.getProductCount());
			jsonList.add(json);
		}
		return jsonList.toString();
	}

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "success", required = false) String success,
			@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "product", required = false) String prodName) {
		ModelAndView mv = new ModelAndView("home");

		mv.addObject("title", "Cart");
		mv.addObject("userClickedShowCart", true);
		mv.addObject("cartlines", cartService.getCartLines());
		List<CartLine> cartItems = cartService.getCartLines();

		if (success != null) {
			if (success.equals("add")) {
				mv.addObject("message", "Product: " + prodName + " has been added to the cart.");
			} else if (success.equals("delete")) {
				mv.addObject("message", "Product: " + prodName + " has been deleted from the cart.");
			} else if (success.equals("update")) {
				mv.addObject("message", "Cart has been updated.");
			}

		}

		if (error != null) {
			if (error.equals("add")) {
				mv.addObject("message", "Product: " + prodName + " has already been added to the cart.");
			}
		}
		return mv;
	}

	@RequestMapping("/add/product/{id}")
	public String addCartLine(@PathVariable("id") int id) {
		Product product = productDAO.get(id);

		/*if (cartService.checkProductInCartLine(product)) {
			return "redirect:/cart/show?error=add&product=" + product.getName();
		} else {*/
			cartService.addCartLine(id);
			/*return "redirect:/cart/show?success=add&product=" + product.getName();*/
		/*}*/
		return "success";

	}
	
	@RequestMapping("/remove/product/{id}")
	@ResponseBody
	public String removeProduct(@PathVariable("id") int id) {
		/*CartLine cartline = cartLineDAO.get(id);
		Product cartLineProduct = cartline.getProduct();*/

		cartService.deleteCartLine(id);
		
		return "success";
	}
	
	@RequestMapping("/reduce/product/{id}")
	@ResponseBody
	public String reduceProduct(@PathVariable("id") int id) {
		/*CartLine cartline = cartLineDAO.get(id);
		Product cartLineProduct = cartline.getProduct();*/

		cartService.reduceProductCount(id);
		
		return "success";
	}
	
	@RequestMapping("/delete/cartline/{id}")
	public String deleteCartLine(@PathVariable("id") int id) {
		CartLine cartline = cartLineDAO.get(id);
		Product cartLineProduct = cartline.getProduct();

		cartService.deleteCartLine(id);
		
		return "redirect:/cart/show?success=delete&product=" + cartLineProduct.getName();
	}

	@RequestMapping("/update/cartline/{id}")
	public String updateCartLine(@PathVariable("id") int id,
			@RequestParam(name = "quantity", required = true) int quantity) {

		//cartService.updateCartLine(id, quantity);

		return "redirect:/cart/show?success=update";
		
	}

}
