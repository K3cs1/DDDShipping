package org.kecsi.dddmodules.shippingcontext.service;

import java.util.Optional;

import org.kecsi.dddmodules.sharedkernel.service.ApplicationService;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.springframework.stereotype.Service;

@Service
public interface ShippingService extends ApplicationService {
	void shipOrder( int orderId );

	void listenToOrderEvents();

	Optional<Parcel> getParcelByOrderId( int orderId );

	void setOrderRepository( ShippingOrderRepository orderRepository );
}
