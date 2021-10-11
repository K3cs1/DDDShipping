package org.kecsi.dddmodules.controller;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kecsi.dddmodules.shippingcontext.model.PackageItem;
import org.kecsi.dddmodules.shippingcontext.model.Parcel;
import org.kecsi.dddmodules.shippingcontext.service.ShippingService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest
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
		Mockito.doReturn( mockParcel ).when( shippingService.getParcelByOrderId( mockParcel.getOrderId() ) );

		// Perform GET request
		mockMvc.perform( MockMvcRequestBuilders.get( "/showShippings" ) )
				.andExpect( MockMvcResultMatchers.status().isOk() )
				.andExpect( content().contentType( MediaType.APPLICATION_JSON_VALUE ) )
				.andExpect( header().string( HttpHeaders.ETAG, "\"1\"" ) )
				.andExpect( header().string( HttpHeaders.LOCATION, "/showShippings" ) )
				.andExpect( jsonPath( "$.id", is( 1 ) ) );
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
