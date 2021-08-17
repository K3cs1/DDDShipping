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
		return new ShippableOrder( customerOrder.getOrderId(), customerOrder.getAddress(), customerOrder.getOrderItems()
				.stream().map( orderItem -> new PackageItem( orderItem.getProductId(),
						orderItem.getUnitWeight(),
						orderItem.getQuantity() * orderItem.getUnitPrice() ) ).collect( Collectors.toList() ) );
	}

}
