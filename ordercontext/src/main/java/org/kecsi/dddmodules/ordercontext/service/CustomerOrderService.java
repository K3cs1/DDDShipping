package org.kecsi.dddmodules.ordercontext.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NoArgsConstructor;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.sharedkernel.events.ApplicationEvent;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.sharedkernel.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomerOrderService implements OrderService {
	public static final String EVENT_ORDER_READY_FOR_SHIPMENT = "OrderReadyForShipmentEvent";

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
	public void placeOrder( CustomerOrder order ) {
		order.setOrderId( sequenceGeneratorService.generateSequence( CustomerOrder.SEQUENCE_NAME ) );
		this.orderRepository.save( order );
		Map<String, String> payload = new HashMap<>();
		payload.put( "order_id", String.valueOf( order.getOrderId() ) );
		ApplicationEvent event = new ApplicationEvent( payload ) {
			@Override
			public String getType() {
				return EVENT_ORDER_READY_FOR_SHIPMENT;
			}
		};
		this.eventBus.publish( event );
	}

	@Override
	public List<CustomerOrder> getCustomerOrders() {
		return this.orderRepository.findAll();
	}

	@Override
	public EventBus getEventBus() {
		return this.eventBus;
	}
}
