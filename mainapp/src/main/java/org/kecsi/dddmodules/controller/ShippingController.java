package org.kecsi.dddmodules.controller;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShippingController {

	private OrderService orderService;
	private ShippingService shippingService;

	@Autowired
	public ShippingController( OrderService orderService, ShippingService shippingService ) {
		this.orderService = orderService;
		this.shippingService = shippingService;
	}

	@GetMapping( "/addCustomerOrder" )
	public String showCustomerOrderForm( CustomerOrder customerOrder ) {
		return "customerOrder";
	}

	@PostMapping( "/addCustomerOrder" )
	public String saveCustomerOrder( CustomerOrder customerOrder ) {
		orderService.placeOrder( customerOrder );
		return "redirect:/index";
	}

	@GetMapping( "/index" )
	public String showCustomerOrders( Model model ) {
		model.addAttribute( "customerOrders", orderService.getCustomerOrders() );
		return "index";
	}

}
