package org.kecsi.dddmodules.shippingcontext.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document
public class PackageItem {

	@Id
	private String productId;
	private float weight;
	private float estimatedValue;
}
