package org.kecsi.dddmodules.ordercontext.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CustomerOrder {
	@Id
	private int orderId;
	private String paymentMethod;
	private String address;
	private List<OrderItem> orderItems;

	public float calculateTotalPrice() {
		return orderItems.stream().map( OrderItem::getTotalPrice )
				.reduce( 0F, Float::sum );
	}
}
