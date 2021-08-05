package org.kecsi.dddmodules.ordercontext.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerOrder {
	private int orderId;
	private String paymentMethod;
	private String address;
	private List<OrderItem> orderItems;

	public float calculateTotalPrice() {
		return orderItems.stream().map( OrderItem::getTotalPrice )
				.reduce( 0F, Float::sum );
	}
}
