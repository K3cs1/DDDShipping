package org.kecsi.dddmodules.ordercontext.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class CustomerOrder {

	@Transient
	public static final String SEQUENCE_NAME = "customer_order_sequence";

	@Id
	private long orderId;

	@NotNull
	@Size( min = 1, max = 100 )
	private String paymentMethod;

	@NotNull
	@Size( min = 1, max = 100 )
	private String address;

	@NotEmpty
	@Valid
	@Builder.Default
	private List<OrderItem> orderItems = new ArrayList<>();

	public double calculateTotalPrice() {
		return orderItems.stream().map( OrderItem::getTotalPrice )
				.reduce( 0F, Float::sum );
	}
}
