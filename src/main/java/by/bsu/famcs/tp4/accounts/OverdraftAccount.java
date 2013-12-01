package by.bsu.famcs.tp4.accounts;

public class OverdraftAccount extends BaseAccount {

	protected double interesetRate;
	
	public OverdraftAccount(double balance, double interestRate) {
		super(balance);
		this.interesetRate = interestRate;
	}
	
	@Override
	public double withdraw(double value)
	{
		setBalance(balance - value);
		return value;
	}
	
	public void payInterest()
	{
		if (balance >= 0)
		{
			return;
		}
		setBalance(balance + (balance * interesetRate));
	}
	
	public double getInteresetRate() {
		return interesetRate;
	}

	public void setInteresetRate(double interesetRate) {
		this.interesetRate = interesetRate;
	}

}
