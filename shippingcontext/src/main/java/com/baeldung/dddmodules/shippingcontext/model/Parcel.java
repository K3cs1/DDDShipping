package com.baeldung.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Parcel {
	private int orderId;
	private String address;
	private String trackingId;
	private List<PackageItem> packageItems;

	public Parcel( int orderId, String address, List<PackageItem> packageItems ) {
		this.orderId = orderId;
		this.address = address;
		this.packageItems = packageItems;
	}

	public float calculateTotalWeight() {
		return packageItems.stream().map( PackageItem::getWeight )
				.reduce( 0F, Float::sum );
	}

	public boolean isTaxable() {
		return calculateEstimatedValue() > 100;
	}

	public float calculateEstimatedValue() {
		return packageItems.stream().map( PackageItem::getWeight )
				.reduce( 0F, Float::sum );
	}
}
