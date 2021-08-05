import org.kecsi.dddmodules.shippingcontext.service.ParcelShippingService;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;

module org.kecsi.dddmodules.shippingcontext {
	requires static lombok;
	requires org.kecsi.dddmodules.sharedkernel;
	exports org.kecsi.dddmodules.shippingcontext.service;
	exports org.kecsi.dddmodules.shippingcontext.model;
	exports org.kecsi.dddmodules.shippingcontext.repository;
	provides ShippingService
			with ParcelShippingService;
}