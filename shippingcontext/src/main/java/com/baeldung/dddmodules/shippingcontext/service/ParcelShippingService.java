package com.baeldung.dddmodules.shippingcontext.service;

import java.util.Optional;

import com.baeldung.dddmodules.shippingcontext.model.Parcel;
import com.baeldung.dddmodules.shippingcontext.repository.ShippingOrderRepository;

public class ParcelShippingService implements ShippingService {
	
	@Override
	public void shipOrder( int orderId ) {

	}

	@Override
	public void listenToOrderEvents() {

	}

	@Override
	public Optional<Parcel> getParcelByOrderId( int orderId ) {
		return Optional.empty();
	}

	@Override
	public void setOrderRepository( ShippingOrderRepository orderRepository ) {

	}
}
