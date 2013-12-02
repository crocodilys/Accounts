package by.bsu.famcs.tp4.tests;

import by.bsu.famcs.tp4.accounts.BaseAccount;
import by.bsu.famcs.tp4.accounts.ChekingAccount;
import by.bsu.famcs.tp4.accounts.OverdraftAccount;
import by.bsu.famcs.tp4.accounts.SavingAccount;
import by.bsu.famcs.tp4.accounts.TimedMaturityAccount;
import junit.framework.TestCase;


public class AccountsTests extends TestCase {
	private double balance;
	private double withdrawingValue1;
	private double withdrawingValue2;
	private double interestRate;
	
	public AccountsTests(String testName)
	{
		super(testName);
	}
	
	protected void setUp() throws Exception
	{
		super.setUp();
		balance = 1000;
		withdrawingValue1 = 500;
		withdrawingValue2 = 1500;
		interestRate = 0.2;
	}
	
	protected void tearDown() throws Exception
	{
		super.tearDown();
		balance = 0;
		withdrawingValue1 = 0;
		withdrawingValue2 = 0;
		interestRate = 0;
	}
	
	public void testBaseWithdraw()
	{
		double correctValue = 500;
		double correctBalance = 500;
		BaseAccount account = new BaseAccount(balance);
		double withdrawedValue = account.withdraw(withdrawingValue1);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testBaseWithdrawFail()
	{
		double correctValue = 0;
		double correctBalance = 1000;
		BaseAccount account = new BaseAccount(balance);
		double withdrawedValue = account.withdraw(withdrawingValue2);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	
	
	public void testChekingWithdraw()
	{
		int mounthlyQuota = 0;
		double transactionFee = 10;
		double correctValue = 500;
		double correctBalance = 490;
		ChekingAccount account = new ChekingAccount(balance, mounthlyQuota, transactionFee);
		double withdrawedValue = account.withdraw(withdrawingValue1);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testChekingWithdrawFail()
	{
		int mounthlyQuota = 0;
		double transactionFee = 10;
		double correctValue = 0;
		double correctBalance = 1000;
		ChekingAccount account = new ChekingAccount(balance, mounthlyQuota, transactionFee);
		double withdrawedValue = account.withdraw(withdrawingValue2);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testOverdraftWithdraw1()
	{
		double correctValue = 500;
		double correctBalance = 500;
		OverdraftAccount account = new OverdraftAccount(balance, interestRate);
		double withdrawedValue = account.withdraw(withdrawingValue1);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testOverdraftWithdraw2()
	{
		double correctValue = 1500;
		double correctBalance = -500;
		OverdraftAccount account = new OverdraftAccount(balance, interestRate);
		double withdrawedValue = account.withdraw(withdrawingValue2);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testOverdraftPayInterest()
	{
		double balance = -1000;
		double correctBalance = -1200;
		OverdraftAccount account = new OverdraftAccount(balance, interestRate);
		account.payInterest();
		double actualBalance = account.getBalance();
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testSavingPayInterest()
	{
		double correctBalance = 1200;
		SavingAccount account = new SavingAccount(balance, interestRate);
		account.payInterest();
		double actualBalance = account.getBalance();
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testTimedMaturityWithdrawWithoutPenalty()
	{
		double correctValue = 500;
		double correctBalance = 500;
		TimedMaturityAccount account = new TimedMaturityAccount(balance, interestRate);
		account.setMaturityOccured(false);
		double withdrawedValue = account.withdraw(withdrawingValue1);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testTimedMaturityWithdrawWithPenalty()
	{
		double correctValue = 400;
		double correctBalance = 500;
		TimedMaturityAccount account = new TimedMaturityAccount(balance, interestRate);
		account.setMaturityOccured(true);
		double withdrawedValue = account.withdraw(withdrawingValue1);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}
	
	public void testTimedMaturityWithdrawFail()
	{
		double correctValue = 0;
		double correctBalance = 1000;
		TimedMaturityAccount account = new TimedMaturityAccount(balance, interestRate);
		double withdrawedValue = account.withdraw(withdrawingValue2);
		double actualBalance = account.getBalance();
		assertEquals(correctValue, withdrawedValue);
		assertEquals(correctBalance, actualBalance);
	}

	

}
