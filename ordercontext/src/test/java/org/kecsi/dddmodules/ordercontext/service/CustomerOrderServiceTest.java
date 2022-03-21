package org.kecsi.dddmodules.ordercontext.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kecsi.dddmodules.ordercontext.model.CustomerOrder;
import org.kecsi.dddmodules.ordercontext.model.OrderItem;
import org.kecsi.dddmodules.ordercontext.model.PaymentMethodType;
import org.kecsi.dddmodules.ordercontext.repository.CustomerOrderRepository;
import org.kecsi.dddmodules.ordercontext.repository.OrderItemRepository;
import org.kecsi.dddmodules.sharedkernel.events.EventBus;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class CustomerOrderServiceTest {

	@Mock
	private CustomerOrderRepository customerOrderRepository;
	@Mock
	private OrderItemRepository orderItemRepository;
	@Mock
	private EventBus eventBus;
	private CustomerOrderService underTest;

	@BeforeEach
	void setup() {
		underTest = new CustomerOrderService( customerOrderRepository, orderItemRepository, eventBus );
	}

	@Test
	@DisplayName( "Can find Customer order by id" )
	void canFindCustomerOrderByOrderId() {
		//given
		String orderId = "62302caf942f0f3db0d6a84d";
		CustomerOrder customerOrder = CustomerOrder.builder()
				.id( orderId )
				.address( "Test street 13." )
				.paymentMethod( PaymentMethodType.CREDIT_CARD.getPaymentMethod() )
				.orderItems( List.of(
						OrderItem.builder()
								.id( "1" )
								.quantity( 10 )
								.unitPrice( 100 )
								.unitWeight( 50.5F )
								.build(),
						OrderItem.builder()
								.id( "2" )
								.quantity( 20 )
								.unitPrice( 200 )
								.unitWeight( 60.F )
								.build()
				) )
				.build();
		given( customerOrderRepository.findCustomerOrderById( orderId ) ).willReturn( Optional.of( customerOrder ) );

		//when
		underTest.findCustomerOrderByOrderId( orderId );

		//verify
		verify( customerOrderRepository ).findCustomerOrderById( orderId );
	}

	@Test
	@DisplayName( "Can save new Customer order" )
	void canPlaceOrder() {
		//given
		CustomerOrder customerOrder = CustomerOrder.builder()
				.address( "Test street 13." )
				.paymentMethod( PaymentMethodType.CREDIT_CARD.getPaymentMethod() )
				.orderItems( List.of(
						OrderItem.builder()
								.id( "1" )
								.quantity( 10 )
								.unitPrice( 100 )
								.unitWeight( 50.5F )
								.build(),
						OrderItem.builder()
								.id( "2" )
								.quantity( 20 )
								.unitPrice( 200 )
								.unitWeight( 60.F )
								.build()
				) )
				.build();
		//when
		underTest.placeOrder( customerOrder );

		//then
		ArgumentCaptor<CustomerOrder> customerOrderArgumentCaptor = ArgumentCaptor.forClass( CustomerOrder.class );
		verify( customerOrderRepository ).save( customerOrderArgumentCaptor.capture() );
		CustomerOrder capturedCustomerOrder = customerOrderArgumentCaptor.getValue();
		assertThat( capturedCustomerOrder ).isEqualTo( customerOrder );

	}

	@Test
	@DisplayName( "Can get Customer orders" )
	void canGetCustomerOrders() {
		//when
		underTest.getCustomerOrders();

		//then
		verify( customerOrderRepository ).findAll();
	}

	@Test
	@DisplayName( "Can delete customer order" )
	void canDeleteCustomerOrder() {
		//given
		String orderId = "62302caf942f0f3db0d6a84d";
		CustomerOrder customerOrder = CustomerOrder.builder()
				.id( orderId )
				.address( "Test street 13." )
				.paymentMethod( PaymentMethodType.CREDIT_CARD.getPaymentMethod() )
				.orderItems( List.of(
						OrderItem.builder()
								.id( "1" )
								.quantity( 10 )
								.unitPrice( 100 )
								.unitWeight( 50.5F )
								.build(),
						OrderItem.builder()
								.id( "2" )
								.quantity( 20 )
								.unitPrice( 200 )
								.unitWeight( 60.F )
								.build()
				) )
				.build();

		//when
		//than
		underTest.placeOrder( customerOrder );
		ArgumentCaptor<CustomerOrder> customerOrderArgumentCaptor = ArgumentCaptor.forClass( CustomerOrder.class );
		verify( customerOrderRepository ).save( customerOrderArgumentCaptor.capture() );
		CustomerOrder capturedCustomerOrder = customerOrderArgumentCaptor.getValue();
		assertThat( capturedCustomerOrder ).isEqualTo( customerOrder );

		//given
		given( customerOrderRepository.findCustomerOrderById( orderId ) ).willReturn( Optional.of( customerOrder ) );
		//when
		//than
		underTest.deleteCustomerOrder( orderId );
		verify( orderItemRepository, atLeastOnce() ).deleteAll( customerOrder.getOrderItems() );
		verify( customerOrderRepository, atLeastOnce() ).deleteCustomerOrderById( orderId );
	}

	@Test
	@DisplayName( "Can not delete customer order" )
	public void cannotDeleteCustomerOrder() {
		//given
		String orderId = "62302caf942f0f3db0d6a84d";
		given( customerOrderRepository.findCustomerOrderById( orderId ) ).willReturn( Optional.empty() );

		//when
		//than
		assertThatThrownBy( () -> underTest.deleteCustomerOrder( orderId ) )
				.isInstanceOf( IllegalStateException.class )
				.hasMessageContaining( "Customer order not found by id: " + orderId );
		verify( orderItemRepository, never() ).deleteAll( any() );
		verify( customerOrderRepository, never() ).deleteCustomerOrderById( any() );
	}

}