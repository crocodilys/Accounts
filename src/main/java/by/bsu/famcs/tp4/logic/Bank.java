package by.bsu.famcs.tp4.logic;
import java.util.ArrayList;

import by.bsu.famcs.tp4.accounts.*;

public class Bank {
	
	private static Bank instance; 
	
	private ArrayList<BaseAccount> accounts;

	private Bank()
	{
		accounts = new ArrayList<BaseAccount>();
	}
	
	public static Bank getInstance()
	{
		if (instance == null)
		{
			synchronized(Bank.class)
			{
				if (instance == null)
				{
					instance = new Bank();
				}
			}            
		}

		return instance;
	}
	
	public void addAccount(BaseAccount account)
	{
		accounts.add(account);
	}
	
	public void createBaseAccount(double balance)
	{
		addAccount(new BaseAccount(balance));
	}
	
	public void createChekingAccount(double balance, int mounthlyQuota, double transactionFee)
	{
		addAccount(new ChekingAccount(balance, mounthlyQuota, transactionFee));
	}
	
	public void createOverdraftAccount(double balance, double interestRate)
	{
		addAccount(new OverdraftAccount(balance, interestRate));
	}
	
	public void createSavingAccount(double balance, double interestRate)
	{
		addAccount(new SavingAccount(balance, interestRate));
	}
	
	public void createTimedMaturityAccount(double balance, double penaltyRate)
	{
		addAccount(new TimedMaturityAccount(balance, penaltyRate));
	}
	
	public BaseAccount searchAccount(int id)
	{
		for (int i = 0; i < accounts.size(); i++)
		{
			if (accounts.get(i).getId() == id)
			{
				return accounts.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<BaseAccount> getAccounts() {
		return accounts;
	}
	
	public boolean checkPin(int enteredPin, int id)
	{
		return searchAccount(id).getPin() == enteredPin;
	}
}
