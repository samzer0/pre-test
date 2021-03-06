package com.priceminister.account.exceptions;

public class IllegalBalanceException extends Exception {

	private static final long serialVersionUID = -9204191749972551939L;

	private Double balance;

	public IllegalBalanceException(Double illegalBalance) {
		balance = illegalBalance;
	}

	@Override
	public String toString() {
		return ErrorMessageEnum.ILLEGAL_BALANCE.toString() + balance;
	}
}
