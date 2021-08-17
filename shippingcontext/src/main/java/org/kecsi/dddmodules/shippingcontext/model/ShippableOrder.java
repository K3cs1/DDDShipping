package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "shippable_order" )
public class ShippableOrder {
	@Id
	private long orderId;
	private String address;
	private List<PackageItem> packageItems;
}
