package org.kecsi.dddmodules.ordercontext.service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.ordercontext.repository.OrderItemRepository;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@EnableMongoRepositories( "org.kecsi.dddmodules.ordercontext.repository" )
public class CustomerOrderService implements OrderService {

	private final CustomerOrderRepository customerOrderRepository;
	private final OrderItemRepository orderItemRepository;
	private final EventBus eventBus;

	@Override
	public CustomerOrder findCustomerOrderByOrderId( String orderId ) {
		return customerOrderRepository.findCustomerOrderById( orderId )
				.orElseThrow( () -> new IllegalStateException( "Customer order not found by id " + orderId ) );
	}

	@Override
	public CustomerOrder placeOrder( CustomerOrder order ) {
		if ( order.getOrderItems() != null && !order.getOrderItems().isEmpty() ) {
			orderItemRepository.saveAll( order.getOrderItems() );
		}
		return customerOrderRepository.save( order );
	}

	@Override
	public List<CustomerOrder> getCustomerOrders() {
		return customerOrderRepository.findAll();
	}

	@Override
	public void deleteCustomerOrder( String orderId ) {
		Optional<CustomerOrder> customerOrderOptional = customerOrderRepository.findCustomerOrderById( orderId );
		if ( customerOrderOptional.isPresent() ) {
			orderItemRepository.deleteAll( customerOrderOptional.get().getOrderItems() );
			customerOrderRepository.deleteCustomerOrderById( orderId );
		} else {
			throw new IllegalStateException( "Customer order not found by id: " + orderId );
		}
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}
}
