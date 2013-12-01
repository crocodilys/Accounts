package by.bsu.famcs.tp4.main;
import java.util.ArrayList;

import by.bsu.famcs.tp4.accounts.BaseAccount;

import by.bsu.famcs.tp4.logic.*;


public class Main {

	public static void main(String[] args) {
		ATM atm = new ATM(2000000000);
		Bank.getInstance().createBaseAccount(Math.random() * 10000);
		Bank.getInstance().createChekingAccount(Math.random() * 10000, 3, 5);
		Bank.getInstance().createOverdraftAccount(Math.random() * 10000, Math.random());
		Bank.getInstance().createSavingAccount(Math.random() * 10000, Math.random());
		Bank.getInstance().createTimedMaturityAccount(Math.random() * 10000, Math.random());
		ArrayList<BaseAccount> accounts = Bank.getInstance().getAccounts();
		for (int i = 0; i < accounts.size(); i++)
		{
			System.out.println(accounts.get(i).toString());
		}
		atm.start();
	}
}
