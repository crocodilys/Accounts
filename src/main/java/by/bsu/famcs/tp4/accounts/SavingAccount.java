package by.bsu.famcs.tp4.accounts;

public class SavingAccount extends BaseAccount {
	
	protected double interestRate;

	public SavingAccount(double balance, double interestRate) {
		super(balance);
		this.interestRate = interestRate;
	}
	
	public double getInterestRate()
	{
		return interestRate;
	}
	
	public void setInterestRate(double interestRate)
	{
		this.interestRate = interestRate;
	}
	
	public void payInterest()
	{
		balance += balance * interestRate;
	}

}
