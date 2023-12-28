package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.enity.Customer;
import com.ilp.enity.Product;
import com.ilp.enity.Service;
import com.ilp.service.BankService;

public class BankUtility {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Customer customer = null;
	
		System.out.println("Welcome to Bank");
		char choice;
		
		do {
			System.out.println("Choose an option : 1.Create Service 2.Create Product 3.Create Customer 4.Manage Account 5.Display Customer 6.Exit");
			int option = scanner.nextInt();
			scanner.nextLine();
			
			switch(option) {
			
			case 1:
				BankService.createService(serviceList);
					//System.out.println(serviceList);
					break;
				
			case 2:
				BankService.createProduct(productList,serviceList);
				//System.out.println("Option 2");
				break;
				   
			case 3:
				
			    customer = BankService.createCustomer(customer,productList,customerList);
				break;
				
			case 4:
         		BankService.manageAccount(customer);
         		break;
         		
			case 5:
				BankService.displayCustomers(customer);
				break;
				
			}
			System.out.println("Do you want to continue y/n?");
			choice = scanner.next().charAt(0);
			
			
		} while(choice=='y');
		

	}

}
