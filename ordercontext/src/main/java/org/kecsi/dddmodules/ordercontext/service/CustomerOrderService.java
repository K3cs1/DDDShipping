package org.kecsi.dddmodules.ordercontext.service;

import java.util.List;

import lombok.NoArgsConstructor;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerOrderService implements OrderService {

	private CustomerOrderRepository orderRepository;
	private EventBus eventBus;
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	public CustomerOrderService( CustomerOrderRepository orderRepository, EventBus eventBus, SequenceGeneratorService sequenceGeneratorService ) {
		this.orderRepository = orderRepository;
		this.eventBus = eventBus;
		this.sequenceGeneratorService = sequenceGeneratorService;
	}

	@Override
	public CustomerOrder findCustomerOrderByOrderId( long orderId ) {
		return this.orderRepository.findCustomerOrderByOrderId( orderId );
	}

	@Override
	public void placeOrder( CustomerOrder order ) {
		order.setOrderId( sequenceGeneratorService.generateSequence( CustomerOrder.SEQUENCE_NAME ) );
		this.orderRepository.save( order );
	}

	@Override
	public List<CustomerOrder> getCustomerOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public void deleteCustomerOrder( long orderId ) {
		orderRepository.deleteCustomerOrderByOrderId( orderId );
	}

	@Override
	public EventBus getEventBus() {
		return this.eventBus;
	}
}
