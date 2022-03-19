package org.kecsi.dddmodules.shippingcontext.service;

import java.util.Optional;

import org.kecsi.dddmodules.sharedkernel.service.ApplicationService;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.springframework.stereotype.Service;

@Service
public interface ShippingService extends ApplicationService {
	void shipOrder( ShippableOrder shippableOrder );

	Optional<Parcel> getParcelByOrderId( String orderId );

	void deleteSippableOrderByOrderId( String orderId );
}
