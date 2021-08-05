import org.kecsi.dddmodules.ordercontext.service.CustomerOrderService;
import org.kecsi.dddmodules.ordercontext.service.OrderService;

module org.kecsi.dddmodules.ordercontext {
	requires static lombok;
	requires org.kecsi.dddmodules.sharedkernel;
	exports org.kecsi.dddmodules.ordercontext.service;
	exports org.kecsi.dddmodules.ordercontext.model;
	exports org.kecsi.dddmodules.ordercontext.repository;
	provides OrderService
			with CustomerOrderService;
}