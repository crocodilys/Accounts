package by.bsu.famcs.tp4.accounts;
import java.util.Random;

public class BaseAccount {
	protected static int currentId = 10000000;
	protected double balance;
	protected int id;
	protected int pin;
	protected boolean active;
	
	public BaseAccount(double balance)
	{
		this.id = currentId++;
		this.balance = balance;
		this.pin = (new Random()).nextInt(8999) + 1000;
		this.active = true;
	}
	
	public double withdraw(double value)
	{
		if (value > balance)
		{
			return 0;
		}
		else
		{
			setBalance(balance - value);
			return value;
		}
	}
	
	protected void setBalance(double value)
	{
		balance = value;
	}
	
	public void add(double value)
	{
		balance += value;
	}
	
	public double getBalance()
	{
		return balance;
	}

	public int getId() {
		return id;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		if (pin < 1000 || pin > 9999)
			return;
		this.pin = pin;
	}
	
	public void setRandomPin()
	{
		this.pin = (new Random()).nextInt(8999) + 1000;
	}
	
	public void activate()
	{
		this.active = true;
	}
	
	public void deactivate()
	{
		this.active = false;
	}
	
	public boolean getActivity()
	{
		return active;
	}
	
	@Override
	public String toString()
	{
		return new String("Account type: " + this.getClass() + ". Account id: " + this.getId() + 
				". Account pin: " + this.pin + ". Account balance: " + this.getBalance() + ".");
	}
}
