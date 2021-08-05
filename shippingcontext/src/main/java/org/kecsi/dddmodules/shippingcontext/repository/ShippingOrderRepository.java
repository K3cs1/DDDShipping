package org.kecsi.dddmodules.shippingcontext.repository;

import java.util.Optional;

import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;

public interface ShippingOrderRepository {
	Optional<ShippableOrder> findShippableOrder( int orderId );
}
