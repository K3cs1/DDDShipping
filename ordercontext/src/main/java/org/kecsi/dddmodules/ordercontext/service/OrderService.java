package org.kecsi.dddmodules.ordercontext.service;

import java.util.List;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.sharedkernel.service.ApplicationService;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends ApplicationService {

	CustomerOrder findCustomerOrderByOrderId( long orderId );

	void placeOrder( CustomerOrder order );

	List<CustomerOrder> getCustomerOrders();

	void deleteCustomerOrder( long orderId );
}
