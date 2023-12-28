package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.enity.Account;
import com.ilp.enity.CurrentAccount;
import com.ilp.enity.Customer;
import com.ilp.enity.LoanAccount;
import com.ilp.enity.Product;
import com.ilp.enity.SavingsMaxAccount;
import com.ilp.enity.Service;

public class BankService {

	public static void createService(ArrayList<Service> serviceList) {

		char serviceCreateChoice;
		Scanner scanner = new Scanner(System.in);

		do {

			System.out.println("Enter the service code :");
			String serviceCode = scanner.nextLine();
			// scanner.nextLine();
			System.out.println("Enter the service name :");
			String serviceName = scanner.nextLine();
			System.out.println("Enter the rate :");
			double rate = scanner.nextDouble();
			scanner.nextLine();

			Service service = new Service(serviceCode, serviceName, rate);
			serviceList.add(service);

			System.out.println("Do you want to add more service y/n");
			serviceCreateChoice = scanner.next().charAt(0);
			scanner.nextLine();

		} while (serviceCreateChoice == 'y' || serviceCreateChoice == 'Y');
	}

	public static void createProduct(ArrayList<Product> productList, ArrayList<Service> serviceList) {
		
		ArrayList<Service> productServiceList = new ArrayList<Service> ();
		Scanner scanner = new Scanner(System.in);
		char choice;
		
		do {
		
		//enter the product code,name
		System.out.println("Enter the product code :");
		String productCode = scanner.nextLine();
		System.out.println("Enter the product name :");
		String productName = scanner.nextLine();		
		 char moreServiceChoice;
		do {
		//list the services
		System.out.println(serviceList);		
		
		//enter the service
		System.out.println("Enter the service name of the service you want to add to product");
		String serviceChoice = scanner.nextLine();
		
		for(Service service:serviceList)
		{
			if(service.getServiceName().equalsIgnoreCase(serviceChoice)) {
				productServiceList.add(service);
			}
		}
		if(productName.equals("SavingsMaxAccount"))
		{
		
			Product product = new SavingsMaxAccount(productCode,productName,productServiceList);
			productList.add(product);
		}
		
		else if(productName.equals("CurrentAccount"))
		{
			Product product = new CurrentAccount(productCode,productName,productServiceList);
			productList.add(product);
		}
		
		else if(productName.equals("LoanAccount"))
		{
			Product product = new LoanAccount(productCode,productName,productServiceList);
			productList.add(product);
		}
		System.out.println("Do you want to add more services to this product y/n?");
		moreServiceChoice = scanner.nextLine().charAt(0);
		 }while(moreServiceChoice=='y');
			System.out.println("Do you want to add more products y/n?");
		choice = scanner.nextLine().charAt(0);
		}while(choice == 'y' || choice == 'Y');

	}
	
	public static Customer createCustomer(Customer customer, ArrayList<Product> productList, ArrayList<Customer> customerList)
	{
		Scanner scanner = new Scanner(System.in);
		ArrayList<Account> accountList = new ArrayList<Account>();
		
		int choice;
		String customerIdCheck;
		System.out.println("Enter customer id :");
		customerIdCheck = scanner.nextLine();
		
		if(customer == null)
		{
		
	
			
			char accountChoice;
			do {
			
			System.out.println("Enter account type 1.Savings Account 2.Current Account 3.Loan Account");
			choice = scanner.nextInt();
			scanner.nextLine();
			String accountNumber;
			double accountBalance;
			String accountType;
			Account account;
	
			
			switch(choice) {
			
			case 1:
		
				System.out.println("Enter account number");
				accountNumber = scanner.nextLine();	
	
				System.out.println("Enter balance");
				accountBalance = scanner.nextDouble();
				scanner.nextLine();
				accountType="SavingsMaxAccount";
				
				account = new Account(accountNumber,accountType,accountBalance,productList.get(0));
				accountList.add(account);
			    break;
				
			case 2:	
				
				System.out.println("Enter account number");
				accountNumber = scanner.nextLine();
	
				System.out.println("Enter balance");
				accountBalance = scanner.nextDouble();
				scanner.nextLine();
				accountType="CurrentAccount";
				
				
				account = new Account(accountNumber,accountType,accountBalance,productList.get(1));
				accountList.add(account);
				break;
				
			case 3:
				
				System.out.println("Enter account number");
				accountNumber = scanner.nextLine();
				
				System.out.println("Enter balance");
				accountBalance = scanner.nextDouble();
				scanner.nextLine();
				accountType="LoanAccount";
				scanner.nextLine();
				
				account = new Account(accountNumber,accountType,accountBalance,productList.get(2));
				accountList.add(account);
				break;					
				
			}
			
			
		    
		    System.out.println("Do you want to create another account y/n");
		    accountChoice = scanner.next().charAt(0);
			scanner.nextLine();
			}while(accountChoice == 'y' || accountChoice == 'Y');
			
			System.out.println("Enter customer code :");
			String customerCode = scanner.nextLine();
			scanner.nextLine();
			System.out.println("Enter customer name");
			String customerName = scanner.nextLine();
			
			customer = new Customer(customerCode,customerName,accountList);			
		    customerList.add(customer);
		} else {
			System.out.println("Customer already exists");
		}
		   
		
	
		return customer;
		
		
	}

