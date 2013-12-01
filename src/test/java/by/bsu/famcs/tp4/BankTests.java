package by.bsu.famcs.tp4;

import by.bsu.famcs.tp4.accounts.BaseAccount;
import by.bsu.famcs.tp4.accounts.ChekingAccount;
import by.bsu.famcs.tp4.accounts.OverdraftAccount;
import by.bsu.famcs.tp4.accounts.SavingAccount;
import by.bsu.famcs.tp4.accounts.TimedMaturityAccount;
import by.bsu.famcs.tp4.logic.*;
import junit.framework.TestCase;

public class BankTests extends TestCase {
	
	private double balance;
	private double interestRate;

	public BankTests(String testName)
	{
		super(testName);
	}
	
	protected void setUp() throws Exception
	{
		super.setUp();
		balance = 1000;
		interestRate = 0.2;
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		balance = 0;
		interestRate = 0;
	}
	
	public void testAccountCreating()
	{
		Bank.getInstance().createBaseAccount(balance);
		Bank.getInstance().createChekingAccount(balance, 10, 10);
		Bank.getInstance().createOverdraftAccount(balance, interestRate);
		Bank.getInstance().createSavingAccount(balance, interestRate);
		Bank.getInstance().createTimedMaturityAccount(balance, interestRate);
		assertEquals(5, Bank.getInstance().getAccounts().size());
		assertEquals(BaseAccount.class, Bank.getInstance().getAccounts().get(0).getClass());
		assertEquals(ChekingAccount.class, Bank.getInstance().getAccounts().get(1).getClass());
		assertEquals(OverdraftAccount.class, Bank.getInstance().getAccounts().get(2).getClass());
		assertEquals(SavingAccount.class, Bank.getInstance().getAccounts().get(3).getClass());
		assertEquals(TimedMaturityAccount.class, Bank.getInstance().getAccounts().get(4).getClass());
	}
	
	public void testPinCheck()
	{
		Bank.getInstance().createBaseAccount(balance);
		int realPin = Bank.getInstance().getAccounts().get(0).getPin();
		int id = Bank.getInstance().getAccounts().get(0).getId();
		assertEquals(Bank.getInstance().checkPin(realPin, id), true);
	}
	
	public void testPinCheckFail()
	{
		Bank.getInstance().createBaseAccount(balance);
		int badPin = Bank.getInstance().getAccounts().get(0).getPin();
		badPin --;
		if (badPin == 999)
		{
			badPin = 9999;
		}
		int id = Bank.getInstance().getAccounts().get(0).getId();
		assertEquals(Bank.getInstance().checkPin(badPin , id), false);
	}
}
