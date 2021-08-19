package org.kecsi.dddmodules.shippingcontext.model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document( collection = "shippable_order" )
public class ShippableOrder {

	@Id
	private long orderId;

	private double totalPrice;

	@Builder.Default
	private List<PackageItem> packageItems = new ArrayList<>();

}
