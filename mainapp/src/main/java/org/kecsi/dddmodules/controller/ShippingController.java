package org.kecsi.dddmodules.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.kecsi.dddmodules.infrastructure.service.AdapterService;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.kecsi.dddmodules.ordercontext.model.PaymentMethodType;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ProductType;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShippingController {

	private OrderService orderService;
	private ShippingService shippingService;
	private AdapterService adapterService;

	@Autowired
	public ShippingController( OrderService orderService, ShippingService shippingService, AdapterService adapterService ) {
		this.orderService = orderService;
		this.shippingService = shippingService;
		this.adapterService = adapterService;
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
	public String saveCustomerOrder( @Valid CustomerOrder customerOrder, BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) {
			return "customerOrder";
		}
		orderService.placeOrder( customerOrder );
		shippingService.shipOrder( adapterService.customerToShippableOrder( customerOrder ) );
		return "redirect:/";
	}

	@GetMapping( { "/", "/shippingManagement" } )
	public String showCustomerOrders( Model model ) {
		model.addAttribute( "customerOrders", orderService.getCustomerOrders() );
		return "index";
	}

	@PostMapping( value = "/shippingManagement", params = { "addOrderItem" } )
	public String addOrderItem( CustomerOrder customerOrder, BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) {
			return "customerOrder";
		}
		customerOrder.getOrderItems().add( new OrderItem() );
		return "customerOrder";
	}

	@PostMapping( value = "/shippingManagement", params = { "removeOrderItem" } )
	public String removeOrderItems( CustomerOrder customerOrder, BindingResult bindingResult, final HttpServletRequest request ) {
		if ( bindingResult.hasErrors() ) {
			return "customerOrder";
		}
		Integer orderItemRow = Integer.valueOf( request.getParameter( "removeOrderItem" ) );
		customerOrder.getOrderItems().remove( orderItemRow.intValue() );
		return "customerOrder";
	}

	@DeleteMapping( "/deleteCustomerOrder/{orderId}" )
	public String deleteCustomerOrder( @PathVariable( value = "orderId" ) long orderId ) {
		orderService.deleteCustomerOrder( orderId );
		shippingService.deleteSippableOrderByOrderId( orderId );
		return "redirect:/";
	}

	@PostMapping( value = "/showShippings", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Parcel showShippings( @RequestBody long orderId ) {
		return shippingService.getParcelByOrderId( orderId ).get();
	}

}
