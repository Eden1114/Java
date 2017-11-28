/**
为了下次再做这个题目的时候，
可以重用代码，这次多写点注释。
@author Eden
@version 1.0
*/

/**
 * 银行账户类<br>
 * 实现了存钱，取钱<br>
 * @author Eden
 */
class Account {    
	protected double balance;    

	/**
	 * Constructor
	 * @param init_balance
	 */
	public Account(double init_balance) {    
		this.balance = init_balance;    
	}
	
	/**
	 * 返回账户余额
	 * @return this.balance
	 */
	public double getBalance() {    
		return this.balance;    
	}    
	
	/**
	 * 存钱
	 * @param amount
	 * @return 存钱成功返回true
	 */
	public boolean deposit(double amount) {    
		this.balance += amount;    
		return true;
	}
	
	/**
	 * 取钱
	 * 当balance不足的时候，抛出OverdraftException异常
	 * @param amount
	 * @throws OverdraftException
	 */
	public void withdraw(double amount) throws OverdraftException {    
		if(this.balance >= amount) {    
			this.balance -= amount;    
		}  
		else   
		{    
			throw new OverdraftException("Insufficient funds", amount-this.balance);  
		}    
	}
	
}    



/**
 *  * 顾客类<br>
 * 一个顾客可以拥有多个账户。这里用ArrayList维护。<br>
 * @author Eden
 *
 */
class Customer {    
	private String firstName;
	private String lastName;
	private ArrayList<Account> accounts;
		
	/**
	 * Constructor 
	 * @param firstName 用户的名
	 * @param lastName 用户的姓
	 */
	public Customer(String firstName,String lastName)    
	{    
		this.lastName = lastName;    
			
		this.firstName = firstName;    
			
		accounts = new ArrayList<Account>();
	}    
	
	/**
	 * 返回当前用户的第accountIndex个账户
	 * @param accountIndex
	 * @return
	 */
	public Account getAccount(int accountIndex) {    
		return this.accounts.get(accountIndex);  
	}    
	
	/**
	 * 添加账户
	 * @param account
	 */
	public void addAccount(Account account)    
	{    
		this.accounts.add(account);  
	}  
	 
	/**
	 * 返回账户索引（指向this.Accounts的迭代器）
	 * @return
	 */
	public Iterator<Account> getAccounts()  
	{  
		return this.accounts.iterator();  
	}  
	
	/**
	 * 
	 * @return 用户的名
	 */
	public String getFirstName() {    
		return this.firstName;    
	}  
	
	/**
	 * 
	 * @return 用户的姓
	 */
	public String getLastName() {    
		return this.lastName;    
	}  
	
	/**
	 * @return 
	 */
	public String toString(){  
		return "[" +this.firstName+" " +this.lastName +"]";  
	}
	
}    
	
/** 
 * 银行类
 * 使用static factory method
 * 构造了唯一的实例bank
 * 一个银行有多个顾客，使用ArrayList维护
 * @author Eden
 */  
class Bank {    
	private ArrayList<Customer> customers;  
	private static Bank bankInstance = new Bank();  
	  
	private  Bank() {    
		customers = new ArrayList<Customer>();    
	}  
	  
	public static Bank getBank()  
	{  
		return bankInstance;  
	}  
	  
	public void addCustomer(String f, String l) {    
		customers.add(new Customer(f,l));  
	}    
		
	public int getNumOfCustomers() {    
		return customers.size();  
	}    
		
	public Customer getCustomer(int index) {    
		return this.customers.get(index);    
	}  
	  
	public Iterator<Customer> GetCustomers() {  
		return customers.iterator();  
	}  
	  
//  public void sortCustomers() {  
//      Collections.sort(customers);  
//  }
}    
	
/**
 * 活期账户
 * @Inherited Account
 */

class SavingsAccount extends Account {    
	private double interestRate;    
		
	public SavingsAccount(double balance, double interest_rate) {    
		super(balance);    
		this.interestRate = interest_rate;    
	}    
		
	public void setInerestRate(double interestRate) {    
		this.interestRate = interestRate;    
	}    
}    
	
class CheckingAccount extends Account {    
	private double overdraftProtection;    
		
	public CheckingAccount(double balance) {    
		super(balance);    
	}
		
	public CheckingAccount(double balance, double protect) {    
		super(balance);    
		this.overdraftProtection = protect;    
	}
		
	public void withdraw(double amount) throws OverdraftException {      
		if (this.balance >= amount) {
			this.balance -= amount;  
		}
		else if (this.overdraftProtection  + this.balance >= amount) {      
			this.overdraftProtection -= (amount - this.balance);      
			this.balance = 0;
		}  
		else {  
			if(overdraftProtection == 0)
				throw new OverdraftException("No overdraft protection",amount-balance);  
			else
				throw new OverdraftException("Insufficient funds", amount-this.balance-this.overdraftProtection);  
		}
			
	}      
}  
  
 /**
  * 余额不足异常
  * @param deficit 表示还需要多少钱才能完成操作。
  * @author Eden
  *
  */
class OverdraftException extends Exception{  
	private double deficit;  
	public OverdraftException(String message, double deficit)  
	{  
		super(message);  
		this.deficit = deficit;  
	}  
	public double getDeficit()  
	{  
		return this.deficit;  
	}  
}  
  
/**
 * 用户报告类
 * 用于打印用户的信息
 */
class CustomerReport {  
	Bank defaultbank;  
	  
	public void generateReport() {  
		NumberFormat currency_format = NumberFormat.getCurrencyInstance();  
		  
		defaultbank = Bank.getBank();  
		Customer customer;  
		System.out.println("CUSTOMERS REPORT");  
		System.out.println("================");  
		Iterator<Customer> iteratorCustomer;  
		iteratorCustomer = defaultbank.GetCustomers();  
		  
		while (iteratorCustomer.hasNext()) {  
			customer = iteratorCustomer.next();  
			System.out.println();  
			System.out.println("Customer: " + "["+customer.getFirstName() + " " + customer.getLastName() + "]");  
			Iterator<Account> iteratorAccount = customer.getAccounts();  
			while(iteratorAccount.hasNext())   
			{  
				Account account = iteratorAccount.next();  
				String account_type = "";  
				  
				if(account instanceof SavingsAccount)  
				{  
					account_type = "Savings Account";  
				}   
				else if(account instanceof CheckingAccount)  
				{  
					account_type = "Checking Account";  
				}   
				else  
				{  
					account_type = "Unknown Account Type";  
				}   
				
				System.out.print("    " + account_type + ": current balance is $");  
				System.out.format("%.1f\n", account.getBalance());  
			}  
		  
		}  
	}  
}