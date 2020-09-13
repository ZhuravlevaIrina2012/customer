package telran.ashkelon2020.customer.service;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.NewAccountDto;
import telran.ashkelon2020.customer.dto.NewCustomerDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;

public interface CustomerService {
	
	boolean addCustomer(NewCustomerDto newCustomerDto);
	
	CustomerDto findCustomer(String id);
	
	CustomerDto updateCustomer(String id, String name);
	
	CustomerDto deleteCustomer(String id);
	
	boolean addAccount(NewAccountDto newAccountDto);
	
	AccountDto findAccount(String login);
	
	AccountDto updateAccount(String login, String topic);
	
	AccountDto deleteAccount(String login);
	
	boolean addSubscriber(SubscriberDto subscriberDto, String accountId);
	
	SubscriberDto findSubscriber(String id);
	
	SubscriberDto updateSubscriber(String id, String name);
	
	SubscriberDto deleteSubscriber(String id);
	
}
