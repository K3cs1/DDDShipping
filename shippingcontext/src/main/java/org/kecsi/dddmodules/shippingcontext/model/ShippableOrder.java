package org.kecsi.dddmodules.shippingcontext.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class ShippableOrder {

	@Id
	private long orderId;

	private double totalPrice;

	@Builder.Default
	private List<PackageItem> packageItems = new ArrayList<>();

}
