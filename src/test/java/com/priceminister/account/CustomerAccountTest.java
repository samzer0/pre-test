package com.priceminister.account;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.priceminister.account.exceptions.ErrorMessageEnum;
import com.priceminister.account.exceptions.IllegalBalanceException;
import com.priceminister.account.exceptions.InvalidAmountArgException;
import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 *
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 *
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 *
 */
public class CustomerAccountTest {

	Account customerAccount;
	AccountRule rule;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		customerAccount = new CustomerAccount();
		rule = new CustomerAccountRule();
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		Assert.assertNotNull(customerAccount.getBalance());
		Assert.assertEquals(Double.valueOf(0), customerAccount.getBalance());
	}

	/**
	 * Adds null amount and expect to receive a InvalidAmountArgException with the
	 * right error message.
	 *
	 * @throws InvalidAmountArgException
	 */
	@Test
	public void testAddNullAmount() throws InvalidAmountArgException {

		thrown.expect(InvalidAmountArgException.class);
		thrown.expectMessage(ErrorMessageEnum.NULL_AMOUNT.toString());

		Double addedAmount = null;
		customerAccount.add(addedAmount);

	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 *
	 * @throws InvalidAmountArgException
	 */
	@Test
	public void testAddPositiveAmount() throws InvalidAmountArgException {
		Double addedAmount = 10.0;
		customerAccount.add(addedAmount);

		Assert.assertEquals(addedAmount, customerAccount.getBalance());
	}

	/**
	 * Adds negative amount and expect to receive a InvalidAmountArgException with
	 * the right error message.
	 *
	 * @throws InvalidAmountArgException
	 */
	@Test
	public void testAddNegativeAmount() throws InvalidAmountArgException {
		thrown.expect(InvalidAmountArgException.class);
		thrown.expectMessage(ErrorMessageEnum.NEGATIVE_AMOUNT.toString());

		Double addedAmount = -10.0;
		customerAccount.add(addedAmount);
	}

	/**
	 * Tests that null amount during withdrawal throws the expected exception.
	 *
	 * @throws IllegalBalanceException
	 * @throws InvalidAmountArgException
	 */
	@Test(expected = InvalidAmountArgException.class)
	public void testWithdrawAndReportBalanceWithNullAmount() throws IllegalBalanceException, InvalidAmountArgException {

		customerAccount.withdrawAndReportBalance(null, rule);

	}

	/**
	 * Tests that negative amount during withdrawal throws the expected exception.
	 *
	 * @throws IllegalBalanceException
	 * @throws InvalidAmountArgException
	 */
	@Test(expected = InvalidAmountArgException.class)
	public void testWithdrawAndReportBalanceWithNegativeAmount()
			throws IllegalBalanceException, InvalidAmountArgException {

		Double withdrawnAmount = -10.0;
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);

	}

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the logic
	 * contained in CustomerAccountRule; feel free to refactor the existing code.
	 *
	 * @throws IllegalBalanceException
	 * @throws InvalidAmountArgException
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException, InvalidAmountArgException {

		Double withdrawnAmount = 10.0;
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);

	}

	/**
	 * Test withdraw when balance amount and withdraw amount are equals.
	 *
	 * @throws IllegalBalanceException
	 * @throws InvalidAmountArgException
	 */
	@Test
	public void testWithdrawAndReportBalanceWithBalanceEqualsToWithdraw()
			throws IllegalBalanceException, InvalidAmountArgException {
		Double addedAmount = 10.0;
		Double withdrawnAmount = 10.0;
		Double expectedBalance = 0.0;

		customerAccount.add(addedAmount);
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);

		Assert.assertEquals(expectedBalance, customerAccount.getBalance());

	}

	/**
	 * Test withdraw with balance greater than withdraw amount.
	 *
	 * @throws IllegalBalanceException
	 * @throws InvalidAmountArgException
	 */
	@Test
	public void testWithdrawAndReportBalanceWithBalanceGreaterThanWithdraw()
			throws IllegalBalanceException, InvalidAmountArgException {
		Double addedAmount = 20.0;
		Double withdrawnAmount = 10.0;
		Double expectedBalance = 10.0;

		customerAccount.add(addedAmount);
		customerAccount.withdrawAndReportBalance(withdrawnAmount, rule);

		Assert.assertEquals(expectedBalance, customerAccount.getBalance());

	}

}
