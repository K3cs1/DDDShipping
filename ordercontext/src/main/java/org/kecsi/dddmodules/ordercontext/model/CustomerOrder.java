package org.kecsi.dddmodules.ordercontext.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {
	@Id
	private int orderId;
	private String paymentMethod;
	private String address;
	private List<OrderItem> orderItems = new ArrayList<>();

	public float calculateTotalPrice() {
		return orderItems.stream().map( OrderItem::getTotalPrice )
				.reduce( 0F, Float::sum );
	}
}
