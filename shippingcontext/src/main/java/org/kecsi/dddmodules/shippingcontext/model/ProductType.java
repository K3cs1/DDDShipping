package org.kecsi.dddmodules.shippingcontext.model;

import java.util.List;

import lombok.Getter;

@Getter
public enum ProductType {
	PRODUCT_ONE( 1L, "one" ),
	PRODUCT_TWO( 2L, "two" ),
	PRODUCT_THREE( 3L, "three" ),
	PRODUCT_FOUR( 4L, "four" );

	private long productId;
	private String productName;
	public static final List<ProductType> ALL_PRODUCTS = List.of( values() );

	ProductType( long productId, String productName ) {
		this.productId = productId;
		this.productName = productName;
	}
}
