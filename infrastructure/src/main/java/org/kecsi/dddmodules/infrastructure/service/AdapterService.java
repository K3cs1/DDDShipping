package org.kecsi.dddmodules.infrastructure.service;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.shippingcontext.model.ShippableOrder;
import org.springframework.stereotype.Service;

@Service
public interface AdapterService {
	ShippableOrder customerToShippableOrder( CustomerOrder customerOrder );
}
