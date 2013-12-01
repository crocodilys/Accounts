package by.bsu.famcs.tp4.accounts;

public class TimedMaturityAccount extends BaseAccount {
	
	protected double penaltyRate;
	protected boolean maturityOccured;

	public TimedMaturityAccount(double balance, double penaltyRate) {
		super(balance);
		this.penaltyRate = penaltyRate;
	}
	
	@Override 
	public double withdraw(double value)
	{
		if (value > balance)
		{
			return 0;
		}
		else
		{
			if (!maturityOccured)
			{
				setBalance(balance - value);
				return value;
			} 
			else
			{
				setBalance(balance - value);
				return value - (value * penaltyRate);
			}
		}
	}
	
	public boolean isMaturityOccured() {
		return maturityOccured;
	}

	public void setMaturityOccured(boolean maturityOccured) {
		this.maturityOccured = maturityOccured;
	}


	public double getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(double penaltyRate) {
		if (penaltyRate > 1 || penaltyRate < 0)
		{
			return;
		}
		this.penaltyRate = penaltyRate;
	}
}
