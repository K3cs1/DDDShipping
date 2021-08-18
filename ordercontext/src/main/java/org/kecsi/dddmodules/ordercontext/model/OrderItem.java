package org.kecsi.dddmodules.ordercontext.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	@Id
	private long productId;

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
