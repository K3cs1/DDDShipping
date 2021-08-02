package com.baeldung.dddmodules.ordercontext.model;

import java.util.List;

public class CustomerOrder {
	private int orderId;
	private String paymentMethod;
	private String address;
	private List<OrderItem> orderItems;

}
