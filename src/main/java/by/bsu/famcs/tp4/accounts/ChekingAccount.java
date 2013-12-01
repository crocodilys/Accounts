package by.bsu.famcs.tp4.accounts;

public class ChekingAccount extends BaseAccount {

	protected int mounthlyQuota;
	protected int totalTransactions;
	protected double transactionFee;
	
	public ChekingAccount(double balance, int mounthlyQuota, double transactionFee) {
		super(balance);
		this.mounthlyQuota = mounthlyQuota;
		this.transactionFee = transactionFee;
		this.totalTransactions = 0;
	}
	
	@Override 
	public double withdraw(double value)
	{
		if (value > balance || 
				(((value + transactionFee) > balance) && (totalTransactions >= mounthlyQuota)))
		{
			return 0;
		}
		else
		{
			totalTransactions++;
			setBalance(balance - value);
			if (totalTransactions > mounthlyQuota)
			{
				setBalance(balance - transactionFee);
			}
			return value;
		}
	}
	
	@Override
	public double getBalance()
	{
		totalTransactions++;
		return balance;
	}

	public int getMounthlyQuota() {
		return mounthlyQuota;
	}

	public void setMounthlyQuota(int mounthlyQuota) {
		this.mounthlyQuota = mounthlyQuota;
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(double transactionFee) {
		this.transactionFee = transactionFee;
	}
	
	

}
