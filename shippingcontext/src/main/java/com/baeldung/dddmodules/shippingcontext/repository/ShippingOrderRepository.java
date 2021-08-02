package com.baeldung.dddmodules.shippingcontext.repository;

import java.util.Optional;

import com.baeldung.dddmodules.shippingcontext.model.ShippableOrder;

public interface ShippingOrderRepository {
	Optional<ShippableOrder> findShippableOrder( int orderId );
}
