package com.ph;

import java.util.function.Predicate;

public class HelloPredicate {
	public static void main(String[] args) {
		Customer newCustomer = new Customer();
		newCustomer.setName("maru");
		
		PromotionService promotionService = new PromotionService(newCustomer);
		promotionService.sendPromotion(customer -> "maru".equals(customer.getName()));
	}
}

class Customer{
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}

class PromotionService{
	private Customer customer;
	public PromotionService(Customer customer) {
		this.customer = customer;
	}

	public void sendPromotion(Predicate<Customer> p) {
		if(p.test(customer)) {
			System.out.println("Send Promotional Email");
		}
	}
	
}
