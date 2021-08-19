package org.kecsi.dddmodules.shippingcontext.model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parcel {

	private long orderId;

	private double totalPrice;

	@Builder.Default
	private List<PackageItem> packageItems = new ArrayList<>();

	public float getCalculatedTotalWeight() {
		return packageItems.stream().map( PackageItem::getWeight )
				.reduce( 0F, Float::sum );
	}

	public boolean isTaxable() {
		return getCalculatedEstimatedValue() > 100;
	}

	public float getCalculatedEstimatedValue() {
		return packageItems.stream().map( PackageItem::getEstimatedValue )
				.reduce( 0F, Float::sum );
	}
}
