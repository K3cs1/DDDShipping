package org.kecsi.dddmodules.ordercontext.repository;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {
	CustomerOrder findCustomerOrderByOrderId( long orderId );

	void deleteCustomerOrderByOrderId( long orderId );
}
