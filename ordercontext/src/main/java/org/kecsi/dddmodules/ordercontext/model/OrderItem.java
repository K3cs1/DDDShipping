package org.kecsi.dddmodules.ordercontext.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
