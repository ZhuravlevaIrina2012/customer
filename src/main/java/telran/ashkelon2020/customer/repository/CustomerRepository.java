package telran.ashkelon2020.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.ashkelon2020.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
	Customer findByName(String name);
	
}
