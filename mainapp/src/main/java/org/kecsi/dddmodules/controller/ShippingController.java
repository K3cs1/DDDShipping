package org.kecsi.dddmodules.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import org.kecsi.dddmodules.infrastructure.service.AdapterService;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.kecsi.dddmodules.ordercontext.model.PaymentMethodType;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ProductType;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
public class ShippingController {

	private final OrderService orderService;
	private final ShippingService shippingService;
	private final AdapterService adapterService;

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
		if ( customerOrder.getId() != null || !customerOrder.getId().isBlank() ) {
			customerOrder.setId( null );
		}
		CustomerOrder customerOrderSaved = orderService.placeOrder( customerOrder );
		shippingService.shipOrder( adapterService.customerToShippableOrder( customerOrderSaved ) );
		return "redirect:/";
	}

	@PostMapping( value = "/shippingManagement", params = { "updateCustomerOrder" } )
	public String updateCustomerOrder( @Valid CustomerOrder customerOrder, BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) {
			return "customerOrder";
		}
		CustomerOrder customerOrderSaved = orderService.placeOrder( customerOrder );
		shippingService.shipOrder( adapterService.customerToShippableOrder( customerOrderSaved ) );
		return "redirect:/";
	}

	@GetMapping( { "/", "/shippingManagement" } )
	public String showCustomerOrders( Model model ) {
		model.addAttribute( "customerOrders", orderService.getCustomerOrders() );
		return "index";
	}

	@PostMapping( value = "/shippingManagement", params = { "addOrderItem" } )
	public String addOrderItem( CustomerOrder customerOrder, BindingResult bindingResult, Model model ) {
		if ( bindingResult.hasErrors() ) {
			return "customerOrder";
		}
		if ( customerOrder.getOrderItems() == null ) {
			customerOrder.setOrderItems( List.of( OrderItem.builder().build() ) );
		} else {
			customerOrder.getOrderItems().add( OrderItem.builder().build() );
		}
		model.addAttribute( "customerOrder", customerOrder );
		return "customerOrder";
	}

	@PostMapping( value = "/shippingManagement", params = { "removeOrderItem" } )
	public String removeOrderItems( CustomerOrder customerOrder, BindingResult bindingResult, final HttpServletRequest request, Model model ) {
		if ( bindingResult.hasErrors() ) {
			return "customerOrder";
		}
		Integer orderItemRow = Integer.valueOf( request.getParameter( "removeOrderItem" ) );
		customerOrder.getOrderItems().remove( orderItemRow.intValue() );
		model.addAttribute( "customerOrder", customerOrder );
		return "customerOrder";
	}

	@PostMapping( value = "/editCustomerOrder/{orderId}" )
	public String editOrderItems( @PathVariable( value = "orderId" ) String orderId, Model model ) {
		model.addAttribute( "customerOrder", orderService.findCustomerOrderByOrderId( orderId ) );
		return "customerOrder";
	}

	@DeleteMapping( "/deleteCustomerOrder/{orderId}" )
	public String deleteCustomerOrder( @PathVariable( value = "orderId" ) String orderId ) {
		orderService.deleteCustomerOrder( orderId );
		shippingService.deleteSippableOrderByOrderId( orderId );
		return "redirect:/";
	}

	@PostMapping( value = "/showShippings", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Parcel showShippings( @RequestBody String orderId ) {
		return shippingService.getParcelByOrderId( orderId ).get();
	}

}
