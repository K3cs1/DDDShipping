module com.baeldung.dddmodules.sharedkernel {
	requires static lombok;
	exports com.baeldung.dddmodules.sharedkernel.events;
	exports com.baeldung.dddmodules.sharedkernel.service;
}