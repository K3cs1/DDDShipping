package org.kecsi.dddmodules.ordercontext.repository;

import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;

public interface CustomerOrderRepository {
	void saveCustomerOrder( CustomerOrder order );
}
