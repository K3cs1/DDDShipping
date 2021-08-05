package org.kecsi.dddmodules.ordercontext.repository;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerOrderRepository extends MongoRepository<CustomerOrder, String> {
	void saveCustomerOrder( CustomerOrder order );
}
