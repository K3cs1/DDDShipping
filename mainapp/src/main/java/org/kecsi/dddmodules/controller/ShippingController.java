package org.kecsi.dddmodules.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.kecsi.dddmodules.ordercontext.model.PaymentMethodType;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.shippingcontext.model.ProductType;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@ModelAttribute( "allProducts" )
	public List<ProductType> populateProducts() {
		return ProductType.ALL_PRODUCTS;
	}

	@ModelAttribute( "allPaymentMethods" )
	public List<PaymentMethodType> populatePaymentMethods() {
		return PaymentMethodType.ALL_PAYMENT_METHODS;
	}

	@GetMapping( "/addCustomerOrder" )
	public String showCustomerOrderForm( CustomerOrder customerOrder ) {
		return "customerOrder";
	}

	@PostMapping( value = "/shippingManagement", params = { "addCustomerOrder" } )
	public String saveCustomerOrder( CustomerOrder customerOrder, BindingResult errors, Model model ) {
		orderService.placeOrder( customerOrder );
		return "redirect:/";
	}

	@GetMapping( { "/", "/shippingManagement" } )
	public String showCustomerOrders( Model model ) {
		model.addAttribute( "customerOrders", orderService.getCustomerOrders() );
		return "index";
	}

	@PostMapping( value = "/shippingManagement", params = { "addOrderItem" } )
	public String addOrderItem( CustomerOrder customerOrder ) {
		customerOrder.getOrderItems().add( new OrderItem() );
		return "customerOrder";
	}

	@PostMapping( value = "/shippingManagement", params = { "removeOrderItem" } )
	public String removeOrderItems( CustomerOrder customerOrder, BindingResult bindingResult, final HttpServletRequest request ) {
		Integer orderItemRow = Integer.valueOf( request.getParameter( "removeOrderItem" ) );
		customerOrder.getOrderItems().remove( orderItemRow.intValue() );
		return "customerOrder";
	}

}
