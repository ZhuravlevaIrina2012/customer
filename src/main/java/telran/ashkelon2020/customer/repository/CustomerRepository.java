package telran.ashkelon2020.customer.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.ashkelon2020.customer.model.Account;
import telran.ashkelon2020.customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
	Customer findByName(String name);
	
	@Query("select a from Customer c join c.accounts a where c.id=?1 and a.topic=?2")
	Stream<Account> findAccountByTopic(String id, String topic);
}
