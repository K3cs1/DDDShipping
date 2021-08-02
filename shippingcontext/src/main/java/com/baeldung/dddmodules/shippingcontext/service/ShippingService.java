package com.baeldung.dddmodules.shippingcontext.service;

import java.util.Optional;

import com.baeldung.dddmodules.shippingcontext.model.Parcel;
import com.baeldung.dddmodules.shippingcontext.repository.ShippingOrderRepository;

public interface ShippingService {
	void shipOrder( int orderId );

	void listenToOrderEvents();

	Optional<Parcel> getParcelByOrderId( int orderId );

	void setOrderRepository( ShippingOrderRepository orderRepository );
}
