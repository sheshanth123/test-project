package net.mypos.MyProject.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.mypos.MyProject.Service.PrinterService;
import net.mypos.MyProject.Service.SalesService;
import net.mypos.MyProjectBackend.dto.Sales;

@Controller
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	SalesService saleService;
	
	@Autowired
	PrinterService printerService;

	@RequestMapping(value = "/pay")
	public ModelAndView processOrder(@RequestParam("name") String name) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("total", name);
		mv.addObject("userClickedOrderPayment", true);
		return mv;
	}

	@RequestMapping(value = "payment/cash")
	public String processCashPayment() {
		logger.info("OrderController::processCashPayment()");

		List<Sales> itemsSold = saleService.addSales();
		printerService.print();
		return "redirect:/home";
	}
	
	@RequestMapping(value = "payment/card")
	public String processCardPayment() {
		logger.info("OrderController::processCardPayment()");

		// Connect to payment gateway and process. If successful, perform below items
		saleService.addSales();
		return "redirect:/home";
	}

}
