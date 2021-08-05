import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.kecsi.dddmodules.shippingcontext.repository.ShippingOrderRepository;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;

module org.kecsi.dddmodules.mainapp {
	requires spring.boot.autoconfigure;
	requires spring.boot;
	requires spring.context;
	requires lombok;
	uses EventBus;
	uses OrderService;
	uses CustomerOrderRepository;
	uses ShippingOrderRepository;
	uses ShippingService;
	requires transitive org.kecsi.dddmodules.infrastructure;
}