	public static void manageAccount(Customer customer) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter customer code :");
		String customerCode = scanner.nextLine();
		ArrayList<Account> accountList = new ArrayList<Account>();
		
		if(customerCode.equalsIgnoreCase(customer.getCustomerCode()))
	
		
		for(Account account : customer.getAccountList())
		{
			accountList = customer.getAccountList();
			System.out.println(account.getAccountNumber()+"  "+account.getAccountType());
		}
		System.out.println("Account List: "+accountList);
		System.out.println("Which acccount you want to manage? Type account type");
		String accountNumber = scanner.nextLine();
		
		for(Account account : accountList ) {
			if(accountNumber.equalsIgnoreCase(account.getAccountNumber()))
			{
				manageCustomerAccount(account);
			}
			
		}	
		
		
	}

	private static void manageCustomerAccount(Account account) {
		
		Scanner scanner = new Scanner(System.in);
	
		if(account.getProduct() instanceof SavingsMaxAccount || account.getProduct() instanceof CurrentAccount ) {
			System.out.println("Choose an option 1.Deposit Money 2.Withdraw Money 3.Display Balance");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			
			case 1:
				depositMoney(account);
				break;
				
			case 2:
				withdrawMoney(account);
				break;
				
			case 3:
				System.out.println("Your account balance is :" + account.getAccountBalance());	
				break;
			
			}			
			
		}
		
		else if(account.getProduct() instanceof LoanAccount) {
			System.out.println("Choose an option 1.Deposit Money 2.Display Balance");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			
			case 1:
				depositMoney(account);
				break;
				
			case 2:
				System.out.println("Your account balance is :" + account.getAccountBalance());
				break;
			}
		}
		
	}
	

	private static void depositMoney(Account account) {
		
		Scanner scanner = new Scanner(System.in); 
		double balance=0;
		
		if(account.getProduct() instanceof SavingsMaxAccount || account.getProduct() instanceof CurrentAccount )
		{
			balance = account.getAccountBalance();
			System.out.println("Enter the amount you want to deposit :");
			double depositMoney = scanner.nextDouble();
			scanner.nextLine();
			balance = balance + depositMoney;
			account.setAccountBalance(balance);
			System.out.println("Your new balance is :" + account.getAccountBalance());
		}
		
		else if(account.getProduct() instanceof LoanAccount) {
			
			balance = account.getAccountBalance();
			System.out.println("Enter the amount you want to deposit");
			double depositMoney = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Which mode of deposit you want to make 1.Cash Deposit 2.Cheque deposit");
			int choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			
			case 1:
				System.out.println("You have chosen Cash Deposit");
				//double balance = account.getAccountBalance();
				balance = balance + depositMoney;
				account.setAccountBalance(balance);
				System.out.println("Your new balance is :" + account.getAccountBalance());
				break;
				
			case 2:
				System.out.println("You have chosen Cheque Deposit");
				LoanAccount loanAccount = (LoanAccount)account.getProduct();
				double amountReduced = ((loanAccount.getChequeDepositCharge()*depositMoney)/100);
				depositMoney = depositMoney - amountReduced;
				balance = balance + depositMoney;
				account.setAccountBalance(balance);	
				System.out.println("Your new balance is :" + account.getAccountBalance());
				break;
				
			}
			
		}
		
	}
	
	private static void withdrawMoney(Account account) {
		
		Scanner scanner = new Scanner(System.in); 
		if(account.getProduct() instanceof SavingsMaxAccount)
		{
			double balance = account.getAccountBalance();
			System.out.println("Enter the amount you want to withdraw :");
			double withdrawMoney = scanner.nextInt();
			scanner.nextLine();
			balance = balance - withdrawMoney;
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount)account.getProduct();
			
			if(balance < savingsMaxAccount.getMinimumBalance())
			{
				System.out.println("You have to maintain a minimum balnce of 1000 Rs");
			}
			
			else
			{
				account.setAccountBalance(balance);
				System.out.println("Your new balance is :" + account.getAccountBalance());
				
			}			
			
		}
		
		else
		{
			double balance = account.getAccountBalance();
			System.out.println("Enter the amount you want to withdraw :");
			double withdrawMoney = scanner.nextInt();
			scanner.nextLine();
			balance = balance - withdrawMoney;
			
			if(balance < 0)
			{
				System.out.println("Insuffecient Funds");
			}
			
			else
			{
				account.setAccountBalance(balance);
				System.out.println("Your new balance is :" + account.getAccountBalance());
			}			
			
		}		
		
	}

	public static void displayCustomers(Customer customer) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer id of customer you want to get details");
		String customerId = scanner.nextLine();
		
		if(customer.getCustomerCode().equalsIgnoreCase(customerId))
		{
			System.out.println("Customer Code" + customer.getCustomerCode());
			System.out.println("Customer Name" + customer.getCustomerName());
			System.out.println("Account details are: ");
			
			for(Account account: customer.getAccountList())
			{
				System.out.println("Account Number is :" +account.getAccountNumber());
				System.out.println("Account type is :" + account.getAccountType());
				System.out.println("Account Balance is :" + account.getAccountBalance());
			}
		}
		
	}

}
