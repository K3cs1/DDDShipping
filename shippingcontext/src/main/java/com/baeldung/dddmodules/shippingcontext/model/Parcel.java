package com.baeldung.dddmodules.shippingcontext.model;

import java.util.List;

public class Parcel {
	private int orderId;
	private String address;
	private String trackingId;
	private List<PackageItem> packageItems;
}
