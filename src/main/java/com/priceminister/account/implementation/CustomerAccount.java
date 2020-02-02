package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.exceptions.ErrorMessageEnum;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.InvalidAmountArgException;

public class CustomerAccount implements Account {

	private Double balance = 0.0;

	public void add(Double addedAmount) throws InvalidAmountArgException {
		if (addedAmount == null) {
			throw new InvalidAmountArgException(ErrorMessageEnum.NULL_AMOUNT.toString());
		} else if (addedAmount < 0) {
			throw new InvalidAmountArgException(ErrorMessageEnum.NEGATIVE_AMOUNT.toString());
		}
		balance += addedAmount;
	}

	public Double getBalance() {
		return balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
			throws IllegalBalanceException, InvalidAmountArgException {

		if (withdrawnAmount == null) {
			throw new InvalidAmountArgException(ErrorMessageEnum.NULL_AMOUNT.toString());
		} else if (withdrawnAmount < 0) {
			throw new InvalidAmountArgException(ErrorMessageEnum.NEGATIVE_AMOUNT.toString());
		}

		balance -= withdrawnAmount;
		if (!rule.withdrawPermitted(balance)) {
			throw new IllegalBalanceException(balance);
		}

		return balance;
	}

}
