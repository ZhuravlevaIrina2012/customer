package telran.ashkelon2020.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.NewAccountDto;
import telran.ashkelon2020.customer.dto.NewCustomerDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customer")
	public boolean addCustomer(@RequestBody NewCustomerDto newCustomerDto) {
		return customerService.addCustomer(newCustomerDto);
	}
	
	@GetMapping("/customer/{id}")
	public CustomerDto findCustomer(@PathVariable String id) {
		return customerService.findCustomer(id);
	}
	
	@PutMapping("/customer/{id}/name/{name}")
	public CustomerDto updateCustomer(@PathVariable String id, @PathVariable String name) {
		return customerService.updateCustomer(id, name);
	}
	
	@DeleteMapping("/customer/{id}")
	public CustomerDto deleteCustomer(@PathVariable String id) {
		return customerService.deleteCustomer(id);
	}
	
	@PostMapping("/account")
	public boolean addAccount(@RequestBody NewAccountDto newAccountDto) {
		return customerService.addAccount(newAccountDto);
	}
	
	@GetMapping("/account/{login}")
	public AccountDto findAccount(@PathVariable String login) {
		return customerService.findAccount(login);
	}
	
	@PutMapping("/account/{login}/topic/{topic}")
	public AccountDto updateAccount(@PathVariable String login, @PathVariable String topic) {
		return customerService.updateAccount(login, topic);
	}
	
	@DeleteMapping("/account/{login}")
	public AccountDto deleteAccount(@PathVariable String login) {
		return customerService.deleteAccount(login);
	}
	
	@PostMapping("/subscriber/account/{accountId}")
	public boolean addSubscriber(@RequestBody SubscriberDto subscriberDto, @PathVariable String accountId) {
		return customerService.addSubscriber(subscriberDto, accountId);
	}
	
	@GetMapping("/subscriber/{id}")
	public SubscriberDto findSubscriber(@PathVariable String id) {
		return customerService.findSubscriber(id);
	}
	
	@PutMapping("/subscriber/{id}/name/{name}")
	public SubscriberDto updateSubscriber(@PathVariable String id, @PathVariable String name) {
		return customerService.updateSubscriber(id, name);
	}
	
	@DeleteMapping("/subscriber/{id}")
	public SubscriberDto deleteSubscriber(@PathVariable String id) {
		return customerService.deleteSubscriber(id);
	}
	
	@GetMapping("/customer/{customerId}/subscribers")
	public List<SubscriberDto> findSubscriberByCustomer(@PathVariable String customerId) {
		return customerService.findSubscriberByCustomer(customerId);
	}
	
	@GetMapping("/customer/{customerId}/accounts/topic/{topic}")
	public List<AccountDto> findAccountByTopic(@PathVariable String customerId, @PathVariable String topic) {
		return customerService.findAccountByTopic(customerId, topic);
	}
}
