package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parcel {
	private long orderId;
	private String address;
	private String trackingId;
	private List<PackageItem> packageItems;

	public Parcel( long orderId, String address, List<PackageItem> packageItems ) {
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
