package org.kecsi.dddmodules.controller;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest( classes = { org.kecsi.dddmodules.mainapp.Application.class },
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@AutoConfigureMockMvc
public class ShippingControllerTest {

	@MockBean
	private ShippingService shippingService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName( "Test /showShippings" )
	public void showShippings() throws Exception {

		// Prepare mock Parcel
		Parcel mockParcel = initParcelData();

		// Prepare mocked service method
		doReturn( Optional.of( mockParcel ) ).when( shippingService ).getParcelByOrderId( mockParcel.getOrderId() );

		// Perform GET request
		mockMvc.perform( post( "/showShippings" )
						.contentType( MediaType.APPLICATION_JSON )
						.content( new ObjectMapper().writeValueAsString( 1L ) ) )
				.andExpect( status().isOk() )
				.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
				.andExpect( jsonPath( "$.orderId", is( 1 ) ) )
				.andExpect( jsonPath( "$.totalPrice", is( 10.0 ) ) );
	}

	private Parcel initParcelData() {
		return Parcel.builder()
				.orderId( 1 )
				.totalPrice( 10 )
				.packageItems( List.of( PackageItem.builder()
								.productId( 1 )
								.estimatedValue( 10 )
								.weight( 10 )
								.build(),
						PackageItem.builder()
								.productId( 2 )
								.estimatedValue( 20 )
								.weight( 20 )
								.build() ) )
				.build();
	}

}
