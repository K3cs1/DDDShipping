package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
public class ShippableOrder {
	@Id
	private int orderId;
	private String address;
	private List<PackageItem> packageItems;

	public ShippableOrder( int orderId, List<PackageItem> packageItems ) {
		this.orderId = orderId;
		this.packageItems = packageItems;
	}
}
