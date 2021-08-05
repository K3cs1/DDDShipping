package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShippableOrder {
	private int orderId;
	private String address;
	private List<PackageItem> packageItems;

	public ShippableOrder( int orderId, List<PackageItem> packageItems ) {
		this.orderId = orderId;
		this.packageItems = packageItems;
	}
}
