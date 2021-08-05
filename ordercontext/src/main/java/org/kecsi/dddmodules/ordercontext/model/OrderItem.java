package org.kecsi.dddmodules.ordercontext.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
	private int productId;
	private int quantity;
	private float unitPrice;
	private float unitWeight;

	public float getTotalPrice() {
		return this.quantity * this.unitPrice;
	}
}
