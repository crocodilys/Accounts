package by.bsu.famcs.tp4.logic;
import java.util.Scanner;



public class ATM {
	private double cash;
	
	private int id;
	private int curId;
	
	private Scanner sc;
	
	public static String OP_ID_ENTER = "Please, enter your account id.";
	public static String OP_PIN_ENTER = "Please, enter PIN. Enter 0 to return card.";
	public static String OP_LIST = "1 - Cash withdraw. 2 - Check balance. 0 - Card return.";
	public static String OP_CASH_WITHDRAW = "Enter amount, you want to withdraw.";
	
	public static String MSG_OP_SUCCESS = "Success!";
	
	public static String ERR_INCORECT_ID = "ID is incorrect. Please, enter id again.";
	public static String ERR_INCORECT_PIN = "Pin is incorrect. Please, try again.";
	public static String ERR_ACCOUNT_BLOCKED = "Account blocked.";
	public static String ERR_NOT_ENOUGH_CASH = "Sorry, i haven't got enough cash.";
	public static String ERR_LITTLE_BALANCE = "Insufficient funds.";
	
	public ATM(double cash) 
	{
		sc = new Scanner(System.in);
		this.cash = cash;
	}
	
	public void start()
	{
		id = 0;
		while (true)
		{
			if (id == 0)
			{
				print(OP_ID_ENTER);
				curId = sc.nextInt();
				if (Bank.getInstance().searchAccount(curId) == null)
				{
					print(ERR_INCORECT_ID);
					id = 0;
				} 
				else
				if (!Bank.getInstance().searchAccount(curId).getActivity())
				{
					print(ERR_ACCOUNT_BLOCKED);
					id = 0;
				} else
				if (!checkPin())
				{
					Bank.getInstance().searchAccount(id).deactivate();
					print(ERR_ACCOUNT_BLOCKED);
					id = 0;
				}
			}
			else
			{	
				print(OP_LIST);
				int operation = sc.nextInt();
				switch(operation)
				{
				case 1:
					withdraw();
					break;
				case 2:
					checkBalance();
					break;
				case 0:
					id = 0;
					break;
				}
			}
		}
	}
	
	private void checkBalance()
	{
		print("Your balance: " + Bank.getInstance().searchAccount(id).getBalance());
	}

	private void withdraw()
	{
		print(OP_CASH_WITHDRAW);
		double value = sc.nextDouble();
		if (value > cash)
		{
			print(ERR_NOT_ENOUGH_CASH);
			return;
		}
		if (Bank.getInstance().searchAccount(id).withdraw(value) != 0)
		{
			print(MSG_OP_SUCCESS);
		}
		else
		{
			print(ERR_LITTLE_BALANCE);
		}
	}
	
	private boolean checkPin()
	{
		for (int i = 0; i < 3; i++)
		{
			print(OP_PIN_ENTER);
			int pin = sc.nextInt();
			if (pin == 0)
			{
				id = 0;
				return true;
			}
			if (Bank.getInstance().checkPin(pin, curId))
			{
				id = curId;
				return true;
			}
		}
		return false;
	}
	
	private void print(String string)
	{
		System.out.println(string);
	}
	
	public int getId() {
		return id;
	}
	
}
