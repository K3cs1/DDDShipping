package org.kecsi.dddmodules.shippingcontext.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class PackageItem {
	@Id
	private int productId;
	private float weight;
	private float estimatedValue;
}
