package org.kecsi.dddmodules.shippingcontext.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PackageItem {

	@Id
	private long productId;

	private float weight;

	private float estimatedValue;

}
