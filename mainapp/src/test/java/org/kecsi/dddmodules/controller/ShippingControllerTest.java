package org.kecsi.dddmodules.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.kecsi.dddmodules.ordercontext.service.OrderService;
import org.kecsi.dddmodules.shippingcontext.model.PackageItem;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest( classes = { org.kecsi.dddmodules.mainapp.Application.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
public class ShippingControllerTest {

	@MockBean
	private ShippingService shippingService;

	@MockBean
	private OrderService orderService;

	@Autowired
	private MockMvc mockMvc;

	private Parcel mockParcel;
	private List<CustomerOrder> mockCustomerOrder;

	@BeforeEach
	public void initData() {
		// Prepare mock Parcel
		mockParcel = initParcelData();
		mockCustomerOrder = initCustomerOrders();
	}

	@Test
	@DisplayName( "Test /showShippings" )
	public void showShippings() throws Exception {

		// Prepare mocked service method
		doReturn( Optional.of( mockParcel ) ).when( shippingService ).getParcelByOrderId( mockParcel.getOrderId() );

		// Perform POST request
		mockMvc.perform( post( "/showShippings" )
						.contentType( MediaType.APPLICATION_JSON )
						.content( "6235db9dada5bc366b93e3e8" ) )
				.andExpect( status().isOk() )
				.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
				.andExpect( jsonPath( "$.orderId", is( "6235db9dada5bc366b93e3e8" ) ) )
				.andExpect( jsonPath( "$.totalPrice", is( 10.0 ) ) );
	}

	@Test
	@DisplayName( "Test /shippingManagement " )
	public void showCustomerOrders() throws Exception {
		doReturn( mockCustomerOrder ).when( orderService ).getCustomerOrders();

		mockMvc.perform( get( "/shippingManagement" )
						.contentType( MediaType.APPLICATION_JSON ) )
				.andExpect( status().isOk() )
				.andExpect( content().contentType( MediaType.TEXT_HTML_VALUE + ";charset=UTF-8" ) );
		//TODO more assertions
	}

	private Parcel initParcelData() {
		return Parcel.builder()
				.orderId( "6235db9dada5bc366b93e3e8" )
				.totalPrice( 10 )
				.packageItems( List.of( PackageItem.builder()
								.productId( "1" )
								.estimatedValue( 10 )
								.weight( 10 )
								.build(),
						PackageItem.builder()
								.productId( "2" )
								.estimatedValue( 20 )
								.weight( 20 )
								.build() ) )
				.build();
	}

	private List<CustomerOrder> initCustomerOrders() {
		List<CustomerOrder> customerOrders = new ArrayList<>();
		customerOrders.addAll( List.of(
				CustomerOrder.builder()
						.id( "6235db9dada5bc366b93e3e8" )
						.address( "Test Street 1." )
						.paymentMethod( "Credit Card" )
						.orderItems( List.of( OrderItem.builder()
										.id( "1" )
										.quantity( 10 )
										.unitWeight( 20 )
										.unitPrice( 50 )
										.build(),
								OrderItem.builder()
										.id( "2" )
										.quantity( 20 )
										.unitWeight( 30 )
										.unitPrice( 25 )
										.build() ) )
						.build(),
				CustomerOrder.builder()
						.id( "6235dbe3ada5bc366b93e3e9" )
						.address( "Test Street 2." )
						.paymentMethod( "Debit Card " )
						.orderItems( List.of( OrderItem.builder()
										.id( "1" )
										.quantity( 30 )
										.unitWeight( 40 )
										.unitPrice( 60 )
										.build(),
								OrderItem.builder()
										.id( "2" )
										.quantity( 70 )
										.unitWeight( 80 )
										.unitPrice( 90 )
										.build() ) )
						.build() ) );
		return customerOrders;
	}

}
