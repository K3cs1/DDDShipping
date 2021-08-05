package org.kecsi.dddmodules.shippingcontext.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PackageItem {
	private int productId;
	private float weight;
	private float estimatedValue;
}
