package com.baeldung.dddmodules.shippingcontext.model;

import java.util.List;

public class ShippableOrder {
	private int orderId;
	private String address;
	private List<PackageItem> packageItems;
}
