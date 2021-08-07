package org.kecsi.dddmodules.ordercontext.service;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.sharedkernel.service.ApplicationService;
import org.springframework.stereotype.Service;

@Service
public interface OrderService extends ApplicationService {
	void placeOrder( CustomerOrder order );

	//void setOrderRepository( CustomerOrderRepository orderRepository );
}
