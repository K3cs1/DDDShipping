package org.kecsi.dddmodules.shippingcontext.repository;

import java.util.Optional;

import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShippingOrderRepository extends MongoRepository<ShippableOrder, String> {
	Optional<ShippableOrder> findShippableOrder( int orderId );
}
