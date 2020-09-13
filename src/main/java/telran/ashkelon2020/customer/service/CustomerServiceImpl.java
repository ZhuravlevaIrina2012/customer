package telran.ashkelon2020.customer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.ashkelon2020.customer.dto.AccountDto;
import telran.ashkelon2020.customer.dto.CustomerDto;
import telran.ashkelon2020.customer.dto.NewAccountDto;
import telran.ashkelon2020.customer.dto.NewCustomerDto;
import telran.ashkelon2020.customer.dto.SubscriberDto;
import telran.ashkelon2020.customer.exception.DocumentNotFoundException;
import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;
import telran.ashkelon2020.customer.model.Subscriber;
import telran.ashkelon2020.customer.repository.AccountRepository;
import telran.ashkelon2020.customer.repository.CustomerRepository;
import telran.ashkelon2020.customer.repository.SubscriberRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	SubscriberRepository subscriberRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	@Transactional
	public boolean addCustomer(NewCustomerDto newCustomerDto) {
		if (customerRepository.existsById(newCustomerDto.getId())) {
			return false;
		}
		Customer customer = modelMapper.map(newCustomerDto, Customer.class);
		customerRepository.save(customer);
		return true;
	}

	@Override
	public CustomerDto findCustomer(String id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public CustomerDto updateCustomer(String id, String name) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
		customer.setName(name);
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public CustomerDto deleteCustomer(String id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
		customer.getAccounts().forEach(a -> deleteAccount(a.getLogin()));
		customerRepository.delete(customer);
		return modelMapper.map(customer, CustomerDto.class);
	}

	@Override
	@Transactional
	public boolean addAccount(NewAccountDto newAccountDto) {
		if (accountRepository.existsById(newAccountDto.getLogin())) {
			return false;
		}
		Customer customer = customerRepository.findByName(newAccountDto.getCustomerName());
		if (customer == null) {
			throw new DocumentNotFoundException(newAccountDto.getCustomerName());
		}
		Account account = new Account(newAccountDto.getLogin(), newAccountDto.getTopic(), customer);
		accountRepository.save(account);
		return true;
	}

	@Override
	public AccountDto findAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new DocumentNotFoundException(login));
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto updateAccount(String login, String topic) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new DocumentNotFoundException(login)); 
		account.setTopic(topic);
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public AccountDto deleteAccount(String login) {
		Account account = accountRepository.findById(login).orElseThrow(() -> new DocumentNotFoundException(login));
		account.getSubscribers().forEach(s -> subscriberRepository.delete(s));
		accountRepository.delete(account);
		return modelMapper.map(account, AccountDto.class);
	}

	@Override
	@Transactional
	public boolean addSubscriber(SubscriberDto subscriberDto, String accountId) {
		if (subscriberRepository.existsById(subscriberDto.getLogin())) {
			return false;
		}
		Account account = accountRepository.findById(accountId).orElseThrow(() -> new DocumentNotFoundException(accountId));
		Subscriber subscriber = new Subscriber(subscriberDto.getLogin(), subscriberDto.getName());
		subscriberRepository.save(subscriber);
		if (!account.getSubscribers().contains(subscriber)) {
			account.getSubscribers().add(subscriber);
			accountRepository.save(account);
		}
		return true;
	}

	@Override
	public SubscriberDto findSubscriber(String id) {
		Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
		return modelMapper.map(subscriber, SubscriberDto.class);
	}

	@Override
	@Transactional
	public SubscriberDto updateSubscriber(String id, String name) {
		Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
		subscriber.setName(name);
		return modelMapper.map(subscriber, SubscriberDto.class);
	}

	@Override
	@Transactional
	public SubscriberDto deleteSubscriber(String id) {
		Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
		subscriber.getAccounts().forEach(a -> accountRepository.findById(a.getLogin()).get().getSubscribers().remove(subscriber));
		subscriberRepository.delete(subscriber);
		return modelMapper.map(subscriber, SubscriberDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AccountDto> findAccountByTopic(String customerId, String topic) {
		return customerRepository.findAccountByTopic(customerId, topic)
					.map(a -> modelMapper.map(a, AccountDto.class))
					.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<SubscriberDto> findSubscriberByCustomer(String customerId) {
		return subscriberRepository.findByAccountsCustomerId(customerId)
					.map(s -> modelMapper.map(s, SubscriberDto.class))
					.collect(Collectors.toList());
	}
	
	

	

	
}
