package com.priceminister.account.exceptions;

public enum ErrorMessageEnum {

	NULL_AMOUNT("Added amount cannot be null"), NEGATIVE_AMOUNT("La maison ne fait pas de crédit! ;)"),
	ILLEGAL_BALANCE("Illegal account balance: ");

	private String message = "";

	ErrorMessageEnum(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}
