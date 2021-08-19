package org.kecsi.dddmodules.infrastructure.service;

import java.util.stream.Collectors;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.shippingcontext.model.PackageItem;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.springframework.stereotype.Component;

@Component
public class ShippableOrderAdapterService implements AdapterService {

	@Override
	public ShippableOrder customerToShippableOrder( CustomerOrder customerOrder ) {
		return ShippableOrder.builder()
				.orderId( customerOrder.getOrderId() )
				.totalPrice( customerOrder.calculateTotalPrice() )
				.packageItems( customerOrder.getOrderItems().stream()
						.map( orderItem -> PackageItem.builder()
								.productId( orderItem.getProductId() )
								.weight( orderItem.getUnitWeight() )
								.estimatedValue( orderItem.getQuantity() * orderItem.getUnitPrice() ).build() )
						.collect( Collectors.toList() ) )
				.build();
	}

}
