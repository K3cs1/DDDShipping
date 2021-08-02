package com.baeldung.dddmodules.infrastructure.db;

import java.util.Optional;

import com.baeldung.dddmodules.ordercontext.model.CustomerOrder;
import com.baeldung.dddmodules.ordercontext.repository.CustomerOrderRepository;
import com.baeldung.dddmodules.shippingcontext.model.ShippableOrder;
import com.baeldung.dddmodules.shippingcontext.repository.ShippingOrderRepository;

public class InMemoryOrderStore implements CustomerOrderRepository, ShippingOrderRepository {

	@Override
	public void saveCustomerOrder( CustomerOrder order ) {

	}

	@Override
	public Optional<ShippableOrder> findShippableOrder( int orderId ) {
		return Optional.empty();
	}

}
