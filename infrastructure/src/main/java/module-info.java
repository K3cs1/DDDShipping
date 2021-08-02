module com.baeldung.dddmodules.infrastructure {
	requires com.baeldung.dddmodules.sharedkernel;
	requires com.baeldung.dddmodules.ordercontext;
	requires com.baeldung.dddmodules.shippingcontext;
	provides com.baeldung.dddmodules.sharedkernel.events.EventBus
			with com.baeldung.dddmodules.infrastructure.events.SimpleEventBus;
	provides com.baeldung.dddmodules.ordercontext.repository.CustomerOrderRepository
			with com.baeldung.dddmodules.infrastructure.db.InMemoryOrderStore;
	provides com.baeldung.dddmodules.shippingcontext.repository.ShippingOrderRepository
			with com.baeldung.dddmodules.infrastructure.db.InMemoryOrderStore;
	exports com.baeldung.dddmodules.infrastructure.db;
	exports com.baeldung.dddmodules.infrastructure.events;
}