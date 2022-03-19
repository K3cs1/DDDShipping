package org.kecsi.dddmodules.shippingcontext.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Builder
@Document
public class ShippableOrder {

	@Id
	private String id;
	private double totalPrice;

	@Builder.Default
	@DBRef
	private List<PackageItem> packageItems = new ArrayList<>();

}
