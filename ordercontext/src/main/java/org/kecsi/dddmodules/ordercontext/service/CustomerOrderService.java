package org.kecsi.dddmodules.ordercontext.service;

import java.util.HashMap;
import java.util.Map;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.sharedkernel.events.ApplicationEvent;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;

public class CustomerOrderService implements OrderService {
	public static final String EVENT_ORDER_READY_FOR_SHIPMENT = "OrderReadyForShipmentEvent";

	private CustomerOrderRepository orderRepository;
	private EventBus eventBus;

	@Override
	public void placeOrder( CustomerOrder order ) {
		this.orderRepository.saveCustomerOrder( order );
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
	public void setOrderRepository( CustomerOrderRepository orderRepository ) {
		this.orderRepository = orderRepository;
	}

	@Override
	public EventBus getEventBus() {
		return this.eventBus;
	}

	@Override
	public void setEventBus( EventBus eventBus ) {
		this.eventBus = eventBus;
	}
}
