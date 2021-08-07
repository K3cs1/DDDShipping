import org.kecsi.dddmodules.ordercontext.service.CustomerOrderService;
import org.kecsi.dddmodules.ordercontext.service.OrderService;

module org.kecsi.dddmodules.ordercontext {
	requires lombok;
	requires java.annotation;
	requires spring.core;
	requires spring.beans;
	requires spring.context;
	requires spring.data.commons;
	requires spring.data.mongodb;
	requires org.kecsi.dddmodules.sharedkernel;
	exports org.kecsi.dddmodules.ordercontext.service;
	exports org.kecsi.dddmodules.ordercontext.model;
	exports org.kecsi.dddmodules.ordercontext.repository;
	provides OrderService
			with CustomerOrderService;
	opens org.kecsi.dddmodules.ordercontext.repository to spring.core, spring.beans, spring.context;
}