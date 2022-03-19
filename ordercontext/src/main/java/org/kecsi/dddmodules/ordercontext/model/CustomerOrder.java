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
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document
public class CustomerOrder {

	@Id
	private String id;

	@NotNull
	@Size( min = 1, max = 100 )
	private String paymentMethod;

	@NotNull
	@Size( min = 1, max = 100 )
	private String address;

	@NotEmpty
	@Valid
	@Builder.Default
	@DBRef
	private List<OrderItem> orderItems = new ArrayList<>();

	public double calculateTotalPrice() {
		return orderItems.stream().map( OrderItem::getTotalPrice )
				.reduce( 0F, Float::sum );
	}
}
