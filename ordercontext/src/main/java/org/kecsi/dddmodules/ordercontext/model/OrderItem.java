package org.kecsi.dddmodules.ordercontext.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class OrderItem {

	@Id
	private String id;

	@NotNull
	@Min( 1 )
	@Max( 100 )
	private int quantity;

	@NotNull
	@Min( 1 )
	@Max( 10000 )
	private float unitPrice;

	@NotNull
	@Min( 1 )
	@Max( 1000 )
	private float unitWeight;

	public float getTotalPrice() {
		return this.quantity * this.unitPrice;
	}
}
