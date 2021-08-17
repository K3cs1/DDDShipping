package org.kecsi.dddmodules.ordercontext.model;

import java.util.List;

import lombok.Getter;

@Getter
public enum PaymentMethodType {
	CASH_ON_DELIVERY( "cash.on.delivery" ),
	BANK_CARD( "bank.card" ),
	CREDIT_CARD( "credit.card" ),
	PAYPAL( "paypal" );

	private String paymentMethod;
	public static List<PaymentMethodType> ALL_PAYMENT_METHODS = List.of( CASH_ON_DELIVERY, BANK_CARD, CREDIT_CARD, PAYPAL );

	PaymentMethodType( String paymentMethod ) {
		this.paymentMethod = paymentMethod;
	}
}
