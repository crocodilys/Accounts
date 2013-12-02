package by.bsu.famcs.tp4.logic;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import by.bsu.famcs.tp4.accounts.*;

public class Bank {
	
	private static Bank instance; 
	
	private ArrayList<BaseAccount> accounts;
	
	private Timer timer;
	
	class PayInterestTask extends TimerTask
	{
		@Override
		public void run() {
			for (int i = 0; i < accounts.size(); i++)
			{
				if (accounts.get(i).getClass() == SavingAccount.class)
				{
					((SavingAccount)(accounts.get(i))).payInterest();
				}
				if (accounts.get(i).getClass() == OverdraftAccount.class)
				{
					((OverdraftAccount)(accounts.get(i))).payInterest();
				}
			}
		}
	}

	private Bank()
	{
		accounts = new ArrayList<BaseAccount>();
		timer = new Timer();
		timer.schedule(new PayInterestTask(), 1, 1000);
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
