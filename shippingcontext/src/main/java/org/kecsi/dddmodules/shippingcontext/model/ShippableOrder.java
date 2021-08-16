package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShippableOrder {
	@Id
	private int orderId;
	private String address;
	private List<PackageItem> packageItems;
}
