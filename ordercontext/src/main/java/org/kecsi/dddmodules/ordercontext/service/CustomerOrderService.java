package org.kecsi.dddmodules.ordercontext.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.service.SequenceGeneratorService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableMongoRepositories( "org.kecsi.dddmodules.ordercontext.repository" )
public class CustomerOrderService implements OrderService {

	private final CustomerOrderRepository customerOrderRepository;
	private final EventBus eventBus;
	private final SequenceGeneratorService sequenceGeneratorService;

	@Override
	public CustomerOrder findCustomerOrderByOrderId( long orderId ) {
		return customerOrderRepository.findCustomerOrderByOrderId( orderId );
	}

	@Override
	public void placeOrder( CustomerOrder order ) {
		order.setOrderId( sequenceGeneratorService.generateSequence( CustomerOrder.SEQUENCE_NAME ) );
		customerOrderRepository.save( order );
	}

	@Override
	public List<CustomerOrder> getCustomerOrders() {
		return customerOrderRepository.findAll();
	}

	@Override
	public void deleteCustomerOrder( long orderId ) {
		customerOrderRepository.deleteCustomerOrderByOrderId( orderId );
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
}
