package org.kecsi.dddmodules.ordercontext.repository;

import java.util.Optional;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {

	Optional<CustomerOrder> findCustomerOrderById( String orderId );

	void deleteCustomerOrderById( String orderId );
}
