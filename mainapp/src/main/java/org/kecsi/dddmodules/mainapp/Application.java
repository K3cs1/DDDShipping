package org.kecsi.dddmodules.mainapp;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication( scanBasePackages = "org.kecsi.dddmodules" )
public class Application implements CommandLineRunner {

	private OrderService orderService;
	private ShippingService shippingService;

	@Autowired
	public Application( OrderService orderService, ShippingService shippingService ) {
		this.orderService = orderService;
		this.shippingService = shippingService;
	}

	public static void main( String args[] ) {
		SpringApplication.run( Application.class, args );
	}

	@Override
	public void run( String... args ) throws Exception {
		shippingService.listenToOrderEvents();

		CustomerOrder customerOrder = new CustomerOrder();
		int orderId = 1;
		customerOrder.setOrderId( orderId );
		List<OrderItem> orderItems = new ArrayList<>();
		orderItems.add( new OrderItem( 1, 2, 3, 1 ) );
		orderItems.add( new OrderItem( 2, 1, 1, 1 ) );
		orderItems.add( new OrderItem( 3, 4, 11, 21 ) );
		customerOrder.setOrderItems( orderItems );
		customerOrder.setPaymentMethod( "PayPal" );
		customerOrder.setAddress( "Full address here" );
		orderService.placeOrder( customerOrder );

		if ( orderId == shippingService.getParcelByOrderId( orderId ).get().getOrderId() ) {
			System.out.println( "Order has been processed and shipped successfully" );
		}
	}

	@PostConstruct
	public void initShippableOrder() {
		shippingService.initShippableOrder();
	}
}
