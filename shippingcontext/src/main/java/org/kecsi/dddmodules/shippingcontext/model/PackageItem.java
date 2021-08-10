package org.kecsi.dddmodules.shippingcontext.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PackageItem {
	@Id
	private int productId;
	private float weight;
	private float estimatedValue;
}
