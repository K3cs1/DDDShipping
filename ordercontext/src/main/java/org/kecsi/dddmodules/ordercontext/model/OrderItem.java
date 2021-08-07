package org.kecsi.dddmodules.ordercontext.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class OrderItem {
	@Id
	private int productId;
	private int quantity;
	private float unitPrice;
	private float unitWeight;

	public float getTotalPrice() {
		return this.quantity * this.unitPrice;
	}
}
