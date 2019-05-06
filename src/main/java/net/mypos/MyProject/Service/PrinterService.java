package net.mypos.MyProject.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mypos.MyProject.Controller.PageController;
import net.mypos.MyProjectBackend.print.ReceiptPrinter;

@Service("printerService")
public class PrinterService {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	ReceiptPrinter printService;
	
	public void print() {
		logger.info("PrinterService:print()");
		logger.info(printService.getPrinters().toString());
		
		//print some stuff
		//printService.printString("Send To OneNote 2013", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		printService.printString("Send To OneNote 2013", "testing testing 1 2 3.");
 
		// cut that paper!
		//byte[] cutP = new byte[] { 0x1d, 'V', 1 };
 
		//printService.printBytes("Microsoft XPS Document Writer", cutP);
	}

}
