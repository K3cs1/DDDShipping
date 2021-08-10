package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShippableOrder {
	@Id
	private int orderId;
	private String address;
	private List<PackageItem> packageItems;
}
