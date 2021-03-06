package org.kecsi.dddmodules.shippingcontext.repository;

import java.util.Optional;

import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingOrderRepository extends MongoRepository<ShippableOrder, String> {
	Optional<ShippableOrder> findShippableOrderById( String orderId );

	void deleteShippableOrderById( String orderId );
}
